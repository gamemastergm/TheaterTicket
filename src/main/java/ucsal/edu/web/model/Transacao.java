package ucsal.edu.web.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import ucsal.edu.web.helpers.Confirmacao;

@Entity
@Table(name = "transacao")
public class Transacao {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne
	private Titular Titular;
	@ManyToOne
	private Estabelecimento Estabelecimento;
	private LocalDate data;
	private String local;
	private Confirmacao confirmacao;
	
	public Transacao(Long id, Titular titular, Estabelecimento estabelecimento,
			LocalDate data, String local, Confirmacao confirmacao) {
		super();
		this.id = id;
		Titular = titular;
		Estabelecimento = estabelecimento;
		this.data = data;
		this.local = local;
		this.confirmacao = confirmacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Titular getTitular() {
		return Titular;
	}

	public void setTitular(Titular titular) {
		Titular = titular;
	}

	public Estabelecimento getEstabelecimento() {
		return Estabelecimento;
	}

	public void setEstabelecimento(Estabelecimento estabelecimento) {
		Estabelecimento = estabelecimento;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public Confirmacao getConfirmacao() {
		return confirmacao;
	}

	public void setConfirmacao(Confirmacao confirmacao) {
		this.confirmacao = confirmacao;
	}

}
