package com.amh.pm.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table
public class TaskNote {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToOne
	private Task taskId;
	@Column(name = "comment", nullable = false, unique = false)
	@NotEmpty(message = "Please enter your comment.")
	private String comment;
	@ManyToOne
	private User commentedBy;
	
	public TaskNote(){
		super();
	}
	public TaskNote(int id, String comment){
		super();
		this.id = id;
		this.comment = comment;
	}
	public TaskNote( String comment){
		super();
		this.comment = comment;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Task getTaskId() {
		return taskId;
	}
	public void setTaskId(Task taskId) {
		this.taskId = taskId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public User getCommentedBy() {
		return commentedBy;
	}
	public void setCommentedBy(User commentedBy) {
		this.commentedBy = commentedBy;
	}
	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof TaskNote))
			return false;
		TaskNote taskNote = (TaskNote) obj;
		return taskNote.getTaskId() == this.getTaskId()
				&& taskNote.getComment() == this.getComment();
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + taskId.hashCode();
		result = 31 * result + comment.hashCode();
		return result;
	}
}
