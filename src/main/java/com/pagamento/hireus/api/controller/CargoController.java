package com.pagamento.hireus.api.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pagamento.hireus.domain.model.Cargo;
import com.pagamento.hireus.domain.repository.CargoRepository;

@RestController
@RequestMapping("/cargos")
public class CargoController {
	
	@Autowired
	private CargoRepository cargoRepository;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Cargo> listarCargo(){
		return cargoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cargo> buscarCargoId(@PathVariable Long id){
		return cargoRepository.findById(id)
				.map(record -> ResponseEntity.ok().body(record))
		           .orElse(ResponseEntity.notFound().build());	
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	/**
	 * @author Bruno Brito ajudou no Hatoes.
	 */
	public ResponseEntity<Cargo> salvarCargo(@RequestBody Cargo cargo) {
		cargo = cargoRepository.save(cargo);
		//HATEOS
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(cargo.getId())
				.toUri();
		return ResponseEntity.created(uri).body(cargo);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Cargo> excluirCargo(@PathVariable Long id) {
		if (!cargoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}

		cargoRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cargo> atualizarCargo(@PathVariable Long id,
			@RequestBody Cargo cargo) {
		if (!cargoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}

		cargo.setId(id);
		cargo = cargoRepository.save(cargo);

		return ResponseEntity.ok(cargo);
	}
	

}
