package com.ibm.bike.locacao.repository;

import com.ibm.bike.locacao.model.Bicycle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BicycleRepository extends JpaRepository<Bicycle, Long> {

    List<Bicycle> findByModelo(String modelo);

    List<Bicycle> findByCor(String cor);
}