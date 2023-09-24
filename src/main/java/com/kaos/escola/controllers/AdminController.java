package com.kaos.escola.controllers;

import com.kaos.escola.models.Administradores;
import com.kaos.escola.repositories.AdminRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

    @Autowired
    private AdminRepository adminRepo;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @PostMapping("/login")
    public String loginAdmin(Administradores dados, Model model, HttpSession session){
        Administradores login = adminRepo.login(dados.getNome(), dados.getSenha());

        if (login != null) {
            session.setAttribute("logado", dados);
            return "redirect:/admin";
        } else {
            model.addAttribute("erro", "Usuário ou Senha Inválidos");
            return "index";
        }

    }
}
