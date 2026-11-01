package com.eep.tarefa2.repository;

import com.eep.tarefa2.domain.Quarto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuartoRepository extends JpaRepository<Quarto, Integer> {

    List<Quarto> findByHotelIdOrderByNumero(Integer hotelId);

    boolean existsByHotelId(Integer hotelId);
}
