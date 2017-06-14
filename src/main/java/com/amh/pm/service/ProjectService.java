package com.amh.pm.service;

import java.util.List;

import com.amh.pm.entity.Project;

public interface ProjectService {

public void save(Project project);
    
    public void delete(Project project);
    
    public void update(Project project);
    
    public List<Project> findAll();
    
    public Project findById(int id);
    
    public Project findProjectByName(String projectName);
}
