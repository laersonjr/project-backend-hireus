package com.pagamento.hireus.api.controller;

import java.net.URI;
import java.time.OffsetDateTime;
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

import com.pagamento.hireus.domain.model.Funcionario;
import com.pagamento.hireus.domain.repository.FuncionarioRepository;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Funcionario> listarFuncionario(){
		return funcionarioRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Funcionario> buscarFuncionarioId(@PathVariable Long id){
		return funcionarioRepository.findById(id)
				.map(record -> ResponseEntity.ok().body(record))
		           .orElse(ResponseEntity.notFound().build());
		
//		Optional<Funcionario> funcioario = funcionarioRepository.findById(id);	
//		return funcioario.isPresent() ? ResponseEntity.ok().body(funcioario.get()) : ResponseEntity.notFound().build();
	}
	
	@GetMapping("/nome/{nomeFunc}")
	public List<Funcionario> listarFuncionarioNome(@PathVariable String nomeFunc){
		return funcionarioRepository.findByNomeFuncionarioContaining(nomeFunc);
	}
	
	@GetMapping("/mat/{matFunc}")
	public List<Funcionario> listarFuncionarioMatricula(@PathVariable String matFunc){
		return funcionarioRepository.findByMatriculaFuncionarioContaining(matFunc);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	/**
	 * @author Bruno Brito ajudou no Hatoes.
	 */
	public ResponseEntity<Funcionario> salvarFuncionario(@RequestBody Funcionario funcionario) {
		funcionario.setDataAdimissao(OffsetDateTime.now());
		funcionario = funcionarioRepository.save(funcionario);
		//HATEOS
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(funcionario.getId())
				.toUri();
		return ResponseEntity.created(uri).body(funcionario);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Funcionario> excluirFuncionario(@PathVariable Long id) {
		if (!funcionarioRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}

		funcionarioRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Funcionario> atualizarFuncionario(@PathVariable Long id,
			@RequestBody Funcionario funcionario) {
		if (!funcionarioRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}

		funcionario.setId(id);
		funcionario = funcionarioRepository.save(funcionario);

		return ResponseEntity.ok(funcionario);
	}
	
}
