package com.montederramo.gestionhorarios.dto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import java.time.LocalTime;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "sesiones_descanso")
public class SesionDescanso {

  @jakarta.persistence.Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "id_jornada")
  private Integer idJornada;

  @Column(name = "tiempo_inicio")
  private LocalTime tiempoInicio;

  @Column(name = "tiempo_fin")
  private LocalTime tiempoFin;

  public void setId(Integer id) {
    this.id = id;
  }
}
