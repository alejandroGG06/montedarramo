package com.montederramo.gestionhorarios.repositories;

import com.montederramo.gestionhorarios.dto.Reunion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Lucas V. (k4ts0v@protonmail.com)
 * @version 1.0
 * @project montedarramo
 */
@Repository
public interface ReunionRepository extends JpaRepository<Reunion, String> {
  public Optional<Reunion> findReunionById(int id);
  void deleteReunionById(int id);
}
