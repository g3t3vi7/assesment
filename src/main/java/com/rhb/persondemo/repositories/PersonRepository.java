package com.rhb.persondemo.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rhb.persondemo.models.Person;

@Repository
public class PersonRepository {
	
	@PersistenceContext
	private EntityManager entityManager;

	public List<Person> findByFirstname(String firstname) {
        
		CriteriaBuilder cbObj = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object> queryObj = cbObj.createQuery();
        Root<Person> from = queryObj.from(Person.class);
 
        CriteriaQuery<Object> selectQuery = queryObj.select(from);
        TypedQuery<Object> typedQuery = entityManager.createQuery(selectQuery.where(cbObj.like(from.get("firstname"), "%" + firstname + "%")));
        List<Person> personList = (List<Person>)(List<?>) typedQuery.getResultList();
 
        
        return personList;
    }

}
