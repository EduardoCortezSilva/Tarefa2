package springboot.crud.exercicio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String index() {
        return "listarDepartamentos";
    }

    @GetMapping("/departamentos")
    public String listdep() {
        return "listarDepartamentos";
    }

    @GetMapping("/alterar-departamento")
    public String alterardep() {
        return "alterarDepartamento";
    }

    @GetMapping("/inserir-departamento")
    public String inserirdep() {
        return "inserirDepartamento";
    }

    @GetMapping("/funcionarios")
    public String listfun() {
        return "listarFuncionarios";
    }

    @GetMapping("/alterar-funcionario")
    public String alterarfun() {
        return "alterarFuncionario";
    }

    @GetMapping("/inserir-funcionario")
    public String inserirfun() {
        return "inserirFuncionario";
    }
}
