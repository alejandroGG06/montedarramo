package com.montederramo.gestionhorarios.repositories;

import com.montederramo.gestionhorarios.dto.SesionTrabajo; // Use dto.SesionTrabajo
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Lucas V. (k4ts0v@protonmail.com)
 * @version 1.0
 * @project montedarramo
 */
@Repository
public interface SesionTrabajoRepository extends JpaRepository<SesionTrabajo, String> {
  public Optional<SesionTrabajo> findSesionTrabajoById(int id);
  void deleteSesionTrabajoById(int id);
}
