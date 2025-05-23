package com.montederramo.gestionhorarios.services;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.montederramo.gestionhorarios.dto.Empleado;
import com.montederramo.gestionhorarios.repositories.EmpleadoRepository;

import jakarta.transaction.Transactional;
/**
 * Servicio encargado de gestionar las operaciones relacionadas con los empleados.
 * Incluye métodos para crear, obtener, actualizar y eliminar empleados.
 * @author Lucas Villa (k4ts0v@protonmail.com)
 * @since 1.0
 * @version 1.0
 */
@Service
@Transactional // Esta anotación se pone para indicar que deben realizarse transacciones para las operaciones indicadas.
public class EmpleadoService {

  private final EmpleadoRepository empleadoRepository;

  /**
   * Constructor para inyección de dependencias.
   *
   * @param empleadoRepository El repositorio de empleados para interactuar con los datos.
   * @since 1.0
   */
  @Autowired
  public EmpleadoService(EmpleadoRepository empleadoRepository) {
    this.empleadoRepository = empleadoRepository;
  }

  /**
   * Crea un nuevo empleado en el sistema.
   *
   * @param empleado El objeto empleado que se desea crear.
   * @return El empleado creado, incluyendo el ID asignado.
   * @since 1.0
   */
  public Empleado crearEmpleado(Empleado empleado) {
    return (Empleado) empleadoRepository.save(empleado);
  }

  /**
   * Obtiene todos los empleados almacenados en la base de datos.
   *
   * @return Una lista con todos los empleados.
   * @since 1.0
   */
  public List<Empleado> obtenerEmpleados() {
    return empleadoRepository.findAll();
  }

  /**
   * Obtiene un empleado por su ID.
   *
   * @param id El identificador del empleado a buscar.
   * @return Un objeto Optional que puede contener el empleado si se encuentra, o estar vacío si no existe.
   * @since 1.0
   */
  public Optional<Empleado> obtenerEmpleadoPorId(Integer id) {
    return empleadoRepository.findEmpleadoById(id);
  }

  /**
   * Actualiza la información de un empleado existente.
   *
   * @param id El identificador del empleado a actualizar.
   * @param empleado El objeto empleado con la nueva información.
   * @return El empleado actualizado, o lanza una excepción si el empleado no existe.
   * @throws RuntimeException Si el empleado con el ID proporcionado no se encuentra.
   * @since 1.0
   */
  public Empleado actualizarEmpleado(Integer id, Empleado empleado) {
    if (empleadoRepository.findEmpleadoById(id).isPresent()) {
      empleado.setId(id);
      return (Empleado) empleadoRepository.save(empleado);
    } else {
      throw new RuntimeException("Empleado no encontrado con id: " + id);
    }
  }

  /**
   * Elimina un empleado por su ID.
   *
   * @param id El identificador del empleado a eliminar.
   * @return true si el empleado fue eliminado correctamente, false si el empleado no existe.
   * @since 1.0
   */
  public boolean eliminarEmpleado(Integer id) {
    if (empleadoRepository.findEmpleadoById(id).isPresent()) {
      empleadoRepository.deleteEmpleadoById(id);
      return true;
    }
    return false;
  }
}
