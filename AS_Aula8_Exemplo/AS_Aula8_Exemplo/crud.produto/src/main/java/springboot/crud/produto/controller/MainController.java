package springboot.crud.produto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String list() {
        return "listarProdutos";
    }
    
    @GetMapping("/alterar")
    public String alterar() {
        return "alterarProduto";
    }

    @GetMapping("/inserir")
    public String inserir() {
        return "inserirProduto";
    }
}
