package ucsal.edu.web.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import ucsal.edu.web.helpers.Status;

@Entity
@Table(name="cartaocredito")
public class CartaoCredito {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String numeroCartao;
	private String nomeCartao;
	private String validade;
	private int codigoSeguranca;
	private double limite;
	private Status status;
	
	public CartaoCredito(Long id, String numeroCartao, String nomeCartao, String validade, int codigoSeguranca,
			double limite, Status status) {
		super();
		this.id = id;
		this.numeroCartao = numeroCartao;
		this.nomeCartao = nomeCartao;
		this.validade = validade;
		this.codigoSeguranca = codigoSeguranca;
		this.limite = limite;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public String getNomeCartao() {
		return nomeCartao;
	}

	public void setNomeCartao(String nomeCartao) {
		this.nomeCartao = nomeCartao;
	}

	public String getValidade() {
		return validade;
	}

	public void setValidade(String validade) {
		this.validade = validade;
	}

	public int getCodigoSeguranca() {
		return codigoSeguranca;
	}

	public void setCodigoSeguranca(int codigoSeguranca) {
		this.codigoSeguranca = codigoSeguranca;
	}

	public double getLimite() {
		return limite;
	}

	public void setLimite(double limite) {
		this.limite = limite;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
	