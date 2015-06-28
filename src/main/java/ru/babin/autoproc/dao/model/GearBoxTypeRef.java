package ru.babin.autoproc.dao.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "GEAR_BOX_TYPE_REF")
public class GearBoxTypeRef implements IRef{

	@javax.persistence.Id
	@javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	@javax.persistence.Column(name = "ID", nullable = false)
	private Long id;

	@javax.persistence.Column(name = "NAME", nullable=false ,length = 50)
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
