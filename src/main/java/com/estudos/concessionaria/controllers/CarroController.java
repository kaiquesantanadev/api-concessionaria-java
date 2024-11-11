package com.estudos.concessionaria.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.catalina.connector.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.estudos.concessionaria.dto.CarroDTO;
import com.estudos.concessionaria.model.Carro;
import com.estudos.concessionaria.repository.CarroRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/carro")
public class CarroController {
	@Autowired
	private CarroRepository carroRepository;

	@GetMapping("/pegarTodos")
	public List<Carro> pegarTodos() {
		return carroRepository.findAll();
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Carro> cadastrarCarro(@RequestBody @Valid CarroDTO carro) {
		var carroModel = new Carro();
		BeanUtils.copyProperties(carro, carroModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(carroRepository.save(carroModel));
	}
	
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Object> deletarCarro(@PathVariable(value = "id") UUID id) {
		Optional<Carro> carroEncontrado = carroRepository.findById(id);
		if (carroEncontrado.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Este carro não foi encontrado na base de dados");
		}
		carroRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Carro (ID:" + carroEncontrado.get().getId() + ", NOME: "+ carroEncontrado.get().getNome() + ") excluído com sucesso");
	}
}
