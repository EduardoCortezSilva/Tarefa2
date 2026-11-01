package springboot.crud.exercicio.controller;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springboot.crud.exercicio.domain.Departamento;
import springboot.crud.exercicio.domain.Funcionario;
import springboot.crud.exercicio.service.FuncionarioService;
import tools.jackson.databind.ObjectMapper;

@RestController
public class FuncionarioController {

    private FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @GetMapping(path = "/funcionario/{id}")
    public Funcionario getFuncionario(@PathVariable("id") Integer id) {
        return funcionarioService.getFuncionario(id);
    }

    @GetMapping(path = "/funcionarios-raw", produces = "application/json")
    public String listFuncionarios() {
        List<Funcionario> funcionarios = funcionarioService.listFuncionario();
        ObjectMapper mapper = new ObjectMapper();
        JSONArray array = new JSONArray();
        for (Funcionario funcionario : funcionarios) {
            try {
                JSONObject obj = new JSONObject(mapper.writeValueAsString(funcionario));
                obj.put("departamento", funcionario.getDepartamento().getNomeDepartamento());
                array.put(obj);
            } catch (JSONException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return array.toString();
    }

    @GetMapping(path = "/remover-funcionario/{id}")
    public void removerFuncionario(HttpServletResponse response, @PathVariable("id") Integer id) throws IOException {
        funcionarioService.removerFuncionario(id);
        response.sendRedirect("/funcionarios");
    }

    @PostMapping(path = "/alterarFuncionario", consumes = {"application/json", "application/x-www-form-urlencoded"})
    public void alterarFuncionario(@RequestBody String dados) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Funcionario funcionario = mapper.readValue(dados, Funcionario.class);
        funcionarioService.alterarFuncionario(funcionario);
    }

    @PostMapping(path = "/inserirFuncionario", consumes = {"application/json", "application/x-www-form-urlencoded"})
    public void inserirFuncionario(@RequestBody String dados) throws IOException {
        JSONObject obj = new JSONObject(dados);
        Departamento departamento = new Departamento();
        departamento.setIdDepartamento(Integer.valueOf(obj.get("departamento").toString()));
        ObjectMapper mapper = new ObjectMapper();
        Funcionario funcionario = mapper.readValue(dados, Funcionario.class);
        funcionario.setDepartamento(departamento);
        funcionarioService.inserirFuncionario(funcionario);
    }
}
