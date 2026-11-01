package springboot.crud.produto.controller;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springboot.crud.produto.domain.Produto;
import springboot.crud.produto.service.ProdutoService;
import tools.jackson.databind.ObjectMapper;

@RestController
public class ProdutoController {

    private ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping(path = "/produto/{id}")
    public Produto getProduto(@PathVariable("id") Integer id) {
        return produtoService.getProduto(id);
    }

    @GetMapping(path = "/produtos")
    public List<Produto> listProdutos() {
        return produtoService.listProdutos();
    }

    @GetMapping(path = "/remover/{id}")
    public void removerProduto(HttpServletResponse response, @PathVariable("id") Integer id) throws IOException {
        produtoService.removerProduto(id);
        response.sendRedirect("/");
    }

    @PostMapping(path = "/alterarProduto", consumes = {"application/json", "application/x-www-form-urlencoded"})
    public void alterarProduto(@RequestBody String dados) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Produto produto = mapper.readValue(dados, Produto.class);
        produtoService.alterarProduto(produto);
    }

    @PostMapping(path = "/inserirProduto", consumes = {"application/json", "application/x-www-form-urlencoded"})
    public void inserirProduto(@RequestBody String dados) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Produto produto = mapper.readValue(dados, Produto.class);
        produtoService.inserirProduto(produto);
    }
}
