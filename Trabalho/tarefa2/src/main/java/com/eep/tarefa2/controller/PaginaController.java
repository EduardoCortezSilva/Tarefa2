package com.eep.tarefa2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Rotas de navegação entre as páginas HTML (pasta resources/static).
 */
@Controller
public class PaginaController {

    @GetMapping("/")
    public String inicio() {
        return "forward:/index.html";
    }

    @GetMapping("/hoteis")
    public String hoteis() {
        return "forward:/hoteis.html";
    }

    @GetMapping("/quartos")
    public String quartos() {
        return "forward:/quartos.html";
    }
}
