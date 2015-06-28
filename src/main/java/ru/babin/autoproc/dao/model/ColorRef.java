package ru.babin.autoproc.dao.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "COLOR_REF")
public class ColorRef implements IRef{

	@javax.persistence.Id
	@javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	@javax.persistence.Column(name = "ID", nullable = false)
	private Long id;

	@javax.persistence.Column(name = "NAME", nullable=false ,length = 50)
	private String name;
	
	@javax.persistence.Column(name = "CODE", length = 10)
	private String code;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
