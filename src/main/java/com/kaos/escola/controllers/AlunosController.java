package com.kaos.escola.controllers;

import com.kaos.escola.models.Alunos;
import com.kaos.escola.repositories.AlunosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class AlunosController {

    @Autowired
    private AlunosRepository alunosRepo;

    @GetMapping("/alunos")
    public String index(){
        return "listadealuno";
    }

    @PostMapping("/cadastro")
    public String cadastrarAlunos(Alunos dados){
        alunosRepo.save(dados);
        return "redirect:/alunos";
    }

    @PutMapping("/atualizar")
    public String atualizarAlunos(Alunos dados, RedirectAttributes redirectAttributes){
        Optional<Alunos> alunoistrue = alunosRepo.findById(dados.getId());

        if(alunoistrue.isPresent()){
            Alunos alunos = alunoistrue.get();
            alunos.setNome(dados.getNome());
            alunos.setIdade(dados.getIdade());
            alunos.setTurma(dados.getTurma());
            alunos.setTurno(dados.getTurno());
            alunosRepo.save(dados);
            return "redirect:/alunos";
        }else{
            redirectAttributes.addAttribute("erro!", "Alguma informação está incorreta!");
            return "redirect:/alunos";
        }

    }

    @DeleteMapping("{id}")
    public String apagarAlunos(@PathVariable Long id, RedirectAttributes redirectAttributes){
        Optional<Alunos> alunoistrue = alunosRepo.findById(id);
        if(alunoistrue.isPresent()){
            alunosRepo.deleteById(id);
            return "redirect:/alunos";
        }else{
            redirectAttributes.addAttribute("erro!", "O aluno com o ID " + id + " não foi encontrado.");
            return "redirect:/alunos";
        }
    }

}
