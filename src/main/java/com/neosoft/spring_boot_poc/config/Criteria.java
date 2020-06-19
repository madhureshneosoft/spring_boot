//package com.neosoft.spring_boot_poc.config;
//
//import com.neosoft.spring_boot_poc.model.User;
//import org.hibernate.SessionFactory;
//import org.hibernate.jpa.HibernateEntityManagerFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//import javax.persistence.TypedQuery;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Root;
//import java.util.List;
//
//@Configuration
//public class Criteria {
//    public void configureEntityManager() {
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("userdb");
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
//        Root<User> from = query.from(User.class);
//        query.select(from);
//
//        TypedQuery<User> typedQuery = entityManager.createQuery(query);
//        List<User> resultList = typedQuery.getResultList();
//
//        entityManager.close();
//        entityManagerFactory.close();
//    }
//
//
//}