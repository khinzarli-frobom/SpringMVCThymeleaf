package com.amh.pm.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.amh.pm.entity.Organization;

import com.amh.pm.entity.User;

@Repository

public class OrganizationDaoImpl implements OrganizationDao {

	@PersistenceContext

	private EntityManager entityManager;

	@Override

	public void save(Organization organization) {

		// TODO Auto-generated method stub

		entityManager.merge(organization);

	}

	@Override

	public void delete(Organization organization) {

		// TODO Auto-generated method stub

	}

	@Override

	public void update(Organization organization) {

		// TODO Auto-generated method stub

	}

	@Override

	public List<Organization> findAll() {

		// TODO Auto-generated method stub

		return entityManager.createQuery("SELECT o FROM Organization o", Organization.class).getResultList();

	}

	@Override

	public Organization findById(int id) {

		// TODO Auto-generated method stub

		return entityManager.find(Organization.class, id);

	}
	
	@Override

    public Organization findOrganizationByName(String orgName) {

        // TODO Auto-generated method stub

        Organization orgResult = null;

        try {

            Query q = entityManager.createQuery("select o from Organization o WHERE o.name=?");

            q.setParameter(1, orgName);

            orgResult = (Organization) q.getSingleResult();

        } catch (NoResultException e) {

            System.out.println("Error is :" + e);

        }

        return orgResult;

    }

}