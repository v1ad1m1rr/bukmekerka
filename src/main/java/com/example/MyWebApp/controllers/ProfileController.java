package com.example.MyWebApp.controllers;

import com.example.MyWebApp.models.Client;
import com.example.MyWebApp.models.Sotrudnik;
import com.example.MyWebApp.services.ClientService;
import com.example.MyWebApp.services.SotrudnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.util.Base64;

@Controller
public class ProfileController {
    @Autowired
    ClientService clientService;
    @Autowired
    SotrudnikService sotrudnikService;

    @GetMapping("/profile")
    public String prof(Principal principal, Model model) {
        Client client = clientService.findByUsername(principal.getName());
        Sotrudnik sotrudnik = sotrudnikService.findByUsername(principal.getName());
        String blolBase;
        if (sotrudnik == null) {
            model.addAttribute("role", "user");
            model.addAttribute("secondName", client.getSecondName());
            model.addAttribute("firstName", client.getFirstName());
            model.addAttribute("otchestvo", client.getOtchestvo());
            model.addAttribute("birthDate", client.getBirthDate());
            model.addAttribute("registrDate", client.getRegistrDate());
            model.addAttribute("cartNum", client.getNomerCarti());
            model.addAttribute("login", client.getEmail());
            if (client.getAvatar() != null) {
                blolBase = Base64.getEncoder().encodeToString(client.getAvatar());
                model.addAttribute("avatar", blolBase);
            } else {
                model.addAttribute("logo", "hasNoImage");
            }

        } else {
            model.addAttribute("role", "admin");
            model.addAttribute("secondName", sotrudnik.getSecondName());
            model.addAttribute("firstName", sotrudnik.getFirstName());
            model.addAttribute("otchestvo", sotrudnik.getOtchestvo());
            model.addAttribute("tel", sotrudnik.getTel());
            model.addAttribute("birthDate", sotrudnik.getBirthDate());
            model.addAttribute("hireDate", sotrudnik.getHireDate());
            model.addAttribute("login", sotrudnik.getEmail());
            if (sotrudnik.getAvatar() != null) {
                blolBase = Base64.getEncoder().encodeToString(sotrudnik.getAvatar());
                model.addAttribute("avatar", blolBase);
            } else {
                model.addAttribute("logo", "hasNoImage");
            }
        }
        return "profile";
    }

    @PostMapping("/profile/Client")
    public String postProf(Principal principal,
                           @RequestParam String secondName,
                           @RequestParam String firstName,
                           @RequestParam String otchestvo,
                           @RequestParam LocalDate birthDate,
                           @RequestParam String cartNum,
                           @RequestParam String login,
                           @RequestParam("ava") MultipartFile multipartFile, Model model) throws IOException {
        Client client = clientService.findByUsername(principal.getName());
        client.setSecondName(secondName);
        client.setFirstName(firstName);
        client.setOtchestvo(otchestvo);
        client.setBirthDate(birthDate);
        client.setNomerCarti(Integer.parseInt(cartNum));
        client.setEmail(login);
        client.setAvatar(multipartFile.getBytes());
        clientService.updateClientWithoutPass(client);
        return "redirect:/profile";
    }

    @PostMapping("/profile/Sotrudnik")
    public String postProf(Principal principal,
                           @RequestParam String secondName,
                           @RequestParam String firstName,
                           @RequestParam String otchestvo,
                           @RequestParam String tel,
                           @RequestParam LocalDate birthDate,
                           @RequestParam String login,
                           @RequestParam("ava") MultipartFile multipartFile, Model model) throws IOException {
        Sotrudnik sotrudnik = sotrudnikService.findByUsername(principal.getName());
        sotrudnik.setSecondName(secondName);
        sotrudnik.setFirstName(firstName);
        sotrudnik.setOtchestvo(otchestvo);
        sotrudnik.setTel(Integer.parseInt(tel));
        sotrudnik.setBirthDate(birthDate);
        sotrudnik.setEmail(login);
        sotrudnik.setAvatar(multipartFile.getBytes());
        sotrudnikService.updateSotrWithoutPass(sotrudnik);
        return "redirect:/profile";
    }

    @GetMapping("/profile/changePass")
    public String changePass() {
        return "changePass";
    }

    @PostMapping("/profile/changePass")
    public String ChangePassword(Principal principal, @RequestParam String newPass,
                                 @RequestParam String confirmPass, Model model) {
        if (!newPass.equals(confirmPass)) {
            model.addAttribute("error", "Пароли не сопадают");
            return "changePass";
        }
        Client client = clientService.findByUsername(principal.getName());
        Sotrudnik sotrudnik = sotrudnikService.findByUsername(principal.getName());
        if (client == null) {
            sotrudnik.setPassword(newPass);
            sotrudnikService.updateSotr(sotrudnik);
        } else {
            client.setPassword(newPass);
            clientService.updateClient(client);
        }
        return "redirect:/";
    }
}
