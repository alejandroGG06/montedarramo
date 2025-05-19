package com.montederramo.gestionhorarios.dto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "reuniones")
public class Reunion {

  @jakarta.persistence.Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "fecha")
  private DateTime fecha;

  @column(name = "titulo")
  private String titulo;

  public void setId(Integer id) {
    this.id = id;
  }
}
