package com.pagamento.hireus.domain.model;

import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Funcionario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nomeFuncionario;
	private String matriculaFuncionario;
	private Boolean status;
	private OffsetDateTime dataAdimissao;
	private OffsetDateTime dataDesligamento;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomeFuncionario() {
		return nomeFuncionario;
	}
	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}
	public String getMatriculaFuncionario() {
		return matriculaFuncionario;
	}
	public void setMatriculaFuncionario(String matriculaFuncionario) {
		this.matriculaFuncionario = matriculaFuncionario;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public OffsetDateTime getDataAdimissao() {
		return dataAdimissao;
	}
	public void setDataAdimissao(OffsetDateTime dataAdimissao) {
		this.dataAdimissao = dataAdimissao;
	}
	public OffsetDateTime getDataDesligamento() {
		return dataDesligamento;
	}
	public void setDataDesligamento(OffsetDateTime dataDesligamento) {
		this.dataDesligamento = dataDesligamento;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((matriculaFuncionario == null) ? 0 : matriculaFuncionario.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcionario other = (Funcionario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (matriculaFuncionario == null) {
			if (other.matriculaFuncionario != null)
				return false;
		} else if (!matriculaFuncionario.equals(other.matriculaFuncionario))
			return false;
		return true;
	}
	
	

}
