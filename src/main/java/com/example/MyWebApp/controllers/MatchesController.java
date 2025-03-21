package com.example.MyWebApp.controllers;

import com.example.MyWebApp.models.*;
import com.example.MyWebApp.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Controller
public class MatchesController {
    @Autowired
    MatchService matchService;
    @Autowired
    ClientService clientService;
    @Autowired
    StavkaService stavkaService;
    @Autowired
    ViplataService viplataService;
    @Autowired
    TeamService teamService;

//    private final KafkaProducer kafkaProducer;
//
//    public MatchesController(KafkaProducer kafkaProducer) {
//        this.kafkaProducer = kafkaProducer;
//    }

    @GetMapping("/matches")
    public String match(Model model) {
        model.addAttribute("event", "Все события");
        model.addAttribute("active", "all");
        List<String> list = matchService.allMatches();
        List<String[]> splitArray = new ArrayList<>();
        String blobBase1, blobBase2;
        for (String item : list) {
            String[] splitItem = item.split(",");
            Team team1 = teamService.findById(Integer.parseInt(splitItem[11]));
            Team team2 = teamService.findById(Integer.parseInt(splitItem[12]));
            blobBase1 = Base64.getEncoder().encodeToString(team1.getLogo());
            blobBase2 = Base64.getEncoder().encodeToString(team2.getLogo());
            splitItem[13] = blobBase1;
            splitItem[14] = blobBase2;
            splitArray.add(splitItem);
        }
        model.addAttribute("splitArray", splitArray);
        return "matches";
    }
    @GetMapping("/matches/{id}")
    public String matchByCategory(@PathVariable(name = "id") int id, Model model) {
        if (id == 1) {
            model.addAttribute("event", "Футбол");
            model.addAttribute("active", "foot");
        } else if (id == 2) {
            model.addAttribute("event", "Баскетбол");
            model.addAttribute("active", "basket");
        } else if (id == 3) {
            model.addAttribute("event", "Хоккей");
            model.addAttribute("active", "hokkey");
        }
        List<String> list = matchService.MatchByCategory(id);
        List<String[]> splitArray = new ArrayList<>();
        String blobBase1, blobBase2;
        for (String item : list) {
            String[] splitItem = item.split(",");
            Team team1 = teamService.findById(Integer.parseInt(splitItem[11]));
            Team team2 = teamService.findById(Integer.parseInt(splitItem[12]));
            blobBase1 = Base64.getEncoder().encodeToString(team1.getLogo());
            blobBase2 = Base64.getEncoder().encodeToString(team2.getLogo());
            splitItem[13] = blobBase1;
            splitItem[14] = blobBase2;
            splitArray.add(splitItem);
        }
        model.addAttribute("splitArray", splitArray);
        return "matches";
    }

    @GetMapping("/matches/{id_match}/{num_koef}")
    public String doStavka(@PathVariable(name = "id_match") int id_match,
                           @PathVariable(name = "num_koef") int num_koef, Model model) {
        Matches match = matchService.findById(id_match);
        List<String> list = matchService.NameTeamsById(id_match);
        model.addAttribute("team1", list.getFirst().split(",")[0]);
        model.addAttribute("team2", list.getFirst().split(",")[1]);
        if (num_koef == 1) {
            model.addAttribute("koef", match.getKoef1());
        } else {
            model.addAttribute("koef", match.getKoef2());
        }
        return "makeBet";
    }

    @PostMapping("/matches/{id_match}/{num_koef}")
    public String doneStavka(@PathVariable(name = "id_match") int id_match,
                             @PathVariable(name = "num_koef") int num_koef,
                             @RequestParam String summa,
                             Principal principal, Model model) {
        Matches match = matchService.findById(id_match);
        Client client = clientService.findByUsername(principal.getName());

        double koef;
        int id_bet_team;
        if (num_koef == 1) {
            koef = match.getKoef1();
            id_bet_team = match.getId_team1();
        } else {
            koef = match.getKoef2();
            id_bet_team = match.getId_team2();
        }

        Stavka stavka = new Stavka(Integer.parseInt(summa), koef, LocalDateTime.now(), client.getId(), match.getId(), 1, id_bet_team);
        stavkaService.save(stavka);


//        kafkaProducer.sendMessage("Ваша ставка успешно принята");

        return "redirect:/";
    }

    @GetMapping("/sellBet")
    public String sellBet(Principal principal, Model model) {
        Client client = clientService.findByUsername(principal.getName());
        List<String> list = clientService.BetsViplataNull(client.getId());
        List<String[]> splitArray = new ArrayList<>();
        int sumSell = 0;

        for (String item : list) {
            String[] splitItem = item.split(",");
            double koefClient = Double.parseDouble(splitItem[8]);
            int sumStavki = Integer.parseInt(splitItem[7]);
            Matches match = matchService.findById(Integer.parseInt(splitItem[13]));
            if (match.getId_team1() == Integer.parseInt(splitItem[12])) {
                if (match.getKoef1() < koefClient) {
                    sumSell = (int) (sumStavki * 1.1);
                } else {
                    sumSell = (int) (sumStavki * 0.4);
                }
            } else {
                if (match.getKoef2() < koefClient) {
                    sumSell = (int) (sumStavki * 1.1);
                } else {
                    sumSell = (int) (sumStavki * 0.4);
                }
            }
            splitItem[14] = String.valueOf(sumSell);
            splitArray.add(splitItem);
        }
        model.addAttribute("splitArray", splitArray);
        return "sellBet";
    }

    @GetMapping("/sellBet/{id_stavka}/{sumSell}")
    public String confirmBet(@PathVariable(name = "id_stavka") int id_stavka,
                             @PathVariable(name = "sumSell") int sumSell, Principal principal, Model model) {
        Stavka stavka = stavkaService.findById(id_stavka);
        Viplata viplata = new Viplata(sumSell, LocalDateTime.now(), id_stavka, clientService.findByUsername(principal.getName()).getId());
        viplataService.save(viplata);
        stavka.setId_status(3);
        stavkaService.save(stavka);
        return "redirect:/";
    }

    @GetMapping("/matchesAdmin")
    public String matchesAdmin(Model model) {
        model.addAttribute("event", "Все события");
        model.addAttribute("active", "all");
        List<String> list = matchService.allMatches();
        List<String[]> splitArray = new ArrayList<>();
        String blobBase1, blobBase2;
        for (String item : list) {
            String[] splitItem = item.split(",");
            Team team1 = teamService.findById(Integer.parseInt(splitItem[11]));
            Team team2 = teamService.findById(Integer.parseInt(splitItem[12]));
            blobBase1 = Base64.getEncoder().encodeToString(team1.getLogo());
            blobBase2 = Base64.getEncoder().encodeToString(team2.getLogo());
            splitItem[13] = blobBase1;
            splitItem[14] = blobBase2;
            splitArray.add(splitItem);
        }
        model.addAttribute("splitArray", splitArray);
        return "matchesAdmin";
    }

    @GetMapping("/matchesAdmin/{id}")
    public String matchAdminByCategory(@PathVariable(name = "id") int id, Model model) {
        if (id == 1) {
            model.addAttribute("event", "Футбол");
            model.addAttribute("active", "foot");
        } else if (id == 2) {
            model.addAttribute("event", "Баскетбол");
            model.addAttribute("active", "basket");
        } else if (id == 3) {
            model.addAttribute("event", "Хоккей");
            model.addAttribute("active", "hokkey");
        }
        List<String> list = matchService.MatchByCategory(id);
        List<String[]> splitArray = new ArrayList<>();
        String blobBase1, blobBase2;
        for (String item : list) {
            String[] splitItem = item.split(",");
            Team team1 = teamService.findById(Integer.parseInt(splitItem[11]));
            Team team2 = teamService.findById(Integer.parseInt(splitItem[12]));
            blobBase1 = Base64.getEncoder().encodeToString(team1.getLogo());
            blobBase2 = Base64.getEncoder().encodeToString(team2.getLogo());
            splitItem[13] = blobBase1;
            splitItem[14] = blobBase2;
            splitArray.add(splitItem);
        }
        model.addAttribute("splitArray", splitArray);
        return "matchesAdmin";
    }

    @PostMapping("/confirmChanges/{id_match}")
    public String doChanges(@PathVariable(name = "id_match") int id_match,
                            @RequestParam String count_team1,
                            @RequestParam String count_team2,
                            @RequestParam String koef1,
                            @RequestParam String koef2,
                            @RequestParam String status, Model model) {
        Matches match = matchService.findById(id_match);
        match.setKoef1(Double.parseDouble(koef1));
        match.setKoef2(Double.parseDouble(koef2));
        if (match.getKat_match() == 1) { // только для футбола
            if (match.getCount_team1() < Integer.parseInt(count_team1)) {
                double newkoef1 = match.getKoef1() * 0.75 <= 1 ? 1.05 : match.getKoef1() * 0.75;
                match.setKoef1((double) Math.round(newkoef1 * 100) / 100);
                match.setKoef2((double) Math.round(match.getKoef2() * 130) / 100);
            }
            if (match.getCount_team2() < Integer.parseInt(count_team2)) {
                double newkoef2 = match.getKoef2() * 0.75 <= 1 ? 1.05 : match.getKoef2() * 0.75;
                match.setKoef2((double) Math.round(newkoef2 * 100) / 100);
                match.setKoef1((double) Math.round(match.getKoef1() * 130) / 100);
            }
        }
        match.setCount_team1(Integer.parseInt(count_team1));
        match.setCount_team2(Integer.parseInt(count_team2));
        int id_status;
        if (status.equals("Не начался")) {
            id_status = 1;
        } else if (status.equals("Идет")) {
            id_status = 2;
        } else {
            id_status = 3;
        }
        match.setId_status(id_status);
        matchService.save(match);
        if (id_status == 3) {
            int idWinner = 0;
            if (match.getCount_team1() > match.getCount_team2()) {
                idWinner = match.getId_team1();
            } else if (match.getCount_team1() < match.getCount_team2()) {
                idWinner = match.getId_team2();
            }

            List<String> list = matchService.StavkaOnEndMatch(id_match);
            List<String[]> splitArray = new ArrayList<>();
            for (String item : list) {
                String[] splitItem = item.split(",");
                int sumViplata = 0;
                if (Integer.parseInt(splitItem[12]) == idWinner) {
                    sumViplata = (int) (Integer.parseInt(splitItem[7]) * Double.parseDouble(splitItem[8]));
                }
                Viplata viplata = new Viplata(sumViplata, LocalDateTime.now(), Integer.parseInt(splitItem[15]), Integer.parseInt(splitItem[0]));
                viplataService.save(viplata);
                Stavka stavka = stavkaService.findById(Integer.parseInt(splitItem[15]));
                stavka.setId_status(2);
                stavkaService.save(stavka);
            }
        }
        return "redirect:/matchesAdmin";
    }
}
