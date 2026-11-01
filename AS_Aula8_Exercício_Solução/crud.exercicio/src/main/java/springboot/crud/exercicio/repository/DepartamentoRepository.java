package springboot.crud.exercicio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import springboot.crud.exercicio.domain.Departamento;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Integer>{
    
    @Query("select d from Departamento d where d.idDepartamento = ?1")
    public Departamento finDepartamentoById(Integer id);
}
