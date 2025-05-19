package com.montederramo.gestionhorarios.services;
import com.montederramo.gestionhorarios.dto.SesionTrabajo;
import com.montederramo.gestionhorarios.repositories.SesionTrabajoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio encargado de gestionar las operaciones relacionadas con las sesionTrabajos.
 * Incluye métodos para crear, obtener, actualizar y eliminar sesionTrabajos.
 * @author Lucas Villa (k4ts0v@protonmail.com)
 * @since 1.0
 * @version 1.0
 */
@Service
@Transactional // Esta anotación se pone para indicar que deben realizarse transacciones para las operaciones indicadas.
public class SesionTrabajoService {

  private final SesionTrabajoRepository sesionTrabajoRepository;

  /**
   * Constructor para inyección de dependencias.
   *
   * @param sesionTrabajoRepository El repositorio de sesionTrabajos para interactuar con los datos.
   * @since 1.0
   */
  @Autowired
  public SesionTrabajoService(SesionTrabajoRepository sesionTrabajoRepository) {
    this.sesionTrabajoRepository = sesionTrabajoRepository;
  }

  /**
   * Crea una nueva sesionTrabajo en el sistema.
   *
   * @param sesionTrabajo El objeto sesionTrabajo que se desea crear.
   * @return  La sesion de trabajo creada, incluyendo el ID asignado.
   * @since 1.0
   */
  public SesionTrabajo crearSesionTrabajo(SesionTrabajo sesionTrabajo) {
    return (SesionTrabajo) sesionTrabajoRepository.save(sesionTrabajo);
  }

  /**
   * Obtiene todas las sesionTrabajos almacenados en la base de datos.
   *
   * @return Una lista con todas las sesiones de trabajo.
   * @since 1.0
   */
  public List<SesionTrabajo> obtenerSesionTrabajos() {
    return sesionTrabajoRepository.findAll();
  }

  /**
   * Obtiene una sesionTrabajo por su ID.
   *
   * @param id El identificador de la sesionTrabajo a buscar.
   * @return Un objeto Optional que puede contener la sesionTrabajo si se encuentra, o estar vacío si no existe.
   * @since 1.0
   */
  public Optional<SesionTrabajo> obtenerSesionTrabajoPorId(Integer id) {
    return sesionTrabajoRepository.findSesionTrabajoById(id);
  }

  /**
   * Actualiza la información de una sesionTrabajo existente.
   *
   * @param id El identificador de la sesionTrabajo a actualizar.
   * @param sesionTrabajo El objeto sesionTrabajo con la nueva información.
   * @return  La sesion de trabajo actualizada, o lanza una excepción si la sesionTrabajo no existe.
   * @throws RuntimeException Si la sesionTrabajo con el ID proporcionado no se encuentra.
   * @since 1.0
   */
  public SesionTrabajo actualizarSesionTrabajo(Integer id, SesionTrabajo sesionTrabajo) {
    if (sesionTrabajoRepository.findSesionTrabajoById(id).isPresent()) {
      sesionTrabajo.setId(id);
      return (SesionTrabajo) sesionTrabajoRepository.save(sesionTrabajo);
    } else {
      throw new RuntimeException("SesionTrabajo no encontrado con id: " + id);
    }
  }

  /**
   * Elimina una sesionTrabajo por su ID.
   *
   * @param id El identificador de la sesionTrabajo a eliminar.
   * @return true si la sesionTrabajo fue eliminado correctamente, false si la sesionTrabajo no existe.
   * @since 1.0
   */
  public boolean eliminarSesionTrabajo(Integer id) {
    if (sesionTrabajoRepository.findSesionTrabajoById(id).isPresent()) {
      sesionTrabajoRepository.deleteSesionTrabajoById(id);
      return true;
    }
    return false;
  }
}
