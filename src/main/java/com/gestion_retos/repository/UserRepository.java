package com.gestion_retos.repository;

import com.gestion_retos.model.User;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<@NonNull User,@NonNull Long>{
    //List<User> findAllByOrderByTotalPointsDesc();
}
