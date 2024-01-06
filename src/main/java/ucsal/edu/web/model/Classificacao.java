package ucsal.edu.web.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "classificacao")
public class Classificacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String fileName;
	private String code;
	private String type;
	private String identification;
	private String definition;
	private String description;
	private String link;

	public Classificacao() {

	}

	public Classificacao(Long id, String fileName, String code, String type, String identification, String definition,
			String description, String link) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.code = code;
		this.type = type;
		this.identification = identification;
		this.definition = definition;
		this.description = description;
		this.link = link;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public String toString() {
		return "Classificacao [id=" + id + ", fileName=" + fileName + ", code=" + code + ", type=" + type
				+ ", identification=" + identification + ", definition=" + definition + ", description=" + description
				+ ", link=" + link + "]";
	}
}
