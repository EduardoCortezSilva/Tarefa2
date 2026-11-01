package springboot.crud.exercicio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import springboot.crud.exercicio.domain.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Object>{
    
    @Query("select f from Funcionario f where f.idFuncionario = ?1")
    public Funcionario findFuncionarioById(Integer id);
}
