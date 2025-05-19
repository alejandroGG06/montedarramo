package com.montederramo.gestionhorarios.controllers;

import com.montederramo.gestionhorarios.dto.Jornada;
import com.montederramo.gestionhorarios.services.JornadaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador que maneja las operaciones CRUD relacionadas con los jornadas.
 * Permite realizar acciones como obtener, crear, actualizar y eliminar jornadas.
 *
 * @author Lucas Villa (k4ts0v@protonmail.com)
 * @since 1.0
 * @version 1.0
 */
@RestController
@RequestMapping("/api/jornadas")
@CrossOrigin
public class JornadaController {

  private final JornadaService jornadaService;

  /**
   * Constructor del controlador.
   *
   * @param jornadaService Servicio para gestionar las operaciones relacionadas con los jornadas.
   * @since 1.0
   */
  @Autowired
  public JornadaController(JornadaService jornadaService) {
    this.jornadaService = jornadaService;
  }

  /**
   * Obtiene una lista de todos los jornadas.
   * Si la lista está vacía, devuelve un estado 204 (No Content).
   * De lo contrario, devuelve la lista con un estado 200 (OK).
   *
   * @return Una lista de jornadas en formato JSON o un estado 204 (No Content) si no hay jornadas.
   * @since 1.0
   */
  @GetMapping
  public ResponseEntity<List<Jornada>> obtenerJornadas() {
    List<Jornada> jornadas = jornadaService.obtenerJornadas();
    if (jornadas.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
    }
    return new ResponseEntity<>(jornadas, HttpStatus.OK); // 200 OK
  }

  /**
   * Obtiene una jornada específico por su ID.
   * Si la jornada existe, devuelve un estado 200 (OK).
   * Si no se encuentra la jornada, devuelve un estado 404 (Not Found).
   *
   * @param id El ID de la jornada que se desea obtener.
   * @return El jornada en formato JSON, o un error 404 si no se encuentra.
   * @since 1.0
   */
  @GetMapping("/{id}")
  public ResponseEntity<Jornada> obtenerJornadaPorId(@PathVariable Integer id) {
    Optional<Jornada> jornada = jornadaService.obtenerJornadaPorId(id);
    if (jornada.isPresent()) {
      return new ResponseEntity<>(jornada.get(), HttpStatus.OK); // 200 OK
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
  }

  /**
   * Crea un nuevo jornada.
   * Si la creación es exitosa, devuelve un estado 201 (Created).
   *
   * @param jornada El jornada que se desea crear.
   * @return El jornada creado en formato JSON.
   * @since 1.0
   */
  @PostMapping
  public ResponseEntity<Jornada> crearJornada(@Valid @RequestBody Jornada jornada) {
    Jornada createdJornada = jornadaService.crearJornada(jornada);
    return new ResponseEntity<>(createdJornada, HttpStatus.CREATED); // 201 Created
  }

  /**
   * Actualiza una jornada existente.
   * Si la actualización es exitosa, devuelve la jornada actualizado con un estado 200 (OK).
   * Si la jornada no se encuentra, devuelve un estado 404 (Not Found).
   *
   * @param id El ID de la jornada a actualizar.
   * @param jornada El jornada con la nueva información.
   * @return El jornada actualizado en formato JSON o un error 404 si no se encuentra.
   * @since 1.0
   */
  @PutMapping("/{id}")
  public ResponseEntity<Jornada> actualizarJornada(@Valid @PathVariable Integer id, @RequestBody Jornada jornada) {
    Jornada updatedJornada = jornadaService.actualizarJornada(id, jornada);
    if (updatedJornada != null) {
      return new ResponseEntity<>(updatedJornada, HttpStatus.OK); // 200 OK
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
  }

  /**
   * Elimina una jornada por su ID.
   * Si la jornada se elimina con éxito, devuelve un estado 204 (No Content).
   * Si la jornada no se encuentra, devuelve un estado 404 (Not Found).
   *
   * @param id El ID de la jornada a eliminar.
   * @return Un estado indicando si la operación fue exitosa o no.
   * @since 1.0
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> eliminarJornada(@PathVariable Integer id) {
    if (jornadaService.eliminarJornada(id)) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
  }
}
