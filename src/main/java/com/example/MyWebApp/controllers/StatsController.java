package com.example.MyWebApp.controllers;

import com.example.MyWebApp.services.ClientService;
import com.example.MyWebApp.services.SotrudnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class StatsController {
    @Autowired
    ClientService clientService;
    @Autowired
    SotrudnikService sotrudnikService;

    @GetMapping("/statsPlayer")
    public String stat(Principal principal, Model model) {
        List<String> list = clientService.histStavkaAndViplata(clientService.findByUsername(principal.getName()).getId());
        List<String[]> listSplitItem = new ArrayList<>();
        for (String item : list) {
            String[] splitItem = item.split(",");
            listSplitItem.add(splitItem);
        }
        model.addAttribute("list", listSplitItem);

        List<String> list2 = clientService.sumStavkaViplata(clientService.findByUsername(principal.getName()).getId());
        String[] splitSums = list2.getFirst().split(",");
        int sumStavka = Integer.parseInt(splitSums[0]);
        int sumViplata = Integer.parseInt(splitSums[1]);
        model.addAttribute("sumStavka", sumStavka);
        model.addAttribute("sumViplata", sumViplata);

        List<String> list3 = clientService.countAnyStavok(clientService.findByUsername(principal.getName()).getId());
        int allStavok = Integer.parseInt(list3.getFirst());
        int successStavok = Integer.parseInt(list3.get(1));
        int losesStavok = Integer.parseInt(list3.get(2));
        int inProcessStavok = Integer.parseInt(list3.get(3));
        model.addAttribute("allStavok", allStavok);
        model.addAttribute("successStavok", successStavok);
        model.addAttribute("losesStavok", losesStavok);
        model.addAttribute("inProcessStavok", inProcessStavok);

        return "statsPlayer";
    }

    @GetMapping("/statsAll")
    public String statsAll(Model model) {
        return "statsAll";
    }

    @PostMapping("/statsAll")
    public String statsAllDate(@RequestParam LocalDate date, Model model) {
        List<String> list = sotrudnikService.clientsAndStavki(date.getDayOfMonth(), date.getMonthValue(), date.getYear());
        List<String[]> listSplitItem = new ArrayList<>();
        for (String item : list) {
            String[] splitItem = item.split(",");
            listSplitItem.add(splitItem);
        }
        model.addAttribute("list", listSplitItem);

        List<String> list2 = sotrudnikService.stavkiViplati();
        String[] splitSums = list2.getFirst().split(",");
        int sumStavka = Integer.parseInt(splitSums[0]);
        int sumViplata = Integer.parseInt(splitSums[1]);
        model.addAttribute("sumStavka", sumStavka);
        model.addAttribute("sumViplata", sumViplata);

        List<String> list3 = sotrudnikService.countSucAndLos();
        int allStavok = Integer.parseInt(list3.getFirst());
        int successStavok = Integer.parseInt(list3.get(1));
        int losesStavok = Integer.parseInt(list3.get(2));
        int inProcessStavok = Integer.parseInt(list3.get(3));
        model.addAttribute("allStavok", allStavok);
        model.addAttribute("successStavok", successStavok);
        model.addAttribute("losesStavok", losesStavok);
        model.addAttribute("inProcessStavok", inProcessStavok);

        List<String> list4 = sotrudnikService.StavVipAtMonth();
        int[] stavVipAtMonth = new int[24];
        int index = 0;
        for (String item : list4) {
            String[] splitItem = item.split(",");
            stavVipAtMonth[index] = Integer.parseInt(splitItem[1]);
            stavVipAtMonth[index + 1] = Integer.parseInt(splitItem[2]);
            index += 2;
        }
        model.addAttribute("stavVipAtMonth", stavVipAtMonth);

        List<String> list5 = sotrudnikService.bestKatMatch();
        String[] res5 = new String[6];
        for (int i = 0; i < 3; i++) {
            res5[i + i] = list5.get(i).split(",")[0];
            res5[i + i + 1] = list5.get(i).split(",")[1];
        }
        model.addAttribute("kats", res5);

        model.addAttribute("isFirst", "false");
        return "statsAll";
    }
}
