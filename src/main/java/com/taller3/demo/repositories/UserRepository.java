package com.taller3.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.taller3.demo.model.prod.UserApp;

@Repository
public interface UserRepository extends CrudRepository<UserApp, Integer> {

	UserApp findByUsername(String username);

}
