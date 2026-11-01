package com.eep.tarefa2.service;

import com.eep.tarefa2.domain.Hotel;
import com.eep.tarefa2.domain.Quarto;
import com.eep.tarefa2.repository.HotelRepository;
import com.eep.tarefa2.repository.QuartoRepository;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QuartoService {

    private final QuartoRepository quartoRepository;
    private final HotelRepository hotelRepository;

    public QuartoService(QuartoRepository quartoRepository, HotelRepository hotelRepository) {
        this.quartoRepository = quartoRepository;
        this.hotelRepository = hotelRepository;
    }

    public List<Quarto> listar() {
        return quartoRepository.findAll(Sort.by("hotel.nome", "numero"));
    }

    public List<Quarto> listarPorHotel(Integer hotelId) {
        return quartoRepository.findByHotelIdOrderByNumero(hotelId);
    }

    public Quarto buscar(Integer id) {
        return quartoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Quarto não encontrado"));
    }

    @Transactional
    public Quarto inserir(Quarto quarto) {
        quarto.setId(null);
        quarto.setHotel(resolverHotel(quarto));
        return quartoRepository.save(quarto);
    }

    @Transactional
    public Quarto atualizar(Integer id, Quarto dados) {
        Quarto quarto = buscar(id);
        quarto.setHotel(resolverHotel(dados));
        quarto.setNumero(dados.getNumero());
        quarto.setTipo(dados.getTipo());
        quarto.setDiaria(dados.getDiaria());
        return quartoRepository.save(quarto);
    }

    @Transactional
    public void excluir(Integer id) {
        quartoRepository.delete(buscar(id));
    }

    private Hotel resolverHotel(Quarto quarto) {
        if (quarto.getHotel() == null || quarto.getHotel().getId() == null) {
            throw new IllegalArgumentException("Informe o hotel do quarto");
        }
        return hotelRepository.findById(quarto.getHotel().getId())
                .orElseThrow(() -> new IllegalArgumentException("Hotel informado não existe"));
    }
}
