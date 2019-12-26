package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CNPJ;

import com.sun.istack.internal.NotNull;

@Entity
public class CadEstabelecimento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull
	@NotEmpty(message = "Razão social é obrigatorio!")
	@Column(length = 80)
	private String razaoS;
	@Column(length = 80)
	private String nomeF;

	@NotNull
	@NotEmpty(message = "Cnpj é obrigatorio!")
	@CNPJ(message = "cnpj Inválido!")
	@Column(length = 18)
	private String cnpj;

	@Email(message = "E-mail inválido!")
	@Column(length = 80)
	private String email;

	@Column(length = 9)
	private String cep;
	private String logradouro;
	private Integer numero;
	
	@Column(length = 10)
	private String complemento;
	@Column(length = 80)
	private String bairro;
	@Column(length = 80)
	private String localidade;
	
	@Column(length = 2)
	private String uf;

	@Column(length = 18)
	private String telefone;

	@Temporal(TemporalType.DATE)
	@Past(message = "Data não pode ser inferior a data atual")
	private Date dataCad = new Date();

	@Column(length = 80)
	private String categoria;

	@Column(length =7)
	private String status;
	
	@Column(length = 5)
	private String agencia;
	
	@Column(length = 8)
	private String conta;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRazaoS() {
		return razaoS;
	}

	public void setRazaoS(String razaoS) {
		this.razaoS = razaoS;
	}

	public String getNomeF() {
		return nomeF;
	}

	public void setNomeF(String nomeF) {
		this.nomeF = nomeF;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}


	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Date getDataCad() {
		return dataCad;
	}

	public void setDataCad(Date dataCad) {
		this.dataCad = dataCad;
	}

	public String getCategoria() {
		return categoria;
	}
	
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		CadEstabelecimento other = (CadEstabelecimento) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CadEstabelecimento [id=" + id + ", razaoS=" + razaoS + ", nomeF=" + nomeF + ", cnpj=" + cnpj
				+ ", email=" + email + ", cep=" + cep + ", logradouro=" + logradouro + ", numero=" + numero
				+ ", complemento=" + complemento + ", bairro=" + bairro + ", localidade=" + localidade + ", uf=" + uf
				+ ", telefone=" + telefone + ", dataCad=" + dataCad + ", categoria=" + categoria + ", status=" + status
				+ ", agencia=" + agencia + ", conta=" + conta + "]";
	}


}
