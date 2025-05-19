package com.montederramo.gestionhorarios.dto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "empleados")
public class Empleado {

  @jakarta.persistence.Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "nombre")
  private String nombre;

  @Column(name = "apellido")
  private String apellido;

  @Column(name = "correo")
  private String correo;

  @Column(name = "rol")
  private String rol;

  @Column(name = "equipo")
  private String equipo;

  @Column(name = "foto_url")
  private String fotoUrl;

  public void setId(Integer id) {
    this.id = id;
  }
}

