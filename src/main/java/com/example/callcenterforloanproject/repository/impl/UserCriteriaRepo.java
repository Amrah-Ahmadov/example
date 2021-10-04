package com.example.callcenterforloanproject.repository.impl;

import com.example.callcenterforloanproject.model.User;
import com.example.callcenterforloanproject.repository.IUserCriteriaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Primary
public class UserCriteriaRepo implements IUserCriteriaRepo {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public User getUserById(Long id){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = criteriaBuilder.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        Predicate userIdPredicate = criteriaBuilder.equal(root.get("id"), id);
        cq.where(userIdPredicate);
        TypedQuery<User> query = entityManager.createQuery(cq);
        return query.getSingleResult();
    }
    @Override
    public User getUserByInnerNumber(Long innerNumber){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        Predicate userNumberPredicate = cb.equal(root.get("innerNumber"), innerNumber);
        cq.where(userNumberPredicate);
        TypedQuery<User> query = entityManager.createQuery(cq);
        return query.getSingleResult();
    }
    @Override
    public List<User> getUserByName(String name){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = criteriaBuilder.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        Predicate userNamePredicate = criteriaBuilder.equal(root.get("name"), name);
        cq.where(userNamePredicate);
        TypedQuery<User> query = entityManager.createQuery(cq);
        return query.getResultList();
    }
    @Override
    public List<User> getUserBySurname(String surname){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = criteriaBuilder.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        Predicate userSurnamePredicate = criteriaBuilder.equal(root.get("surname"), surname);
        cq.where(userSurnamePredicate);
        TypedQuery<User> query = entityManager.createQuery(cq);
        return query.getResultList();
    }
    @Override
    public User getUserByUserName(String userName){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        Predicate userNamePredicate = cb.equal(root.get("username"), userName);
        cq.where(userNamePredicate);
        TypedQuery<User> query = entityManager.createQuery(cq);
        return query.getSingleResult();
    }
    @Override
    @Transactional
    public User saveUser(User user){
        entityManager.persist(user);
        return user;
    }
    @Override
    @Transactional
    public User updateUser(User user){
        entityManager.merge(user);
        return user;
    }
    @Override
    @Transactional
    public User deleteUser(User user){
        if (entityManager.contains(user)) {
            entityManager.remove(user);
        } else {
            entityManager.remove(entityManager.merge(user));
        }
        return user;
    }
}
