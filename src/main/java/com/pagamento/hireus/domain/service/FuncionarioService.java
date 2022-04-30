package com.pagamento.hireus.domain.service;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pagamento.hireus.api.exceptionhandler.EmptyRecurseException;
import com.pagamento.hireus.api.model.FuncionarioInputModel;
import com.pagamento.hireus.domain.model.Cargo;
import com.pagamento.hireus.domain.model.Funcionario;
import com.pagamento.hireus.domain.repository.CargoRepository;
import com.pagamento.hireus.domain.repository.FuncionarioRepository;

@Service
public class FuncionarioService {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CargoRepository cargoRepository;
	
	public ResponseEntity<Funcionario> salvarFuncionario(FuncionarioInputModel funcionarioInputModel){
		Funcionario funcionario = toEntity(funcionarioInputModel);
		Cargo cargo = cargoRepository.findById(funcionarioInputModel.getCargoId()).get();
		funcionario.setDataAdimissao(OffsetDateTime.now());
		funcionario.setStatus(true);
		funcionario.setCargo(cargo);
		funcionario = funcionarioRepository.save(funcionario);
		//HATEOS
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(funcionario.getId())
				.toUri();
		return ResponseEntity.created(uri).body(funcionario);
	}
	
	
	public ResponseEntity<Funcionario> excluirFuncionario(Long id) {
		Funcionario funcionario = buscarFuncionarioPeloId(id);
		funcionarioRepository.deleteById(funcionario.getId());
		return ResponseEntity.noContent().build();
		
	}
	
	private Funcionario toEntity(FuncionarioInputModel funcionarioInputModel) {
		return modelMapper.map(funcionarioInputModel, Funcionario.class);
	}
	
	private Funcionario buscarFuncionarioPeloId(Long id) {
		Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
		if(funcionario.isEmpty()) {
			throw new EmptyRecurseException("Nenhum recurso encontrado!");
		}
		return funcionario.get();
		
	}
	
}
