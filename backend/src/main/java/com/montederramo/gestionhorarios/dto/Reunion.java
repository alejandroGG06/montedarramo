package com.montederramo.gestionhorarios.dto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "reuniones")
public class Reunion {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "fecha")
  private Date fecha;

  @Column(name = "titulo")
  private String titulo;

  public void setId(Integer id) {
    this.id = id;
  }
}
