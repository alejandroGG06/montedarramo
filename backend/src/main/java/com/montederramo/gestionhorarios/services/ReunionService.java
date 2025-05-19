package com.montederramo.gestionhorarios.services;
import com.montederramo.gestionhorarios.dto.Reunion;
import com.montederramo.gestionhorarios.repositories.ReunionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio encargado de gestionar las operaciones relacionadas con las reuniones.
 * Incluye métodos para crear, obtener, actualizar y eliminar reuniones.
 * @author Lucas Villa (k4ts0v@protonmail.com)
 * @since 1.0
 * @version 1.0
 */
@Service
@Transactional // Esta anotación se pone para indicar que deben realizarse transacciones para las operaciones indicadas.
public class ReunionService {

  private final ReunionRepository reunionRepository;

  /**
   * Constructor para inyección de dependencias.
   *
   * @param reunionRepository El repositorio de reuniones para interactuar con los datos.
   * @since 1.0
   */
  @Autowired
  public ReunionService(ReunionRepository reunionRepository) {
    this.reunionRepository = reunionRepository;
  }

  /**
   * Crea un nuevo reunion en el sistema.
   *
   * @param reunion El objeto reunion que se desea crear.
   * @return La reunion creado, incluyendo el ID asignado.
   * @since 1.0
   */
  public Reunion crearReunion(Reunion reunion) {
    return (Reunion) reunionRepository.save(reunion);
  }

  /**
   * Obtiene todas las reuniones almacenados en la base de datos.
   *
   * @return Una lista con todas las reuniones.
   * @since 1.0
   */
  public List<Reunion> obtenerReunions() {
    return reunionRepository.findAll();
  }

  /**
   * Obtiene una reunion por su ID.
   *
   * @param id El identificador de la reunion a buscar.
   * @return Un objeto Optional que puede contener la reunion si se encuentra, o estar vacío si no existe.
   * @since 1.0
   */
  public Optional<Reunion> obtenerReunionPorId(Integer id) {
    return reunionRepository.findReunionById(id);
  }

  /**
   * Actualiza la información de una reunion existente.
   *
   * @param id El identificador de la reunion a actualizar.
   * @param reunion El objeto reunion con la nueva información.
   * @return La reunion actualizado, o lanza una excepción si la reunion no existe.
   * @throws RuntimeException Si la reunion con el ID proporcionado no se encuentra.
   * @since 1.0
   */
  public Reunion actualizarReunion(Integer id, Reunion reunion) {
    if (reunionRepository.findReunionById(id).isPresent()) {
      reunion.setId(id);
      return (Reunion) reunionRepository.save(reunion);
    } else {
      throw new RuntimeException("Reunion no encontrado con id: " + id);
    }
  }

  /**
   * Elimina una reunion por su ID.
   *
   * @param id El identificador de la reunion a eliminar.
   * @return true si la reunion fue eliminado correctamente, false si la reunion no existe.
   * @since 1.0
   */
  public boolean eliminarReunion(Integer id) {
    if (reunionRepository.findReunionById(id).isPresent()) {
      reunionRepository.deleteReunionById(id);
      return true;
    }
    return false;
  }
}
