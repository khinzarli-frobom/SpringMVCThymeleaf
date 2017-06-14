package com.amh.pm.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amh.pm.dao.ProjectDao;
import com.amh.pm.entity.Project;
@Service
public class ProjectServiceImpl implements ProjectService{
    
    private ProjectDao projectDao;
    
    public void setProjectDao(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }


    @Override
    @Transactional
    public void save(Project project) {
        // TODO Auto-generated method stub
        projectDao.save(project);
    }

    @Override
    @Transactional
    public void delete(Project project) {
        // TODO Auto-generated method stub
        
    }

    @Override
    @Transactional
    public void update(Project project) {
        // TODO Auto-generated method stub
        
    }

    @Override
    @Transactional
    public List<Project> findAll() {
        // TODO Auto-generated method stub
        return projectDao.findAll();
    }

    @Override
    @Transactional
    public Project findById(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional
    public Project findProjectByName(String projectName) {
        // TODO Auto-generated method stub
        return projectDao.findProjectByName(projectName);
    }

}
