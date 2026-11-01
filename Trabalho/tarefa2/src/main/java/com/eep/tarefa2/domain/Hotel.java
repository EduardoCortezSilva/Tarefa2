package com.eep.tarefa2.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hotel")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "O nome é obrigatório")
    @Size(max = 255, message = "O nome deve ter no máximo 255 caracteres")
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "O endereço é obrigatório")
    @Size(max = 500, message = "O endereço deve ter no máximo 500 caracteres")
    @Column(nullable = false, length = 500)
    private String endereco;

    // coluna opcional (NULL permitido)
    private Integer categoria;

    // lado "1" da relação 1:N
    @JsonIgnore
    @OneToMany(mappedBy = "hotel")
    private List<Quarto> quartos = new ArrayList<>();

    public Hotel() {
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public Integer getCategoria() { return categoria; }
    public void setCategoria(Integer categoria) { this.categoria = categoria; }

    public List<Quarto> getQuartos() { return quartos; }
    public void setQuartos(List<Quarto> quartos) { this.quartos = quartos; }
}
