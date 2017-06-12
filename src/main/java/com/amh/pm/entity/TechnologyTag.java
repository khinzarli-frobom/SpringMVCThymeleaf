package com.amh.pm.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table
public class TechnologyTag {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "name", nullable = false, unique = true)
	@NotEmpty(message = "Please enter technology name.")
	private String name;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "TaskTechnologyTag", joinColumns = @JoinColumn(name = "technologyTag", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "taskId", referencedColumnName = "id"))
	private List<Task> task;

	public TechnologyTag() {
		super();
	}

	public TechnologyTag( String name) {
		super();
		this.name = name;
	}

	public TechnologyTag(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Task> getTask() {
		return task;
	}

	public void setTask(List<Task> task) {
		this.task = task;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof TechnologyTag))
			return false;
		TechnologyTag technologyTag = (TechnologyTag) obj;
		return technologyTag.getName() == this.getName();
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + name.hashCode();
		return result;
	}
}

