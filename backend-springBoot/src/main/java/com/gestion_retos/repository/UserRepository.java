package com.gestion_retos.repository;

import com.gestion_retos.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Long>{
}
