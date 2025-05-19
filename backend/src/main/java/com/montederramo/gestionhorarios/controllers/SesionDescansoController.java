package com.montederramo.gestionhorarios.controllers;

import com.montederramo.gestionhorarios.dto.SesionDescanso;
import com.montederramo.gestionhorarios.services.SesionDescansoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador que maneja las operaciones CRUD relacionadas con las sesiones de descanso.
 * Permite realizar acciones como obtener, crear, actualizar y eliminar sesiones de descanso.
 *
 * @author Lucas Villa (k4ts0v@protonmail.com)
 * @since 1.0
 * @version 1.0
 */
@RestController
@RequestMapping("/api/sesionesDescanso")
@CrossOrigin
public class SesionDescansoController {

  private final SesionDescansoService sesionDescansoService;

  /**
   * Constructor del controlador.
   *
   * @param sesionDescansoService Servicio para gestionar las operaciones relacionadas con las sesiones de descanso.
   * @since 1.0
   */
  @Autowired
  public SesionDescansoController(SesionDescansoService sesionDescansoService) {
    this.sesionDescansoService = sesionDescansoService;
  }

  /**
   * Obtiene una lista de todos las sesiones de descanso.
   * Si la lista está vacía, devuelve un estado 204 (No Content).
   * De lo contrario, devuelve la lista con un estado 200 (OK).
   *
   * @return Una lista de sesiones de descanso en formato JSON o un estado 204 (No Content) si no hay sesiones de descanso.
   * @since 1.0
   */
  @GetMapping
  public ResponseEntity<List<SesionDescanso>> obtenerSesionDescansos() {
    List<SesionDescanso> sesionesDescanso = sesionDescansoService.obtenerSesionDescansos();
    if (sesionesDescanso.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
    }
    return new ResponseEntity<>(sesionesDescanso, HttpStatus.OK); // 200 OK
  }

  /**
   * Obtiene una sesion de descanso específico por su ID.
   * Si la sesion de descanso existe, devuelve un estado 200 (OK).
   * Si no se encuentra la sesion de descanso, devuelve un estado 404 (Not Found).
   *
   * @param id El ID de la sesion de descanso que se desea obtener.
   * @return La sesion de descanso en formato JSON, o un error 404 si no se encuentra.
   * @since 1.0
   */
  @GetMapping("/{id}")
  public ResponseEntity<SesionDescanso> obtenerSesionDescansoPorId(@PathVariable Integer id) {
    Optional<SesionDescanso> sesionDescanso = sesionDescansoService.obtenerSesionDescansoPorId(id);
    if (sesionDescanso.isPresent()) {
      return new ResponseEntity<>(sesionDescanso.get(), HttpStatus.OK); // 200 OK
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
  }

  /**
   * Crea un nuevo sesion de descanso.
   * Si la creación es exitosa, devuelve un estado 201 (Created).
   *
   * @param sesion de descanso La sesion de descanso que se desea crear.
   * @return La sesion de descanso creado en formato JSON.
   * @since 1.0
   */
  @PostMapping
  public ResponseEntity<SesionDescanso> crearSesionDescanso(@Valid @RequestBody SesionDescanso sesionDescanso) {
    SesionDescanso createdSesionDescanso = sesionDescansoService.crearSesionDescanso(sesionDescanso);
    return new ResponseEntity<>(createdSesionDescanso, HttpStatus.CREATED); // 201 Created
  }

  /**
   * Actualiza una sesion de descanso existente.
   * Si la actualización es exitosa, devuelve la sesion de descanso actualizado con un estado 200 (OK).
   * Si la sesion de descanso no se encuentra, devuelve un estado 404 (Not Found).
   *
   * @param id El ID de la sesion de descanso a actualizar.
   * @param sesion de descanso La sesion de descanso con la nueva información.
   * @return La sesion de descanso actualizado en formato JSON o un error 404 si no se encuentra.
   * @since 1.0
   */
  @PutMapping("/{id}")
  public ResponseEntity<SesionDescanso> actualizarSesionDescanso(@Valid @PathVariable Integer id, @RequestBody SesionDescanso sesionDescanso) {
    SesionDescanso updatedSesionDescanso = sesionDescansoService.actualizarSesionDescanso(id, sesionDescanso);
    if (updatedSesionDescanso != null) {
      return new ResponseEntity<>(updatedSesionDescanso, HttpStatus.OK); // 200 OK
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
  }

  /**
   * Elimina una sesion de descanso por su ID.
   * Si la sesion de descanso se elimina con éxito, devuelve un estado 204 (No Content).
   * Si la sesion de descanso no se encuentra, devuelve un estado 404 (Not Found).
   *
   * @param id El ID de la sesion de descanso a eliminar.
   * @return Un estado indicando si la operación fue exitosa o no.
   * @since 1.0
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> eliminarSesionDescanso(@PathVariable Integer id) {
    if (sesionDescansoService.eliminarSesionDescanso(id)) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
  }
}
