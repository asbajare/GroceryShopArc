package com.cg.gsm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cg.gsm.entities.User;

@Repository
public interface UserDAOInt extends JpaRepository<User, Integer> {

}
