package com.amh.pm.entity;

import java.io.Serializable;

public class ProjectMemberId implements Serializable{

	private int userId;
	
	private int projectId;
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int hashCode() {
	    return (int)(userId + projectId);
	  }

	  public boolean equals(Object object) {
	    if (object instanceof ProjectMemberId) {
	    	ProjectMemberId otherId = (ProjectMemberId) object;
	      return (otherId.userId == this.userId) && (otherId.projectId == this.projectId);
	    }
	    return false;
	  }
}
