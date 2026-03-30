package com.gestion_retos.repository;

import com.gestion_retos.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<User,Long>{
//    List<User> findByActiveTrue(Sort sort);
}
