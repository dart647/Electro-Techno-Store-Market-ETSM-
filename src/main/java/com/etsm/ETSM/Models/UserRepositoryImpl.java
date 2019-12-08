/*
 * Copyright (c) 2019. Nikita Smalkov
 */

package com.etsm.ETSM.Models;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class UserRepositoryImpl implements UserRepository {
    private static final AtomicInteger AUTO_ID = new AtomicInteger(0);
    private static Map<Integer,UserEntity> users = new HashMap<>();
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public <S extends UserEntity> S save(S s) {
        return null;
    }

    @Override
    public <S extends UserEntity> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<UserEntity> findById(Long aLong) {
        Session session = sessionFactory.getCurrentSession();
        UserEntity user =
                session.get(UserEntity.class,aLong);
        Optional<UserEntity> foundUser = Optional.of(user);
        return foundUser;
    }

    @Override
    public boolean existsById(Long aLong) {
        return users.containsKey(aLong);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Iterable<UserEntity> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from UserEntity").list();
    }

    @Override
    public Iterable<UserEntity> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return users.size();
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(UserEntity userEntity) {

    }

    @Override
    public void deleteAll(Iterable<? extends UserEntity> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}
