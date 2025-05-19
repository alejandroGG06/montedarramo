package com.montederramo.gestionhorarios.services;
import com.montederramo.gestionhorarios.dto.Jornada;
import com.montederramo.gestionhorarios.repositories.JornadaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio encargado de gestionar las operaciones relacionadas con las jornadas.
 * Incluye métodos para crear, obtener, actualizar y eliminar jornadas.
 * @author Lucas Villa (k4ts0v@protonmail.com)
 * @since 1.0
 * @version 1.0
 */
@Service
@Transactional // Esta anotación se pone para indicar que deben realizarse transacciones para las operaciones indicadas.
public class JornadaService {

  private final JornadaRepository jornadaRepository;

  /**
   * Constructor para inyección de dependencias.
   *
   * @param jornadaRepository El repositorio de jornadas para interactuar con los datos.
   * @since 1.0
   */
  @Autowired
  public JornadaService(JornadaRepository jornadaRepository) {
    this.jornadaRepository = jornadaRepository;
  }

  /**
   * Crea un nuevo jornada en el sistema.
   *
   * @param jornada El objeto jornada que se desea crear.
   * @return La jornada creado, incluyendo el ID asignado.
   * @since 1.0
   */
  public Jornada crearJornada(Jornada jornada) {
    return (Jornada) jornadaRepository.save(jornada);
  }

  /**
   * Obtiene todas las jornadas almacenados en la base de datos.
   *
   * @return Una lista con todas las jornadas.
   * @since 1.0
   */
  public List<Jornada> obtenerJornadas() {
    return jornadaRepository.findAll();
  }

  /**
   * Obtiene una jornada por su ID.
   *
   * @param id El identificador de la jornada a buscar.
   * @return Un objeto Optional que puede contener la jornada si se encuentra, o estar vacío si no existe.
   * @since 1.0
   */
  public Optional<Jornada> obtenerJornadaPorId(Integer id) {
    return jornadaRepository.findJornadaById(id);
  }

  /**
   * Actualiza la información de una jornada existente.
   *
   * @param id El identificador de la jornada a actualizar.
   * @param jornada El objeto jornada con la nueva información.
   * @return La jornada actualizado, o lanza una excepción si la jornada no existe.
   * @throws RuntimeException Si la jornada con el ID proporcionado no se encuentra.
   * @since 1.0
   */
  public Jornada actualizarJornada(Integer id, Jornada jornada) {
    if (jornadaRepository.findJornadaById(id).isPresent()) {
      jornada.setId(id);
      return (Jornada) jornadaRepository.save(jornada);
    } else {
      throw new RuntimeException("Jornada no encontrado con id: " + id);
    }
  }

  /**
   * Elimina una jornada por su ID.
   *
   * @param id El identificador de la jornada a eliminar.
   * @return true si la jornada fue eliminado correctamente, false si la jornada no existe.
   * @since 1.0
   */
  public boolean eliminarJornada(Integer id) {
    if (jornadaRepository.findJornadaById(id).isPresent()) {
      jornadaRepository.deleteJornadaById(id);
      return true;
    }
    return false;
  }
}
