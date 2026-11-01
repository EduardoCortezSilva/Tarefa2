package com.eep.tarefa2.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "quarto")
public class Quarto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // lado "N" da relação 1:N
    @NotNull(message = "O hotel é obrigatório")
    @ManyToOne(optional = false)
    @JoinColumn(name = "hotel_id", nullable = false)
    @JsonIgnoreProperties({"quartos"})
    private Hotel hotel;

    @NotBlank(message = "O número é obrigatório")
    @Size(max = 20, message = "O número deve ter no máximo 20 caracteres")
    @Column(nullable = false, length = 20)
    private String numero;

    @NotBlank(message = "O tipo é obrigatório")
    @Size(max = 100, message = "O tipo deve ter no máximo 100 caracteres")
    @Column(nullable = false, length = 100)
    private String tipo;

    @NotNull(message = "A diária é obrigatória")
    @DecimalMin(value = "0.00", message = "A diária não pode ser negativa")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal diaria;

    public Quarto() {
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Hotel getHotel() { return hotel; }
    public void setHotel(Hotel hotel) { this.hotel = hotel; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public BigDecimal getDiaria() { return diaria; }
    public void setDiaria(BigDecimal diaria) { this.diaria = diaria; }
}
