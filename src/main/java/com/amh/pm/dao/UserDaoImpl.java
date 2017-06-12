package com.amh.pm.dao;

import java.util.List;

import javax.persistence.EntityManager;

import javax.persistence.NoResultException;

import javax.persistence.PersistenceContext;

import javax.persistence.Query;

import javax.validation.Validation;

import javax.validation.Validator;

import javax.validation.ValidatorFactory;

import org.springframework.stereotype.Repository;

import java.util.Iterator;

import java.util.Set;

import javax.validation.ConstraintViolation;

import com.amh.pm.entity.User;

@Repository

public class UserDaoImpl implements UserDao {

	@PersistenceContext

	private EntityManager entityManager;

	@Override

	public void save(User user) {

		// TODO Auto-generated method stub

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

		Validator validator = factory.getValidator();

		Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);

		if (constraintViolations.size() > 0) {

			Iterator<ConstraintViolation<User>> iterator = constraintViolations.iterator();

			while (iterator.hasNext()) {

				ConstraintViolation<User> cv = iterator.next();

				System.out.println("Violation Error:" + cv.getRootBeanClass().getName() + "." + cv.getPropertyPath()

						+ " " + cv.getMessage());

			}

		} else {

			entityManager.persist(user);

		}

	}

	@Override

	public void delete(User user) {

		// TODO Auto-generated method stub

	}

	@Override

	public void update(User user) {

		// TODO Auto-generated method stub

	}

	@Override

	public List<User> findAll() {

		// TODO Auto-generated method stub

		return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();

	}

	@Override

	public User findById(int userId) {

		// TODO Auto-generated method stub

		return entityManager.find(User.class, userId);

	}

	@Override

	public User userByName(String name, String password) {

		// TODO Auto-generated method stub

		User u = null;

		try {

			Query q = entityManager.createQuery("SELECT u FROM User u WHERE u.name=? AND u.password=?");

			q.setParameter(1, name);

			q.setParameter(2, password);

			u = (User) q.getSingleResult();

		} catch (NoResultException e) {

			System.out.println(e);

			// TODO: handle exception

		}

		return u;

	}

	@Override

	public List<User> findUserNameByOrgnId(int orgId) {

		// TODO Auto-generated method stub

		Query q = entityManager.createQuery("select u from User u JOIN u.orgList orgmlist WHERE orgmlist.id=?");

		q.setParameter(1, orgId);

		List<User> userNameList = q.getResultList();

		return userNameList;

	}

	@Override

	public User findUserIdByName(String userName) {

		// TODO Auto-generated method stub

		User u = null;

		try {

			Query q = entityManager.createQuery("select u from User u WHERE u.name=?");

			q.setParameter(1, userName);

			u = (User) q.getSingleResult();

		} catch (NoResultException e) {

			System.out.println("Error is :" + e);

		}

		return u;

	}

}