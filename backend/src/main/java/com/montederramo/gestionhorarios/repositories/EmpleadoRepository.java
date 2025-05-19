package com.montederramo.gestionhorarios.repositories;

import com.montederramo.gestionhorarios.dto.Empleado; // Use dto.Empleado
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Lucas V. (k4ts0v@protonmail.com)
 * @version 1.0
 * @project montedarramo
 */
@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, String> {
  public Optional<Empleado> findEmpleadoById(int id);
  void deleteEmpleadoById(int id);
}
