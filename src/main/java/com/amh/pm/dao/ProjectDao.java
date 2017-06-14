package com.amh.pm.dao;

import java.util.List;

import com.amh.pm.entity.Project;


public interface ProjectDao {
    
    public void save(Project project);

    public void delete(Project project);

    public void update(Project project);

    public List<Project> findAll();

    public Project findById(int id);
    
    public Project findProjectByName(String projectName);

}
