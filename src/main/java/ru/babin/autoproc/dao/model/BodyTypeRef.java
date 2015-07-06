package ru.babin.autoproc.dao.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import ru.babin.autoproc.api.model.EAutoBodyType;

@Entity
@Table(name = "BODY_TYPE_REF")
public class BodyTypeRef implements IRef{

	@javax.persistence.Id
	@javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	@javax.persistence.Column(name = "ID", nullable = false)
	private Long id;

	@javax.persistence.Column(name = "NAME", nullable = false, length = 50)
	private String name;
	
	@javax.persistence.Column(name = "ENUM_NAME", nullable = true, length = 20)
	@javax.persistence.Enumerated(javax.persistence.EnumType.STRING)
	private EAutoBodyType enumName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public EAutoBodyType getEnumName() {
		return enumName;
	}

	public void setEnumName(EAutoBodyType enumName) {
		this.enumName = enumName;
	}

}
