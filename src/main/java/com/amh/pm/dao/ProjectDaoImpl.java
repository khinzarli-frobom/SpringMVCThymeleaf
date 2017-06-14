package com.amh.pm.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.amh.pm.entity.Organization;
import com.amh.pm.entity.Project;
@Repository
public class ProjectDaoImpl implements ProjectDao{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Project project) {
        entityManager.merge(project);
        
    }

    @Override
    public void delete(Project project) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void update(Project project) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<Project> findAll() {
        // TODO Auto-generated method stub
        return entityManager.createQuery("SELECT p FROM Project p", Project.class).getResultList();

    }

    @Override
    public Project findById(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Project findProjectByName(String projectName) {
        // TODO Auto-generated method stub
        Project projectResult = null;

        try {

            Query q = entityManager.createQuery("select p from Organization p WHERE p.name=?");

            q.setParameter(1, projectName);

            projectResult = (Project) q.getSingleResult();

        } catch (NoResultException e) {

            System.out.println("Error is :" + e);

        }

        return projectResult;

    }
}
