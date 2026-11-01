package springboot.crud.exercicio.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "departamento")

public class Departamento implements Serializable {
    
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer idDepartamento;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "departamento")
    private List<Funcionario> funcionarios;
    
    @Column(name = "nome", nullable = false)
    private String nomeDepartamento;
    @Column(name = "local", nullable = false)
    private String localDepartamento;
    @Column(name = "descricao", nullable = false)
    private String descricaoDepartamento;
}
