package com.pagamento.hireus.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
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

import com.pagamento.hireus.api.model.FuncionarioInputModel;
import com.pagamento.hireus.api.model.FuncionarioOutputModel;
import com.pagamento.hireus.domain.model.Funcionario;
import com.pagamento.hireus.domain.repository.FuncionarioRepository;
import com.pagamento.hireus.domain.service.FuncionarioService;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<FuncionarioOutputModel> listarFuncionario(){
		return toCollectionModel(funcionarioRepository.findAll());
	}
// Pendente
	@GetMapping("/{id}")
	public ResponseEntity<Funcionario> buscarFuncionarioId(@PathVariable Long id){
		return funcionarioRepository.findById(id)
				.map(record -> ResponseEntity.ok().body(record))
		           .orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nomeFunc}")
	public List<FuncionarioOutputModel> listarFuncionarioNome(@PathVariable String nomeFunc){
		return toCollectionModel(funcionarioRepository.findByNomeFuncionarioContaining(nomeFunc));
	}
	
	@GetMapping("/mat/{matFunc}")
	public List<FuncionarioOutputModel> listarFuncionarioMatricula(@PathVariable String matFunc){
		return  toCollectionModel(funcionarioRepository.findByMatriculaFuncionarioContaining(matFunc));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Funcionario> salvarFuncionario(@Valid @RequestBody FuncionarioInputModel funcionarioInputModel) {
		return funcionarioService.salvarFuncionario(funcionarioInputModel);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Funcionario> excluirFuncionario(@PathVariable Long id) {		
		return funcionarioService.excluirFuncionario(id);
	}
// Pendente
	@PutMapping("/{id}")
	public ResponseEntity<Funcionario> atualizarFuncionario(@PathVariable Long id,
			@Valid @RequestBody Funcionario funcionario) {
		if (!funcionarioRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}

		funcionario.setId(id);
		funcionario = funcionarioRepository.save(funcionario);

		return ResponseEntity.ok(funcionario);
	}
	
	private FuncionarioOutputModel toModel(Funcionario funcionario) {
		return modelMapper.map(funcionario, FuncionarioOutputModel.class);
	}
	
	private List<FuncionarioOutputModel> toCollectionModel(List<Funcionario> funcionarios){
		return funcionarios.stream()
				.map(this::toModel)
				.collect(Collectors.toList());
	}
	

	
}
