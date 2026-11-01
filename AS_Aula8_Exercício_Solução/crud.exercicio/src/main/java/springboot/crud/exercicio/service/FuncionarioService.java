package springboot.crud.exercicio.service;

import java.util.List;
import org.springframework.stereotype.Service;
import springboot.crud.exercicio.domain.Funcionario;
import springboot.crud.exercicio.repository.FuncionarioRepository;

@Service
public class FuncionarioService {
    
    private final FuncionarioRepository funcionarioRepository;
    
    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }
    
    public Funcionario getFuncionario(Integer id) {
        return funcionarioRepository.findFuncionarioById(id);
    }
    
    public List<Funcionario> listFuncionario() {
        return funcionarioRepository.findAll();
    }
    
    public void removerFuncionario(Integer id) {
        funcionarioRepository.deleteById(id);
    }

    public void alterarFuncionario(Funcionario funcionario) {
        funcionarioRepository.save(funcionario);
    }

    public void inserirFuncionario(Funcionario funcionario) {
        funcionarioRepository.save(funcionario);
    }
}
