package ru.babin.autoproc.dao.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "MODEL_REF")
public class ModelRef implements IRef{

	@javax.persistence.Id
	@javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	@javax.persistence.Column(name = "ID", nullable = false)
	private Long id;

	@javax.persistence.Column(name = "NAME", length = 100)
	private String name;

	@javax.persistence.Column(name = "MARK_ID", nullable = false)
	private Long markId;
	
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

	public Long getMarkId() {
		return markId;
	}

	public void setMarkId(Long markId) {
		this.markId = markId;
	}
	
}
