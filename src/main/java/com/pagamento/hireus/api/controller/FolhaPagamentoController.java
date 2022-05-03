package com.pagamento.hireus.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pagamento.hireus.domain.model.FolhaPagamento;
import com.pagamento.hireus.domain.model.Funcionario;
import com.pagamento.hireus.domain.repository.FolhaPagamentoRepository;
import com.pagamento.hireus.domain.repository.FuncionarioRepository;


@RestController
@RequestMapping("/pagamento")
public class FolhaPagamentoController {
	
	@Autowired
	private FolhaPagamentoRepository pagamentoRepository; 
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@PostMapping
	public FolhaPagamento gerarFolha(@RequestBody FolhaPagamento folhaPagamento) {
		Long idFuncionarioBuscado = folhaPagamento.getFuncionario().getId();
		Optional<Funcionario> funcionarioBuscado = funcionarioRepository.findById(idFuncionarioBuscado);

		if (!funcionarioBuscado.isPresent()) {
			throw new IllegalArgumentException("Funcionário não existe na base");
		}
		folhaPagamento.setFuncionario(funcionarioBuscado.get());
		folhaPagamento.setInss();
		folhaPagamento.setIrrf();
		folhaPagamento.setSalarioLiquido();

		return pagamentoRepository.save(folhaPagamento);
	}
	
}
