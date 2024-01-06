package ucsal.edu.web.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import ucsal.edu.web.helpers.ROLE;

@Entity
@Table(name = "titular")
public class Titular {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@OneToMany
	private List<CartaoCredito> cartaoCredito;
	@OneToMany
	private List<Transacao> transacao;
	@OneToMany
	private List<Dependente> dependente;
	@Enumerated(EnumType.STRING)
	private ROLE role;
	private String nome;
	private String cpf;
	private String endereco;
	private String numIdentificacao;
	private String classificacao;
	private LocalDate nascimento;
	
	public Titular() {
		super();
	}

	public Titular(Long id, List<CartaoCredito> cartaoCredito, List<Transacao> transacao, List<Dependente> dependente,
			ROLE role, String nome, String cpf, String endereco, String numIdentificacao, String classificacao,
			LocalDate nascimento) {
		super();
		this.id = id;
		this.cartaoCredito = cartaoCredito;
		this.transacao = transacao;
		this.dependente = dependente;
		this.role = role;
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
		this.numIdentificacao = numIdentificacao;
		this.classificacao = classificacao;
		this.nascimento = nascimento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<CartaoCredito> getCartaoCredito() {
		return cartaoCredito;
	}

	public void setCartaoCredito(List<CartaoCredito> cartaoCredito) {
		this.cartaoCredito = cartaoCredito;
	}

	public List<Transacao> getTransacao() {
		return transacao;
	}

	public void setTransacao(List<Transacao> transacao) {
		this.transacao = transacao;
	}

	public List<Dependente> getDependente() {
		return dependente;
	}

	public void setDependente(List<Dependente> dependente) {
		this.dependente = dependente;
	}

	public ROLE getRole() {
		return role;
	}

	public void setRole(ROLE role) {
		this.role = role;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNumIdentificacao() {
		return numIdentificacao;
	}

	public void setNumIdentificacao(String numIdentificacao) {
		this.numIdentificacao = numIdentificacao;
	}

	public String getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}

	public LocalDate getNascimento() {
		return nascimento;
	}

	public void setNascimento(LocalDate nascimento) {
		this.nascimento = nascimento;
	}

}
