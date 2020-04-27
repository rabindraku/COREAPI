package com.example.demo.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.EmployeeEntity;

@Repository
public class UserDaoImpl implements UserDao {
	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings({ "rawtypes", "deprecation" })
	public List getUserDetails() {
		Criteria criteria = entityManager.unwrap(Session.class).createCriteria(EmployeeEntity.class);
		return criteria.list();
	}
}
