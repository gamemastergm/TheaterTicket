package ucsal.edu.web.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="estabelecimento")
public class Estabelecimento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String endereco;
	private Long CNPJ;
	private LocalDate data;
	private String servicos;
	private double pontuacao;
	private String comentarios;
	
	public Estabelecimento(Long id, String endereco, Long cNPJ, LocalDate data, String servicos, double pontuacao,
			String comentarios) {
		super();
		this.id = id;
		this.endereco = endereco;
		CNPJ = cNPJ;
		this.data = data;
		this.servicos = servicos;
		this.pontuacao = pontuacao;
		this.comentarios = comentarios;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Long getCNPJ() {
		return CNPJ;
	}

	public void setCNPJ(Long cNPJ) {
		CNPJ = cNPJ;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getServicos() {
		return servicos;
	}

	public void setServicos(String servicos) {
		this.servicos = servicos;
	}

	public double getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(double pontuacao) {
		this.pontuacao = pontuacao;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	
}
	