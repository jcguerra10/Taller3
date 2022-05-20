package com.taller3.demo.repositories;

import com.taller3.demo.model.prod.UserApp;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserApp, Integer> {

	UserApp findByUsername(String username);

}
