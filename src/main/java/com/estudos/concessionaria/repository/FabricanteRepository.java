package com.estudos.concessionaria.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estudos.concessionaria.model.Fabricante;

public interface FabricanteRepository extends JpaRepository<Fabricante, UUID> {

}
