package com.montederramo.gestionhorarios.dto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "tareas")
public class Tarea {

  @jakarta.persistence.Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "fecha")
  private Date fecha;

  @Column(name = "titulo")
  private String titulo;
 
  @Column(name = "estado")
  private String estado;

  public void setId(Integer id) {
    this.id = id;
  }
}
