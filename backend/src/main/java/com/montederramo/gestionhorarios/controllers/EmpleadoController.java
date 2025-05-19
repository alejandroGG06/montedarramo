package com.montederramo.gestionhorarios.controllers;

import com.montederramo.gestionhorarios.dto.Empleado;
import com.montederramo.gestionhorarios.services.EmpleadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador que maneja las operaciones CRUD relacionadas con los empleados.
 * Permite realizar acciones como obtener, crear, actualizar y eliminar empleados.
 *
 * @author Lucas Villa (k4ts0v@protonmail.com)
 * @since 1.0
 * @version 1.0
 */
@RestController
@RequestMapping("/api/empleados")
@CrossOrigin
public class EmpleadoController {

  private final EmpleadoService empleadoService;

  /**
   * Constructor del controlador.
   *
   * @param empleadoService Servicio para gestionar las operaciones relacionadas con los empleados.
   * @since 1.0
   */
  @Autowired
  public EmpleadoController(EmpleadoService empleadoService) {
    this.empleadoService = empleadoService;
  }

  /**
   * Obtiene una lista de todos los empleados.
   * Si la lista está vacía, devuelve un estado 204 (No Content).
   * De lo contrario, devuelve la lista con un estado 200 (OK).
   *
   * @return Una lista de empleados en formato JSON o un estado 204 (No Content) si no hay empleados.
   * @since 1.0
   */
  @GetMapping
  public ResponseEntity<List<Empleado>> obtenerEmpleados() {
    List<Empleado> empleados = empleadoService.obtenerEmpleados();
    if (empleados.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
    }
    return new ResponseEntity<>(empleados, HttpStatus.OK); // 200 OK
  }

  /**
   * Obtiene un empleado específico por su ID.
   * Si el empleado existe, devuelve un estado 200 (OK).
   * Si no se encuentra el empleado, devuelve un estado 404 (Not Found).
   *
   * @param id El ID del empleado que se desea obtener.
   * @return El empleado en formato JSON, o un error 404 si no se encuentra.
   * @since 1.0
   */
  @GetMapping("/{id}")
  public ResponseEntity<Empleado> obtenerEmpleadoPorId(@PathVariable Integer id) {
    Optional<Empleado> empleado = empleadoService.obtenerEmpleadoPorId(id);
    if (empleado.isPresent()) {
      return new ResponseEntity<>(empleado.get(), HttpStatus.OK); // 200 OK
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
  }

  /**
   * Crea un nuevo empleado.
   * Si la creación es exitosa, devuelve un estado 201 (Created).
   *
   * @param empleado El empleado que se desea crear.
   * @return El empleado creado en formato JSON.
   * @since 1.0
   */
  @PostMapping
  public ResponseEntity<Empleado> crearEmpleado(@Valid @RequestBody Empleado empleado) {
    Empleado createdEmpleado = empleadoService.crearEmpleado(empleado);
    return new ResponseEntity<>(createdEmpleado, HttpStatus.CREATED); // 201 Created
  }

  /**
   * Actualiza un empleado existente.
   * Si la actualización es exitosa, devuelve el empleado actualizado con un estado 200 (OK).
   * Si el empleado no se encuentra, devuelve un estado 404 (Not Found).
   *
   * @param id El ID del empleado a actualizar.
   * @param empleado El empleado con la nueva información.
   * @return El empleado actualizado en formato JSON o un error 404 si no se encuentra.
   * @since 1.0
   */
  @PutMapping("/{id}")
  public ResponseEntity<Empleado> actualizarEmpleado(@Valid @PathVariable Integer id, @RequestBody Empleado empleado) {
    Empleado updatedEmpleado = empleadoService.actualizarEmpleado(id, empleado);
    if (updatedEmpleado != null) {
      return new ResponseEntity<>(updatedEmpleado, HttpStatus.OK); // 200 OK
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
  }

  /**
   * Elimina un empleado por su ID.
   * Si el empleado se elimina con éxito, devuelve un estado 204 (No Content).
   * Si el empleado no se encuentra, devuelve un estado 404 (Not Found).
   *
   * @param id El ID del empleado a eliminar.
   * @return Un estado indicando si la operación fue exitosa o no.
   * @since 1.0
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> eliminarEmpleado(@PathVariable Integer id) {
    if (empleadoService.eliminarEmpleado(id)) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
  }
}
