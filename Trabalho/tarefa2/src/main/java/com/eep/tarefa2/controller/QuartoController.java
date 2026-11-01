package com.eep.tarefa2.controller;

import com.eep.tarefa2.domain.Quarto;
import com.eep.tarefa2.service.QuartoService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quartos")
public class QuartoController {

    private final QuartoService quartoService;

    public QuartoController(QuartoService quartoService) {
        this.quartoService = quartoService;
    }

    // GET /api/quartos            -> todos
    // GET /api/quartos?hotelId=1  -> apenas os quartos do hotel 1
    @GetMapping
    public List<Quarto> listar(@RequestParam(required = false) Integer hotelId) {
        return hotelId == null ? quartoService.listar() : quartoService.listarPorHotel(hotelId);
    }

    @GetMapping("/{id}")
    public Quarto buscar(@PathVariable Integer id) {
        return quartoService.buscar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Quarto inserir(@Valid @RequestBody Quarto quarto) {
        return quartoService.inserir(quarto);
    }

    @PutMapping("/{id}")
    public Quarto atualizar(@PathVariable Integer id, @Valid @RequestBody Quarto quarto) {
        return quartoService.atualizar(id, quarto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Integer id) {
        quartoService.excluir(id);
    }
}
