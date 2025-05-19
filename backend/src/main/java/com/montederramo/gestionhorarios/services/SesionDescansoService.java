package com.montederramo.gestionhorarios.services;
import com.montederramo.gestionhorarios.dto.SesionDescanso;
import com.montederramo.gestionhorarios.repositories.SesionDescansoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio encargado de gestionar las operaciones relacionadas con las sesionDescansos.
 * Incluye métodos para crear, obtener, actualizar y eliminar sesionDescansos.
 * @author Lucas Villa (k4ts0v@protonmail.com)
 * @since 1.0
 * @version 1.0
 */
@Service
@Transactional // Esta anotación se pone para indicar que deben realizarse transacciones para las operaciones indicadas.
public class SesionDescansoService {

  private final SesionDescansoRepository sesionDescansoRepository;

  /**
   * Constructor para inyección de dependencias.
   *
   * @param sesionDescansoRepository El repositorio de sesionDescansos para interactuar con los datos.
   * @since 1.0
   */
  @Autowired
  public SesionDescansoService(SesionDescansoRepository sesionDescansoRepository) {
    this.sesionDescansoRepository = sesionDescansoRepository;
  }

  /**
   * Crea una nueva sesionDescanso en el sistema.
   *
   * @param sesionDescanso El objeto sesionDescanso que se desea crear.
   * @return  La sesion de descanso creada, incluyendo el ID asignado.
   * @since 1.0
   */
  public SesionDescanso crearSesionDescanso(SesionDescanso sesionDescanso) {
    return (SesionDescanso) sesionDescansoRepository.save(sesionDescanso);
  }

  /**
   * Obtiene todas las sesionDescansos almacenados en la base de datos.
   *
   * @return Una lista con todas las sesiones de descanso.
   * @since 1.0
   */
  public List<SesionDescanso> obtenerSesionDescansos() {
    return sesionDescansoRepository.findAll();
  }

  /**
   * Obtiene una sesionDescanso por su ID.
   *
   * @param id El identificador de la sesionDescanso a buscar.
   * @return Un objeto Optional que puede contener la sesionDescanso si se encuentra, o estar vacío si no existe.
   * @since 1.0
   */
  public Optional<SesionDescanso> obtenerSesionDescansoPorId(Integer id) {
    return sesionDescansoRepository.findSesionDescansoById(id);
  }

  /**
   * Actualiza la información de una sesionDescanso existente.
   *
   * @param id El identificador de la sesionDescanso a actualizar.
   * @param sesionDescanso El objeto sesionDescanso con la nueva información.
   * @return  La sesion de descanso actualizada, o lanza una excepción si la sesionDescanso no existe.
   * @throws RuntimeException Si la sesionDescanso con el ID proporcionado no se encuentra.
   * @since 1.0
   */
  public SesionDescanso actualizarSesionDescanso(Integer id, SesionDescanso sesionDescanso) {
    if (sesionDescansoRepository.findSesionDescansoById(id).isPresent()) {
      sesionDescanso.setId(id);
      return (SesionDescanso) sesionDescansoRepository.save(sesionDescanso);
    } else {
      throw new RuntimeException("SesionDescanso no encontrado con id: " + id);
    }
  }

  /**
   * Elimina una sesionDescanso por su ID.
   *
   * @param id El identificador de la sesionDescanso a eliminar.
   * @return true si la sesionDescanso fue eliminado correctamente, false si la sesionDescanso no existe.
   * @since 1.0
   */
  public boolean eliminarSesionDescanso(Integer id) {
    if (sesionDescansoRepository.findSesionDescansoById(id).isPresent()) {
      sesionDescansoRepository.deleteSesionDescansoById(id);
      return true;
    }
    return false;
  }
}
