package com.estudos.concessionaria.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estudos.concessionaria.model.Carro;

public interface CarroRepository extends JpaRepository<Carro, UUID> {
	
}
