package com.montederramo.gestionhorarios.controllers;

import com.montederramo.gestionhorarios.dto.Tarea;
import com.montederramo.gestionhorarios.services.TareaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador que maneja las operaciones CRUD relacionadas con los tareas.
 * Permite realizar acciones como obtener, crear, actualizar y eliminar tareas.
 *
 * @author Lucas Villa (k4ts0v@protonmail.com)
 * @since 1.0
 * @version 1.0
 */
@RestController
@RequestMapping("/api/tareas")
@CrossOrigin
public class TareaController {

  private final TareaService tareaService;

  /**
   * Constructor del controlador.
   *
   * @param tareaService Servicio para gestionar las operaciones relacionadas con los tareas.
   * @since 1.0
   */
  @Autowired
  public TareaController(TareaService tareaService) {
    this.tareaService = tareaService;
  }

  /**
   * Obtiene una lista de todos los tareas.
   * Si la lista está vacía, devuelve un estado 204 (No Content).
   * De lo contrario, devuelve la lista con un estado 200 (OK).
   *
   * @return Una lista de tareas en formato JSON o un estado 204 (No Content) si no hay tareas.
   * @since 1.0
   */
  @GetMapping
  public ResponseEntity<List<Tarea>> obtenerTareas() {
    List<Tarea> tareas = tareaService.obtenerTareas();
    if (tareas.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
    }
    return new ResponseEntity<>(tareas, HttpStatus.OK); // 200 OK
  }

  /**
   * Obtiene una tarea específico por su ID.
   * Si la tarea existe, devuelve un estado 200 (OK).
   * Si no se encuentra la tarea, devuelve un estado 404 (Not Found).
   *
   * @param id El ID de la tarea que se desea obtener.
   * @return El tarea en formato JSON, o un error 404 si no se encuentra.
   * @since 1.0
   */
  @GetMapping("/{id}")
  public ResponseEntity<Tarea> obtenerTareaPorId(@PathVariable Integer id) {
    Optional<Tarea> tarea = tareaService.obtenerTareaPorId(id);
    if (tarea.isPresent()) {
      return new ResponseEntity<>(tarea.get(), HttpStatus.OK); // 200 OK
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
  }

  /**
   * Crea un nuevo tarea.
   * Si la creación es exitosa, devuelve un estado 201 (Created).
   *
   * @param tarea El tarea que se desea crear.
   * @return El tarea creado en formato JSON.
   * @since 1.0
   */
  @PostMapping
  public ResponseEntity<Tarea> crearTarea(@Valid @RequestBody Tarea tarea) {
    Tarea createdTarea = tareaService.crearTarea(tarea);
    return new ResponseEntity<>(createdTarea, HttpStatus.CREATED); // 201 Created
  }

  /**
   * Actualiza una tarea existente.
   * Si la actualización es exitosa, devuelve la tarea actualizado con un estado 200 (OK).
   * Si la tarea no se encuentra, devuelve un estado 404 (Not Found).
   *
   * @param id El ID de la tarea a actualizar.
   * @param tarea El tarea con la nueva información.
   * @return El tarea actualizado en formato JSON o un error 404 si no se encuentra.
   * @since 1.0
   */
  @PutMapping("/{id}")
  public ResponseEntity<Tarea> actualizarTarea(@Valid @PathVariable Integer id, @RequestBody Tarea tarea) {
    Tarea updatedTarea = tareaService.actualizarTarea(id, tarea);
    if (updatedTarea != null) {
      return new ResponseEntity<>(updatedTarea, HttpStatus.OK); // 200 OK
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
  }

  /**
   * Elimina una tarea por su ID.
   * Si la tarea se elimina con éxito, devuelve un estado 204 (No Content).
   * Si la tarea no se encuentra, devuelve un estado 404 (Not Found).
   *
   * @param id El ID de la tarea a eliminar.
   * @return Un estado indicando si la operación fue exitosa o no.
   * @since 1.0
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> eliminarTarea(@PathVariable Integer id) {
    if (tareaService.eliminarTarea(id)) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
  }
}
