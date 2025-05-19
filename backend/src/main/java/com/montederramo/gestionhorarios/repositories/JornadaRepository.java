package com.montederramo.gestionhorarios.repositories;

import com.montederramo.gestionhorarios.dto.Jornada; // Use dto.Jornada
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Lucas V. (k4ts0v@protonmail.com)
 * @version 1.0
 * @project montedarramo
 */
@Repository
public interface JornadaRepository extends JpaRepository<Jornada, String> {
  public Optional<Jornada> findJornadaById(int id);
  void deleteJornadaById(int id);
}
