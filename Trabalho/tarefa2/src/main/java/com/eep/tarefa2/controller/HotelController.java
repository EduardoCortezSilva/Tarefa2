package com.eep.tarefa2.controller;

import com.eep.tarefa2.domain.Hotel;
import com.eep.tarefa2.domain.Quarto;
import com.eep.tarefa2.service.HotelService;
import com.eep.tarefa2.service.QuartoService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hoteis")
public class HotelController {

    private final HotelService hotelService;
    private final QuartoService quartoService;

    public HotelController(HotelService hotelService, QuartoService quartoService) {
        this.hotelService = hotelService;
        this.quartoService = quartoService;
    }

    @GetMapping
    public List<Hotel> listar() {
        return hotelService.listar();
    }

    @GetMapping("/{id}")
    public Hotel buscar(@PathVariable Integer id) {
        return hotelService.buscar(id);
    }

    @GetMapping("/{id}/quartos")
    public List<Quarto> listarQuartos(@PathVariable Integer id) {
        hotelService.buscar(id); // garante 404 se o hotel não existir
        return quartoService.listarPorHotel(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Hotel inserir(@Valid @RequestBody Hotel hotel) {
        return hotelService.inserir(hotel);
    }

    @PutMapping("/{id}")
    public Hotel atualizar(@PathVariable Integer id, @Valid @RequestBody Hotel hotel) {
        return hotelService.atualizar(id, hotel);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Integer id) {
        hotelService.excluir(id);
    }
}
