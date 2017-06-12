package com.amh.pm.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "title", nullable = false, unique = false)
	@NotEmpty(message = "Please enter task title.")
	private String title;
	@Column(name = "summary", nullable = false, unique = false)
	@NotEmpty(message = "Please enter task summary.")
	private String summary;
	@Column(name = "scheduleStartDate", nullable = false, unique = false)
	//@NotEmpty(message = "Please enter your task start date.")
	private Date scheduleStartDate;
	@Column(name = "scheduleEndDate", nullable = false, unique = false)
	//@NotEmpty(message = "Please enter your task end date.")
	private Date scheduleEndDate;
	@Column(name = "actualStartDate", nullable = false, unique = false)
	private Date actualStartDate;
	@Column(name = "actualEndDate", nullable = false, unique = false)
	private Date actualEndDate;
	@Column(name = "status", nullable = false, unique = false)
	@NotEmpty(message = "Please enter your task status.")
	private String status;
	@ManyToOne
	//@NotEmpty(message = "Please choose poject.")
	private Project project;
	@ManyToOne
	//@NotEmpty(message = "Please choose assigne user.")
	private User assignee;
	@Column(name = "weight", nullable = false, unique = false)
	@NotEmpty(message = "Please enter weight.")
	private String weight;
	@Column(name = "score", nullable = false, unique = false)
	@NotEmpty(message = "Please enter score.")
	private String score;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "TaskTechnologyTag", joinColumns = @JoinColumn(name = "taskId", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "technologyTag", referencedColumnName = "id"))
	private List<TechnologyTag> technologyTag;

	public Task() {
		super();
	}

	public Task(int id, String title, String summary, Date scheduleStartDate, Date scheduleEndDate,
			Date actualStartDate, Date actualEndDate, String weight, String score) {
		super();
		this.id = id;
		this.title = title;
		this.summary = summary;
		this.scheduleStartDate = scheduleStartDate;
		this.scheduleEndDate = scheduleEndDate;
		this.actualStartDate = actualStartDate;
		this.actualEndDate = actualEndDate;
		this.weight = weight;
		this.score = score;
	}
	public Task( String title, String summary, Date scheduleStartDate, Date scheduleEndDate,
			Date actualStartDate, Date actualEndDate, String weight, String score, Project project, User assignee) {
		super();

		this.title = title;
		this.summary = summary;
		this.scheduleStartDate = scheduleStartDate;
		this.scheduleEndDate = scheduleEndDate;
		this.actualStartDate = actualStartDate;
		this.actualEndDate = actualEndDate;
		this.weight = weight;
		this.score = score;
		this.project = project;
		this.assignee = assignee;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Date getScheduleStartDate() {
		return scheduleStartDate;
	}

	public void setScheduleStartDate(Date scheduleStartDate) {
		this.scheduleStartDate = scheduleStartDate;
	}

	public Date getScheduleEndDate() {
		return scheduleEndDate;
	}

	public void setScheduleEndDate(Date scheduleEndDate) {
		this.scheduleEndDate = scheduleEndDate;
	}

	public Date getActualStartDate() {
		return actualStartDate;
	}

	public void setActualStartDate(Date actualStartDate) {
		this.actualStartDate = actualStartDate;
	}

	public Date getActualEndDate() {
		return actualEndDate;
	}

	public void setActualEndDate(Date actualEndDate) {
		this.actualEndDate = actualEndDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public User getAssignee() {
		return assignee;
	}

	public void setAssignee(User assignee) {
		this.assignee = assignee;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public List<TechnologyTag> getTechnologyTag() {
		return technologyTag;
	}

	public void setTechnologyTag(List<TechnologyTag> technologyTag) {
		this.technologyTag = technologyTag;
	}
	@Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof Task))
            return false;
        Task task = (Task) obj;
        return task.getTitle() == this.getTitle()
                && task.getSummary() == this.getSummary()
                && task.getScheduleStartDate() == this.getScheduleStartDate()
                && task.getScheduleEndDate() == this.getActualEndDate()
                && task.getActualStartDate() == this.getScheduleStartDate()
                && task.getActualEndDate() == this.getActualEndDate()
                && task.getStatus() == this.getStatus()
                && task.getWeight() == this.getWeight()
                && task.getAssignee() == this.getAssignee();
    }
 @Override
    public int hashCode() {
	 int result = 17;
        result = 31 * result + title.hashCode();
        result = 31 * result + summary.hashCode();
        result = 31 * result + scheduleStartDate.hashCode();
        result = 31 * result + scheduleEndDate.hashCode();
        result = 31 * result + actualStartDate.hashCode();
        result = 31 * result + actualEndDate.hashCode();
        result = 31 * result + weight.hashCode();
        result = 31 * result + assignee.hashCode();
        return result;
    }
}
