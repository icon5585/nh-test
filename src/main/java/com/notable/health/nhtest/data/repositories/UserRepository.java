package com.notable.health.nhtest.data.repositories;

import javax.persistence.Entity;

import org.springframework.data.repository.CrudRepository;

import com.notable.health.nhtest.data.entities.User;

/**
 * JPA enabled CRUD repository for {@link User} {@link Entity} objects
 * 
 * @author Hank DeDona
 *
 */
public interface UserRepository extends CrudRepository<User, Integer>{

}
