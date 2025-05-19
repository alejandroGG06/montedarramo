package com.montederramo.gestionhorarios.repositories;

import com.montederramo.gestionhorarios.dto.SesionDescanso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Lucas V. (k4ts0v@protonmail.com)
 * @version 1.0
 * @project montedarramo
 */
@Repository
public interface SesionDescansoRepository extends JpaRepository<SesionDescanso, String> {
  public Optional<SesionDescanso> findSesionDescansoById(int id);
  void deleteSesionDescansoById(int id);
}
