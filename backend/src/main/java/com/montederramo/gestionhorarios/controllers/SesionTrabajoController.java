package com.montederramo.gestionhorarios.controllers;

import com.montederramo.gestionhorarios.dto.SesionTrabajo;
import com.montederramo.gestionhorarios.services.SesionTrabajoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador que maneja las operaciones CRUD relacionadas con las sesiones de trabajo.
 * Permite realizar acciones como obtener, crear, actualizar y eliminar sesiones de trabajo.
 *
 * @author Lucas Villa (k4ts0v@protonmail.com)
 * @since 1.0
 * @version 1.0
 */
@RestController
@RequestMapping("/api/sesionesTrabajo")
@CrossOrigin
public class SesionTrabajoController {

  private final SesionTrabajoService sesionTrabajoService;

  /**
   * Constructor del controlador.
   *
   * @param sesionTrabajoService Servicio para gestionar las operaciones relacionadas con las sesiones de trabajo.
   * @since 1.0
   */
  @Autowired
  public SesionTrabajoController(SesionTrabajoService sesionTrabajoService) {
    this.sesionTrabajoService = sesionTrabajoService;
  }

  /**
   * Obtiene una lista de todos las sesiones de trabajo.
   * Si la lista está vacía, devuelve un estado 204 (No Content).
   * De lo contrario, devuelve la lista con un estado 200 (OK).
   *
   * @return Una lista de sesiones de trabajo en formato JSON o un estado 204 (No Content) si no hay sesiones de trabajo.
   * @since 1.0
   */
  @GetMapping
  public ResponseEntity<List<SesionTrabajo>> obtenerSesionTrabajos() {
    List<SesionTrabajo> sesionesTrabajo = sesionTrabajoService.obtenerSesionTrabajos();
    if (sesionesTrabajo.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
    }
    return new ResponseEntity<>(sesionesTrabajo, HttpStatus.OK); // 200 OK
  }

  /**
   * Obtiene una sesion de trabajo específico por su ID.
   * Si la sesion de trabajo existe, devuelve un estado 200 (OK).
   * Si no se encuentra la sesion de trabajo, devuelve un estado 404 (Not Found).
   *
   * @param id El ID de la sesion de trabajo que se desea obtener.
   * @return La sesion de trabajo en formato JSON, o un error 404 si no se encuentra.
   * @since 1.0
   */
  @GetMapping("/{id}")
  public ResponseEntity<SesionTrabajo> obtenerSesionTrabajoPorId(@PathVariable Integer id) {
    Optional<SesionTrabajo> sesionTrabajo = sesionTrabajoService.obtenerSesionTrabajoPorId(id);
    if (sesionTrabajo.isPresent()) {
      return new ResponseEntity<>(sesionTrabajo.get(), HttpStatus.OK); // 200 OK
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
  }

  /**
   * Crea un nuevo sesion de trabajo.
   * Si la creación es exitosa, devuelve un estado 201 (Created).
   *
   * @param sesion de trabajo La sesion de trabajo que se desea crear.
   * @return La sesion de trabajo creado en formato JSON.
   * @since 1.0
   */
  @PostMapping
  public ResponseEntity<SesionTrabajo> crearSesionTrabajo(@Valid @RequestBody SesionTrabajo sesionTrabajo) {
    SesionTrabajo createdSesionTrabajo = sesionTrabajoService.crearSesionTrabajo(sesionTrabajo);
    return new ResponseEntity<>(createdSesionTrabajo, HttpStatus.CREATED); // 201 Created
  }

  /**
   * Actualiza una sesion de trabajo existente.
   * Si la actualización es exitosa, devuelve la sesion de trabajo actualizado con un estado 200 (OK).
   * Si la sesion de trabajo no se encuentra, devuelve un estado 404 (Not Found).
   *
   * @param id El ID de la sesion de trabajo a actualizar.
   * @param sesion de trabajo La sesion de trabajo con la nueva información.
   * @return La sesion de trabajo actualizado en formato JSON o un error 404 si no se encuentra.
   * @since 1.0
   */
  @PutMapping("/{id}")
  public ResponseEntity<SesionTrabajo> actualizarSesionTrabajo(@Valid @PathVariable Integer id, @RequestBody SesionTrabajo sesionTrabajo) {
    SesionTrabajo updatedSesionTrabajo = sesionTrabajoService.actualizarSesionTrabajo(id, sesion de trabajo);
    if (updatedSesionTrabajo != null) {
      return new ResponseEntity<>(updatedSesionTrabajo, HttpStatus.OK); // 200 OK
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
  }

  /**
   * Elimina una sesion de trabajo por su ID.
   * Si la sesion de trabajo se elimina con éxito, devuelve un estado 204 (No Content).
   * Si la sesion de trabajo no se encuentra, devuelve un estado 404 (Not Found).
   *
   * @param id El ID de la sesion de trabajo a eliminar.
   * @return Un estado indicando si la operación fue exitosa o no.
   * @since 1.0
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> eliminarSesionTrabajo(@PathVariable Integer id) {
    if (sesionTrabajoService.eliminarSesionTrabajo(id)) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
  }
}
