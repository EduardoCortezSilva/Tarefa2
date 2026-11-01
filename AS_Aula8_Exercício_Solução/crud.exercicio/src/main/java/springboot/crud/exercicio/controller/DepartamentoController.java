package springboot.crud.exercicio.controller;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springboot.crud.exercicio.domain.Departamento;
import springboot.crud.exercicio.service.DepartamentoService;
import tools.jackson.databind.ObjectMapper;

@RestController
public class DepartamentoController {
    
    private DepartamentoService departamentoService;
    
    public DepartamentoController(DepartamentoService departamentoService) {
        this.departamentoService = departamentoService;
    }
    
    @GetMapping(path = "/departamento/{id}")
    public Departamento getDepartamento(@PathVariable("id") Integer id) {
        return departamentoService.getDepartamento(id);
    }
    
    @GetMapping(path = "/departamentos-raw")
    public List<Departamento> listDespartamentos() {
        return departamentoService.listDespartamentos();
    }
    
    @GetMapping(path = "/remover-departamento/{id}")
    public void removerDepartamento(HttpServletResponse response, @PathVariable("id") Integer id) throws IOException {
        departamentoService.removerDepartamento(id);
        response.sendRedirect("/departamentos");
    }
    
    @PostMapping(path = "/alterarDepartamento", consumes = {"application/json", "application/x-www-form-urlencoded"})
    public void alterarDepartamento(@RequestBody String dados) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Departamento departamento = mapper.readValue(dados, Departamento.class);
        departamentoService.alterarDepartamento(departamento);
    }

    @PostMapping(path = "/inserirDepartamento", consumes = {"application/json", "application/x-www-form-urlencoded"})
    public void inserirDepartamento(@RequestBody String dados) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Departamento departamento = mapper.readValue(dados, Departamento.class);
        departamentoService.inserirDepartamento(departamento);
    }
}
