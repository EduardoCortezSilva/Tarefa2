package springboot.crud.exercicio.service;

import java.util.List;
import org.springframework.stereotype.Service;
import springboot.crud.exercicio.domain.Departamento;
import springboot.crud.exercicio.repository.DepartamentoRepository;

@Service
public class DepartamentoService {
    
    private final DepartamentoRepository departamentoRepository;
    
    public DepartamentoService(DepartamentoRepository departamentoRepository) {
        this.departamentoRepository = departamentoRepository;
    }
    
    public Departamento getDepartamento(Integer id) {
        return departamentoRepository.finDepartamentoById(id);
    }
    
    public List<Departamento> listDespartamentos() {
        return departamentoRepository.findAll();
    }
    
    public void removerDepartamento(Integer id) {
        departamentoRepository.deleteById(id);
    }

    public void alterarDepartamento(Departamento departamento) {
        departamentoRepository.save(departamento);
    }

    public void inserirDepartamento(Departamento departamento) {
        departamentoRepository.save(departamento);
    }
}
