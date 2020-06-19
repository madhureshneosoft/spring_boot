package com.neosoft.spring_boot_poc.repo;

import com.neosoft.spring_boot_poc.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UserRepoImpl {

    @PersistenceContext
    private EntityManager entityManager;

    public List<User> dynamicSearch(String queryString) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<User> criteriaQuery = cb.createQuery(User.class);
        Root<User> c = criteriaQuery.from(User.class);
        criteriaQuery.select(c);

        TypedQuery<User> query = entityManager.createQuery(queryString,User.class);
        return query.getResultList();
    }
}
