package com.montederramo.gestionhorarios.repositories;

import com.montederramo.gestionhorarios.dto.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Lucas V. (k4ts0v@protonmail.com)
 * @version 1.0
 * @project montedarramo
 */
@Repository
public interface TareaRepository extends JpaRepository<Tarea, String> {
  public Optional<Tarea> findTareaById(int id);
  void deleteTareaById(int id);
}
