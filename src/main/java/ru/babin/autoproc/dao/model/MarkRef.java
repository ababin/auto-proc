package ru.babin.autoproc.dao.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "MARK_REF")
public class MarkRef implements IRef{

	@javax.persistence.Id
	@javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	@javax.persistence.Column(name = "ID", nullable = false)
	private Long id;

	@javax.persistence.Column(name = "NAME", length = 50)
	private String name;

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

}