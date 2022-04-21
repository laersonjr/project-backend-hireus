package com.pagamento.hireus.domain.model;

import java.time.OffsetDateTime;

import javax.persistence.Entity;


public class Funcionario {
	
	private Long id;
	private String nomeFuncionario;
	private String matriculaFuncionario;
	private Boolean status;
	private OffsetDateTime dataAdimissao;
	private OffsetDateTime dataDesligamento;

}
