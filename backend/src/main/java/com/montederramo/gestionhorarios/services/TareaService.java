package com.montederramo.gestionhorarios.services;
import com.montederramo.gestionhorarios.dto.Tarea;
import com.montederramo.gestionhorarios.repositories.TareaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio encargado de gestionar las operaciones relacionadas con las tareas.
 * Incluye métodos para crear, obtener, actualizar y eliminar tareas.
 * @author Lucas Villa (k4ts0v@protonmail.com)
 * @since 1.0
 * @version 1.0
 */
@Service
@Transactional // Esta anotación se pone para indicar que deben realizarse transacciones para las operaciones indicadas.
public class TareaService {

  private final TareaRepository tareaRepository;

  /**
   * Constructor para inyección de dependencias.
   *
   * @param tareaRepository El repositorio de tareas para interactuar con los datos.
   * @since 1.0
   */
  @Autowired
  public TareaService(TareaRepository tareaRepository) {
    this.tareaRepository = tareaRepository;
  }

  /**
   * Crea una nueva tarea en el sistema.
   *
   * @param tarea El objeto tarea que se desea crear.
   * @return La tarea creado, incluyendo el ID asignado.
   * @since 1.0
   */
  public Tarea crearTarea(Tarea tarea) {
    return (Tarea) tareaRepository.save(tarea);
  }

  /**
   * Obtiene todas las tareas almacenadas en la base de datos.
   *
   * @return Una lista con todas las tareas.
   * @since 1.0
   */
  public List<Tarea> obtenerTareas() {
    return tareaRepository.findAll();
  }

  /**
   * Obtiene una tarea por su ID.
   *
   * @param id El identificador de la tarea a buscar.
   * @return Un objeto Optional que puede contener la tarea si se encuentra, o estar vacío si no existe.
   * @since 1.0
   */
  public Optional<Tarea> obtenerTareaPorId(Integer id) {
    return tareaRepository.findTareaById(id);
  }

  /**
   * Actualiza la información de una tarea existente.
   *
   * @param id El identificador de la tarea a actualizar.
   * @param tarea El objeto tarea con la nueva información.
   * @return La tarea actualizado, o lanza una excepción si la tarea no existe.
   * @throws RuntimeException Si la tarea con el ID proporcionado no se encuentra.
   * @since 1.0
   */
  public Tarea actualizarTarea(Integer id, Tarea tarea) {
    if (tareaRepository.findTareaById(id).isPresent()) {
      tarea.setId(id);
      return (Tarea) tareaRepository.save(tarea);
    } else {
      throw new RuntimeException("Tarea no encontrado con id: " + id);
    }
  }

  /**
   * Elimina una tarea por su ID.
   *
   * @param id El identificador de la tarea a eliminar.
   * @return true si la tarea fue eliminado correctamente, false si la tarea no existe.
   * @since 1.0
   */
  public boolean eliminarTarea(Integer id) {
    if (tareaRepository.findTareaById(id).isPresent()) {
      tareaRepository.deleteTareaById(id);
      return true;
    }
    return false;
  }
}
