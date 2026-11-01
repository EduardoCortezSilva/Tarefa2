package springboot.crud.exercicio.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "funcionario")

public class Funcionario implements Serializable {
    
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer idFuncionario;
    @Column(name = "nome", nullable = false)
    private String nomeFuncionario;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departamento_id")
    @JsonIgnore
    private Departamento departamento;
    
    @Column(name = "funcao", nullable = false)
    private String funcaoFuncionario;
    @Column(name = "registro", nullable = false)
    private String registroFuncionario;
}
