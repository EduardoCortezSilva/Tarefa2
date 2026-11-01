package com.eep.tarefa2.service;

import com.eep.tarefa2.domain.Hotel;
import com.eep.tarefa2.repository.HotelRepository;
import com.eep.tarefa2.repository.QuartoRepository;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;
    private final QuartoRepository quartoRepository;

    public HotelService(HotelRepository hotelRepository, QuartoRepository quartoRepository) {
        this.hotelRepository = hotelRepository;
        this.quartoRepository = quartoRepository;
    }

    public List<Hotel> listar() {
        return hotelRepository.findAll(Sort.by("nome"));
    }

    public Hotel buscar(Integer id) {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Hotel não encontrado"));
    }

    @Transactional
    public Hotel inserir(Hotel hotel) {
        hotel.setId(null);
        return hotelRepository.save(hotel);
    }

    @Transactional
    public Hotel atualizar(Integer id, Hotel dados) {
        Hotel hotel = buscar(id);
        hotel.setNome(dados.getNome());
        hotel.setEndereco(dados.getEndereco());
        hotel.setCategoria(dados.getCategoria());
        return hotelRepository.save(hotel);
    }

    @Transactional
    public void excluir(Integer id) {
        Hotel hotel = buscar(id);
        if (quartoRepository.existsByHotelId(id)) {
            throw new IllegalStateException(
                    "Não é possível excluir: o hotel possui quartos cadastrados. Exclua os quartos primeiro.");
        }
        hotelRepository.delete(hotel);
    }
}
