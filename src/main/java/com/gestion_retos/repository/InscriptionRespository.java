package com.gestion_retos.repository;

import com.gestion_retos.model.Inscription;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InscriptionRespository extends JpaRepository<@NonNull Inscription,@NonNull Long> {
}
