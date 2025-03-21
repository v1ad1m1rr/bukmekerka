package com.example.MyWebApp.controllers;

import com.example.MyWebApp.models.*;
import com.example.MyWebApp.repo.*;
import com.example.MyWebApp.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    ClientService clientService;
    @Autowired
    SotrudnikService sotrudnikService;
    @Autowired
    Adres_sotrService adres_sotrService;
    @Autowired
    DolzgnostService dolzgnostService;
    @Autowired
    Kat_matchService katMatchService;
    @Autowired
    MatchService matchService;
    @Autowired
    OtdelService otdelService;
    @Autowired
    StavkaService stavkaService;
    @Autowired
    ViplataService viplataService;
    @Autowired
    ZarplataService zarplataService;
    @Autowired
    TeamService teamService;
    @Autowired
    ChampionshipService championshipService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Главная страница");
        return "home";
    }

    @GetMapping("/onas")
    public String onas() {
        return "onas";
    }

    @GetMapping("crud")
    public String crud(Model model) {
        model.addAttribute("title", "CRUD");
        return "crud";
    }

    @GetMapping("crud/table/{tableName}")
    public String table(@PathVariable(value = "tableName") String tableName, Model model) {
        switch (tableName) {
            case "Client" -> {
                List<Client> clients = clientService.findAll();
                model.addAttribute("spisok", clients);
                model.addAttribute("title", "Client");
                model.addAttribute("nameTable", "Таблица: Клиенты");
            }
            case "Sotrudnik" -> {
                List<Sotrudnik> sotrudniks = sotrudnikService.findAll();
                model.addAttribute("spisok", sotrudniks);
                model.addAttribute("title", "Sotrudnik");
                model.addAttribute("nameTable", "Таблица: Сотрудники");
            }
            case "Adres_sotr" -> {
                List<Adres_sotr> adreses = adres_sotrService.findAll();
                model.addAttribute("spisok", adreses);
                model.addAttribute("title", "Adres_sotr");
                model.addAttribute("nameTable", "Таблица: Адреса Сотрудников");
            }
            case "Dolzgnost" -> {
                List<Dolzgnost> dolzgnosts = dolzgnostService.findAll();
                model.addAttribute("spisok", dolzgnosts);
                model.addAttribute("title", "Dolzgnost");
                model.addAttribute("nameTable", "Таблица: Должности");
            }
            case "Kat_match" -> {
                List<Kat_match> kat_matches = katMatchService.findAll();
                model.addAttribute("spisok", kat_matches);
                model.addAttribute("title", "Kat_match");
                model.addAttribute("nameTable", "Таблица: Категории Матчей");
            }
            case "Matches" -> {
                List<Matches> matches = matchService.findAll();
                model.addAttribute("spisok", matches);
                model.addAttribute("title", "Matches");
                model.addAttribute("nameTable", "Таблица: Матчи");
            }
            case "Otdel" -> {
                List<Otdel> otdels = otdelService.findAll();
                model.addAttribute("spisok", otdels);
                model.addAttribute("title", "Otdel");
                model.addAttribute("nameTable", "Таблица: Отделы");
            }
            case "Stavka" -> {
                List<Stavka> stavki = stavkaService.findAll();
                model.addAttribute("spisok", stavki);
                model.addAttribute("title", "Stavka");
                model.addAttribute("nameTable", "Таблица: Ставки");
            }
            case "Viplata" -> {
                List<Viplata> viplati = viplataService.findAll();
                model.addAttribute("spisok", viplati);
                model.addAttribute("title", "Viplata");
                model.addAttribute("nameTable", "Таблица: Выплаты");
            }
            case "Zarplata" -> {
                List<Zarplata> zarplati = zarplataService.findAll();
                model.addAttribute("spisok", zarplati);
                model.addAttribute("title", "Zarplata");
                model.addAttribute("nameTable", "Таблица: Зарплаты");
            }
        }
        return "table";
    }

    @GetMapping("crud/table/addRow/{tableName}")
    public String addRow(@PathVariable(value = "tableName") String tableName, Model model) {
        model.addAttribute("title", "AddRow");
        model.addAttribute("nameAction", "Добавить запись");
        model.addAttribute("tableName", tableName);
        return "addRow";
    }

    @PostMapping("crud/table/addRow/Client")
    public String addRowClient(@RequestParam String secondName,
                            @RequestParam String firstName,
                            @RequestParam String otchestvo,
                            @RequestParam LocalDate dataRog,
                            @RequestParam LocalDate dataReg,
                            @RequestParam String cardNum,
                            @RequestParam String email,
                            @RequestParam String password,
                            Model model) {
        Client client = new Client(secondName, firstName, otchestvo, dataRog, dataReg, Integer.parseInt(cardNum), email, password);
        for (Client client1 : clientService.findAll()) {
            if (client1.getEmail().equals(client.getEmail())) {
                model.addAttribute("userNameError", "Пользователь с таким именем уже существует");
                return "redirect:/crud/table/addRow/Client";
            }
        }
        for (Sotrudnik sotrudnik : sotrudnikService.findAll()) {
            if (sotrudnik.getEmail().equals(client.getEmail())) {
                model.addAttribute("userNameError", "Пользователь с таким именем уже существует");
                return "redirect:/crud/table/addRow/Client";
            }
        }
        clientService.saveClient(client);
        return table("Client", model);
    }

    @GetMapping("crud/table/changeRow/{tableName}/{id}")
    public String changeRow(@PathVariable(value = "tableName") String tableName, @PathVariable(value = "id") int id, Model model) {
        model.addAttribute("title", "ChangeRow");
        model.addAttribute("nameAction", "Изменить запись");
        model.addAttribute("tableName", tableName);
        if (tableName.equals("Client")) {
            Client client = clientService.findById(id);
            model.addAttribute("client", client);
        } else if (tableName.equals("Sotrudnik")) {
            Sotrudnik sotrudnik = sotrudnikService.findById(id);
            model.addAttribute("sotrudnik", sotrudnik);
        } else if (tableName.equals("Adres_sotr")) {
            Adres_sotr adres_sotr = adres_sotrService.findById(id);
            model.addAttribute("adres_sotr", adres_sotr);
        } else if (tableName.equals("Dolzgnost")) {
            Dolzgnost dolzgnost = dolzgnostService.findById(id);
            model.addAttribute("dolzgnost", dolzgnost);
        } else if (tableName.equals("Kat_match")) {
            Kat_match kat_match = katMatchService.findById(id);
            model.addAttribute("kat_match", kat_match);
        } else if (tableName.equals("Matches")) {
            Matches matches = matchService.findById(id);
            model.addAttribute("matches", matches);
        } else if (tableName.equals("Otdel")) {
            Otdel otdel = otdelService.findById(id);
            model.addAttribute("otdel", otdel);
        } else if (tableName.equals("Stavka")) {
            Stavka stavka = stavkaService.findById(id);
            model.addAttribute("stavka", stavka);
        } else if (tableName.equals("Viplata")) {
            Viplata viplata = viplataService.findById(id);
            model.addAttribute("viplata", viplata);
        } else if (tableName.equals("Zarplata")) {
            Zarplata zarplata = zarplataService.findById(id);
            model.addAttribute("zarplata", zarplata);
        }
        return "changeRow";
    }

    @PostMapping("crud/table/changeRow/Client/{id}")
    public String changeClient(@PathVariable(value = "id") int id,
                         @RequestParam String secondName,
                         @RequestParam String firstName,
                         @RequestParam String otchestvo,
                         @RequestParam LocalDate dataRog,
                         @RequestParam LocalDate dataReg,
                         @RequestParam String cardNum,
                               @RequestParam String email,
                               @RequestParam String password,
                               Model model) {
        Client client = clientService.findById(id);
        if (!client.getEmail().equals(email)) {
            for (Client client1 : clientService.findAll()) {
                if (client1.getEmail().equals(email)) {
                    model.addAttribute("userNameError", "Пользователь с таким именем уже существует");
                    return "redirect:/crud/table/changeRow/Client/{id}";
                }
            }
            for (Sotrudnik sotrudnik : sotrudnikService.findAll()) {
                if (sotrudnik.getEmail().equals(email)) {
                    model.addAttribute("userNameError", "Пользователь с таким именем уже существует");
                    return "redirect:/crud/table/changeRow/Client{id}";
                }
            }
        }
        client.setSecondName(secondName);
        client.setFirstName(firstName);
        client.setOtchestvo(otchestvo);
        client.setBirthDate(dataRog);
        client.setRegistrDate(dataReg);
        client.setNomerCarti(Integer.parseInt(cardNum));
        client.setEmail(email);
        client.setPassword(password);
        clientService.updateClientWithoutPass(client);
        return table("Client", model);
    }

    @GetMapping("crud/table/deleteClient/{id}")
    public String deleteClient(@PathVariable(value = "id") int id, Model model) {
        clientService.deleteClient(id);
        return table("Client", model);
    }

    @PostMapping("crud/table/addRow/Sotrudnik")
    public String addRowSorudnik(@RequestParam String secondName,
                              @RequestParam String firstName,
                              @RequestParam String otchestvo,
                              @RequestParam String tel,
                              @RequestParam LocalDate dataRog,
                              @RequestParam LocalDate dataHire,
                              @RequestParam String idOtdela,
                              @RequestParam String idAdres,
                              @RequestParam String idDolzgnost,
                              @RequestParam String idZarplata,
                                 @RequestParam String email,
                                 @RequestParam String password,
                                 Model model) {
        Sotrudnik sotrudnik = new Sotrudnik(secondName, firstName, otchestvo, Integer.parseInt(tel), dataRog, dataHire,
                Integer.parseInt(idOtdela), Integer.parseInt(idAdres), Integer.parseInt(idDolzgnost), Integer.parseInt(idZarplata),
                email, password);
        for (Client client1 : clientService.findAll()) {
            if (client1.getEmail().equals(sotrudnik.getEmail())) {
                model.addAttribute("userNameError", "Пользователь с таким именем уже существует");
                return "redirect:/crud/table/addRow/Sotrudnik";
            }
        }
        for (Sotrudnik sotrudnik1 : sotrudnikService.findAll()) {
            if (sotrudnik1.getEmail().equals(sotrudnik.getEmail())) {
                model.addAttribute("userNameError", "Пользователь с таким именем уже существует");
                return "redirect:/crud/table/addRow/Sotrudnik";
            }
        }
        sotrudnikService.saveSotr(sotrudnik);
        return table("Sotrudnik", model);
    }

    @PostMapping("crud/table/changeRow/Sotrudnik/{id}")
    public String changeSotrudnik(@PathVariable(value = "id") int id,
                         @RequestParam String secondName,
                         @RequestParam String firstName,
                         @RequestParam String otchestvo,
                         @RequestParam String tel,
                         @RequestParam LocalDate dataRog,
                         @RequestParam LocalDate dataHire,
                         @RequestParam String idOtdel,
                         @RequestParam String idAdres,
                         @RequestParam String idDolzgnost,
                         @RequestParam String idZarplata,
                                  @RequestParam String email,
                                  @RequestParam String password,
                                  Model model) {
        Sotrudnik sotrudnik = sotrudnikService.findById(id);
        if (!sotrudnik.getEmail().equals(email)) {
            for (Client client1 : clientService.findAll()) {
                if (client1.getEmail().equals(email)) {
                    model.addAttribute("userNameError", "Пользователь с таким именем уже существует");
                    return "redirect:/crud/table/changeRow/Sotrudnik/{id}";
                }
            }
            for (Sotrudnik sotrudnik1 : sotrudnikService.findAll()) {
                if (sotrudnik1.getEmail().equals(email)) {
                    model.addAttribute("userNameError", "Пользователь с таким именем уже существует");
                    return "redirect:/crud/table/changeRow/Sotrudnik{id}";
                }
            }
        }
        sotrudnik.setSecondName(secondName);
        sotrudnik.setFirstName(firstName);
        sotrudnik.setOtchestvo(otchestvo);
        sotrudnik.setTel(Integer.parseInt(tel));
        sotrudnik.setBirthDate(dataRog);
        sotrudnik.setHireDate(dataHire);
        sotrudnik.setIdOtdel(Integer.parseInt(idOtdel));
        sotrudnik.setId_adres(Integer.parseInt(idAdres));
        sotrudnik.setId_dolzgnost(Integer.parseInt(idDolzgnost));
        sotrudnik.setId_zarplata(Integer.parseInt(idZarplata));
        sotrudnik.setEmail(email);
        sotrudnik.setPassword(password);
        sotrudnikService.updateSotrWithoutPass(sotrudnik);
        return table("Sotrudnik", model);
    }

    @GetMapping("crud/table/deleteSotrudnik/{id}")
    public String deleteSotrudnik(@PathVariable(value = "id") int id, Model model) {
        sotrudnikService.deleteSotr(id);
        return table("Sotrudnik", model);
    }

    @PostMapping("crud/table/addRow/Adres_sotr")
    public String addRowAdres(@RequestParam String city,
                                 @RequestParam String street,
                                 @RequestParam String house,
                                 @RequestParam String korpus,
                                 @RequestParam String kvartira,
                                 Model model) {
        Adres_sotr adres_sotr = new Adres_sotr(city, street, Integer.parseInt(house), korpus, Integer.parseInt(kvartira));
        adres_sotrService.save(adres_sotr);
        return table("Adres_sotr", model);
    }

    @PostMapping("crud/table/changeRow/Adres_sotr/{id}")
    public String changeAdres_sotr(@PathVariable(value = "id") int id,
                               @RequestParam String city,
                               @RequestParam String street,
                               @RequestParam String house,
                               @RequestParam String korpus,
                               @RequestParam String kvartira, Model model) {
        Adres_sotr adres_sotr = adres_sotrService.findById(id);
        adres_sotr.setCity(city);
        adres_sotr.setStreet(street);
        adres_sotr.setHouse(Integer.parseInt(house));
        adres_sotr.setKorpus(korpus);
        adres_sotr.setKvartira(Integer.parseInt(kvartira));
        adres_sotrService.save(adres_sotr);
        return table("Adres_sotr", model);
    }

    @GetMapping("crud/table/deleteAdres_sotr/{id}")
    public String deleteAdres_sotr(@PathVariable(value = "id") int id, Model model) {
        adres_sotrService.deleteAdres(id);
        return table("Adres_sotr", model);
    }

    @PostMapping("crud/table/addRow/Dolzgnost")
    public String addRowDolzgnost(@RequestParam String nazvanie, Model model) {
        Dolzgnost dolzgnost = new Dolzgnost(nazvanie);
        dolzgnostService.save(dolzgnost);
        return table("Dolzgnost", model);
    }

    @PostMapping("crud/table/changeRow/Dolzgnost/{id}")
    public String changeDolzgnost(@PathVariable(value = "id") int id,
                                   @RequestParam String nazvanie, Model model) {
        Dolzgnost dolzgnost = dolzgnostService.findById(id);
        dolzgnost.setNazvanie(nazvanie);
        dolzgnostService.save(dolzgnost);
        return table("Dolzgnost", model);
    }

    @GetMapping("crud/table/deleteDolzgnost/{id}")
    public String deleteDolzgnost(@PathVariable(value = "id") int id, Model model) {
        dolzgnostService.deleteDolzgnost(id);
        return table("Dolzgnost", model);
    }

    @PostMapping("crud/table/addRow/Kat_match")
    public String addRowKat_match(@RequestParam String nazvanie, Model model) {
        Kat_match kat_match = new Kat_match(nazvanie);
        katMatchService.save(kat_match);
        return table("Kat_match", model);
    }

    @PostMapping("crud/table/changeRow/Kat_match/{id}")
    public String changeKat_match(@PathVariable(value = "id") int id,
                                  @RequestParam String nazvanie, Model model) {
        Kat_match kat_match = katMatchService.findById(id);
        kat_match.setNazvanie(nazvanie);
        katMatchService.save(kat_match);
        return table("Kat_match", model);
    }

    @GetMapping("crud/table/deleteKat_match/{id}")
    public String deleteKat_match(@PathVariable(value = "id") int id, Model model) {
        katMatchService.deleteCategory(id);
        return table("Kat_match", model);
    }

    @PostMapping("crud/table/addRow/Otdel")
    public String addRowOtdel(@RequestParam String nazvanie, @RequestParam String tel, Model model) {
        Otdel otdel = new Otdel(nazvanie, Integer.parseInt(tel));
        otdelService.save(otdel);
        return table("Otdel", model);
    }

    @PostMapping("crud/table/changeRow/Otdel/{id}")
    public String changeOtdel(@PathVariable(value = "id") int id,
                                  @RequestParam String nazvanie, @RequestParam String tel, Model model) {
        Otdel otdel = otdelService.findById(id);
        otdel.setNazvanie(nazvanie);
        otdel.setTel(Integer.parseInt(tel));
        otdelService.save(otdel);
        return table("Otdel", model);
    }

    @GetMapping("crud/table/deleteOtdel/{id}")
    public String deleteOtdel(@PathVariable(value = "id") int id, Model model) {
        otdelService.deleteOtdel(id);
        return table("Otdel", model);
    }

    @PostMapping("crud/table/addRow/Zarplata")
    public String addRowZarplata(@RequestParam String zarplata, Model model) {
        Zarplata zarp = new Zarplata(Integer.parseInt(zarplata));
        zarplataService.save(zarp);
        return table("Zarplata", model);
    }

    @PostMapping("crud/table/changeRow/Zarplata/{id}")
    public String changeZarplata(@PathVariable(value = "id") int id,
                                  @RequestParam String zarplata, Model model) {
        Zarplata zarp = zarplataService.findById(id);
        zarp.setZarplata(Integer.parseInt(zarplata));
        zarplataService.save(zarp);
        return table("Zarplata", model);
    }

    @GetMapping("crud/table/deleteZarplata/{id}")
    public String deleteZarplata(@PathVariable(value = "id") int id, Model model) {
        zarplataService.deleteZarplata(id);
        return table("Zarplata", model);
    }

    @PostMapping("crud/table/addRow/Matches")
    public String addRowMatches(@RequestParam String id_team1,
                              @RequestParam String id_team2,
                              @RequestParam LocalDateTime match_date,
                              @RequestParam String kat_match,
                              @RequestParam String championship,
                              @RequestParam String id_status,
                              @RequestParam String count_team1,
                              @RequestParam String count_team2, Model model) {
        double koef1, koef2;
        Team team1 = teamService.findById(Integer.parseInt(id_team1));
        Team team2 = teamService.findById(Integer.parseInt(id_team2));
        if (team1.getRating() < team2.getRating()) {
            double razn = team2.getRating() * 1.0 / team1.getRating();
            double procWinMin = 100.0 / (razn + 1);
            double procWinMax = 100 - procWinMin;
            koef1 = 100.0 / procWinMin;
            koef2 = 100.0 / procWinMax;
        } else {
            double razn = team1.getRating() * 1.0 / team2.getRating();
            double procWinMin = 100.0 / (razn + 1);
            double procWinMax = 100 - procWinMin;
            koef1 = 100.0 / procWinMax;
            koef2 = 100.0 / procWinMin;
        }
        koef1 *= 100;
        koef1 = Math.round(koef1);
        koef1 /= 100.0;

        koef2 *= 100;
        koef2 = Math.round(koef2);
        koef2 /= 100.0;
        // 90 30 => x + 3x = 100 => x = 25 => 1.33 4
        // 90 85 = x + 1.06x = 2.06x = 100 => 48.54 = x => 1.94 2.06
        Matches matches = new Matches(match_date, koef1, koef2, Integer.parseInt(kat_match), Integer.parseInt(championship), Integer.parseInt(id_team1), Integer.parseInt(id_team2), Integer.parseInt(id_status), Integer.parseInt(count_team1), Integer.parseInt(count_team2));
        matchService.save(matches);
        return table("Matches", model);
    }

    @PostMapping("crud/table/changeRow/Matches/{id}")
    public String changeMatches(@PathVariable(value = "id") int id,
                                   @RequestParam String id_team1,
                                   @RequestParam String id_team2,
                                   @RequestParam LocalDateTime match_date,
                                   @RequestParam String koef1,
                                   @RequestParam String koef2,
                                   @RequestParam String kat_match,
                                   @RequestParam String championship,
                                   @RequestParam String id_status,
                                   @RequestParam String count_team1,
                                   @RequestParam String count_team2, Model model) {
        Matches matches = matchService.findById(id);
        matches.setId_team1(Integer.parseInt(id_team1));
        matches.setId_team2(Integer.parseInt(id_team2));
        matches.setMatch_date(match_date);
        matches.setKoef1(Double.parseDouble(koef1));
        matches.setKoef2(Double.parseDouble(koef2));
        matches.setKat_match(Integer.parseInt(kat_match));
        matches.setChampionship(Integer.parseInt(championship));
        matches.setId_status(Integer.parseInt(id_status));
        matches.setCount_team1(Integer.parseInt(count_team1));
        matches.setCount_team2(Integer.parseInt(count_team2));
        matchService.save(matches);
        return table("Matches", model);
    }

    @GetMapping("crud/table/deleteMatches/{id}")
    public String deleteMatches(@PathVariable(value = "id") int id, Model model) {
        matchService.deleteMatch(id);
        return table("Matches", model);
    }

    @PostMapping("crud/table/addRow/Stavka")
    public String addRowStavka(@RequestParam String summa,
                                @RequestParam String koef,
                                @RequestParam LocalDateTime date,
                                @RequestParam String id_client,
                                @RequestParam String id_match,
                                @RequestParam String id_bet_team,
                                @RequestParam String id_status, Model model) {
        Stavka stavka = new Stavka(Integer.parseInt(summa), Double.parseDouble(koef), date, Integer.parseInt(id_client), Integer.parseInt(id_match), Integer.parseInt(id_status), Integer.parseInt(id_bet_team));
        stavkaService.save(stavka);
        return table("Stavka", model);
    }

    @PostMapping("crud/table/changeRow/Stavka/{id}")
    public String changeStavka(@PathVariable(value = "id") int id,
                                @RequestParam String summa,
                                @RequestParam String koef,
                                @RequestParam LocalDateTime date,
                                @RequestParam String id_client,
                                @RequestParam String id_match,
                                @RequestParam String id_bet_team,
                                @RequestParam String id_status, Model model) {
        Stavka stavka = stavkaService.findById(id);
        stavka.setSumma(Integer.parseInt(summa));
        stavka.setKoef(Double.parseDouble(koef));
        stavka.setDate(date);
        stavka.setId_client(Integer.parseInt(id_client));
        stavka.setId_match(Integer.parseInt(id_match));
        stavka.setId_bet_team(Integer.parseInt(id_bet_team));
        stavka.setId_status(Integer.parseInt(id_status));
        stavkaService.save(stavka);
        return table("Stavka", model);
    }

    @GetMapping("crud/table/deleteStavka/{id}")
    public String deleteStavka(@PathVariable(value = "id") int id, Model model) {
        stavkaService.deleteStavka(id);
        return table("Stavka", model);
    }

    @PostMapping("crud/table/addRow/Viplata")
    public String addRowViplata(@RequestParam String summa,
                               @RequestParam LocalDateTime date,
                               @RequestParam String id_stavka,
                               @RequestParam String id_client, Model model) {
        Viplata viplata = new Viplata(Integer.parseInt(summa), date, Integer.parseInt(id_stavka), Integer.parseInt(id_client));
        viplataService.save(viplata);
        return table("Viplata", model);
    }

    @PostMapping("crud/table/changeRow/Viplata/{id}")
    public String changeViplata(@PathVariable(value = "id") int id,
                               @RequestParam String summa,
                               @RequestParam LocalDateTime date,
                               @RequestParam String id_stavka,
                               @RequestParam String id_client, Model model) {
        Viplata viplata = viplataService.findById(id);
        viplata.setSumma(Integer.parseInt(summa));
        viplata.setDate(date);
        viplata.setId_stavka(Integer.parseInt(id_stavka));
        viplata.setId_client(Integer.parseInt(id_client));
        viplataService.save(viplata);
        return table("Viplata", model);
    }

    @GetMapping("crud/table/deleteViplata/{id}")
    public String deleteViplata(@PathVariable(value = "id") int id, Model model) {
        viplataService.deleteViplata(id);
        return table("Viplata", model);
    }
}
