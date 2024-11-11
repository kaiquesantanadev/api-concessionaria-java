package com.estudos.concessionaria.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.catalina.connector.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estudos.concessionaria.dto.FabricanteDTO;
import com.estudos.concessionaria.model.Fabricante;
import com.estudos.concessionaria.repository.FabricanteRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/fabricante")
public class FabricanteController {
	@Autowired
	private FabricanteRepository fabricanteRepository;
	
	@GetMapping("/pegarTodos")
	public List<Fabricante> pegarFabricantes() {
		return fabricanteRepository.findAll();
	}
	
	@GetMapping("/pegarPorId/{id}") 
	public ResponseEntity<Object> pegarPorID(@PathVariable(value = "id") UUID id) {
		Optional<Fabricante> fabricantePorId = fabricanteRepository.findById(id);
		if (fabricantePorId.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível encontrar um fabricante com o ID: " + id);
		}
		return ResponseEntity.status(HttpStatus.OK).body(fabricantePorId.get());
	}
	
	@PostMapping("/cadastrar") 
	public ResponseEntity<Fabricante> cadastrarFabricante(@RequestBody @Valid FabricanteDTO fabricante) {
		var fabricanteModel = new Fabricante();
		BeanUtils.copyProperties(fabricante, fabricanteModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(fabricanteRepository.save(fabricanteModel));
	}
	
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Object> deletarFabricante(@PathVariable(value = "id") UUID id) {
		Optional<Fabricante>  fabricantePorId= fabricanteRepository.findById(id);
		if (fabricantePorId.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado um fabricante com este ID");
		}
		fabricanteRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Fabricante excluído com êxito");
	}
}
