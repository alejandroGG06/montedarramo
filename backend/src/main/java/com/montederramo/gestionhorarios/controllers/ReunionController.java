package com.montederramo.gestionhorarios.controllers;

import com.montederramo.gestionhorarios.dto.Reunion;
import com.montederramo.gestionhorarios.services.ReunionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador que maneja las operaciones CRUD relacionadas con los reuniones.
 * Permite realizar acciones como obtener, crear, actualizar y eliminar reuniones.
 *
 * @author Lucas Villa (k4ts0v@protonmail.com)
 * @since 1.0
 * @version 1.0
 */
@RestController
@RequestMapping("/api/reuniones")
@CrossOrigin
public class ReunionController {

  private final ReunionService reunionService;

  /**
   * Constructor del controlador.
   *
   * @param reunionService Servicio para gestionar las operaciones relacionadas con los reuniones.
   * @since 1.0
   */
  @Autowired
  public ReunionController(ReunionService reunionService) {
    this.reunionService = reunionService;
  }

  /**
   * Obtiene una lista de todos los reuniones.
   * Si la lista está vacía, devuelve un estado 204 (No Content).
   * De lo contrario, devuelve la lista con un estado 200 (OK).
   *
   * @return Una lista de reuniones en formato JSON o un estado 204 (No Content) si no hay reuniones.
   * @since 1.0
   */
  @GetMapping
  public ResponseEntity<List<Reunion>> obtenerReunions() {
    List<Reunion> reuniones = reunionService.obtenerReunions();
    if (reuniones.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
    }
    return new ResponseEntity<>(reuniones, HttpStatus.OK); // 200 OK
  }

  /**
   * Obtiene una reunion específico por su ID.
   * Si la reunion existe, devuelve un estado 200 (OK).
   * Si no se encuentra la reunion, devuelve un estado 404 (Not Found).
   *
   * @param id El ID de la reunion que se desea obtener.
   * @return El reunion en formato JSON, o un error 404 si no se encuentra.
   * @since 1.0
   */
  @GetMapping("/{id}")
  public ResponseEntity<Reunion> obtenerReunionPorId(@PathVariable Integer id) {
    Optional<Reunion> reunion = reunionService.obtenerReunionPorId(id);
    if (reunion.isPresent()) {
      return new ResponseEntity<>(reunion.get(), HttpStatus.OK); // 200 OK
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
  }

  /**
   * Crea un nuevo reunion.
   * Si la creación es exitosa, devuelve un estado 201 (Created).
   *
   * @param reunion El reunion que se desea crear.
   * @return El reunion creado en formato JSON.
   * @since 1.0
   */
  @PostMapping
  public ResponseEntity<Reunion> crearReunion(@Valid @RequestBody Reunion reunion) {
    Reunion createdReunion = reunionService.crearReunion(reunion);
    return new ResponseEntity<>(createdReunion, HttpStatus.CREATED); // 201 Created
  }

  /**
   * Actualiza una reunion existente.
   * Si la actualización es exitosa, devuelve la reunion actualizado con un estado 200 (OK).
   * Si la reunion no se encuentra, devuelve un estado 404 (Not Found).
   *
   * @param id El ID de la reunion a actualizar.
   * @param reunion El reunion con la nueva información.
   * @return El reunion actualizado en formato JSON o un error 404 si no se encuentra.
   * @since 1.0
   */
  @PutMapping("/{id}")
  public ResponseEntity<Reunion> actualizarReunion(@Valid @PathVariable Integer id, @RequestBody Reunion reunion) {
    Reunion updatedReunion = reunionService.actualizarReunion(id, reunion);
    if (updatedReunion != null) {
      return new ResponseEntity<>(updatedReunion, HttpStatus.OK); // 200 OK
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
  }

  /**
   * Elimina una reunion por su ID.
   * Si la reunion se elimina con éxito, devuelve un estado 204 (No Content).
   * Si la reunion no se encuentra, devuelve un estado 404 (Not Found).
   *
   * @param id El ID de la reunion a eliminar.
   * @return Un estado indicando si la operación fue exitosa o no.
   * @since 1.0
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> eliminarReunion(@PathVariable Integer id) {
    if (reunionService.eliminarReunion(id)) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
  }
}
