package com.kaos.escola.controllers;

import com.kaos.escola.models.Administradores;
import com.kaos.escola.repositories.AdminRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private AdminRepository adminRepo;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/cadastro")
    public String cadastro(){
        return "admin/cadastro";
    }

    @PostMapping("/cadastrar")
    public String cadastrar(Administradores dados){
        adminRepo.save(dados);
        return "redirect:/";
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

    @GetMapping("/admin")
    public String administradores(Model model){
        List<Administradores> lista = adminRepo.admins();
        model.addAttribute("lista", lista);
        return "admin/lista";
    }

    @GetMapping("admin/editar/{id}")
    public String editarAdmin(@PathVariable("id") Long id, Model model){
        Administradores dados = adminRepo.buscar(id);
        model.addAttribute("dados", dados);
        return "admin/editar";
    }

    @PostMapping("admin/editar/{id}")
    public String salvarAdmin(@PathVariable("id") Long id, Administradores dados){
        adminRepo.editar(id, dados.getNome(), dados.getSenha());
        return "redirect:/admin";
    }

    @PostMapping("admin/excluir/{id}")
    public String excluirAdmin(@PathVariable("id") Long id){
        adminRepo.excluir(id);
        return "redirect:/admin";
    }


}

