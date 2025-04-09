import { Component } from '@angular/core';
import { Trabajoservice } from './trabajoservice';

@Component({
  selector: 'app-trabajo',
  imports: [],
  templateUrl: './trabajo.component.html',
  styleUrls: ['./trabajo.component.css'],
})
export class TrabajoComponent {
  workProgress: number = 0;
  breakProgress: number = 0;
  workHours: number = 8; // Horas de trabajo
  breakHours: number = 1; // Horas de descanso

  // Estado de los botones
  botonTrabajoTexto: string = 'Empezar';
  botonPausaTexto: string = 'Pausa';
  trabajoEnCurso: boolean = false;
  descansoEnCurso: boolean = false;

  constructor(private Trabajoservice: Trabajoservice) {
    this.Trabajoservice.workProgress$.subscribe((progress) => {
      this.workProgress = progress;
    });

    this.Trabajoservice.breakProgress$.subscribe((progress) => {
      this.breakProgress = progress;
    });
  }

  // Iniciar jornada de trabajo
  startWork() {
    if (!this.trabajoEnCurso) {
      this.Trabajoservice.startWork();
      this.botonTrabajoTexto = 'Trabajando'; // Cambiar texto a "Trabajando"
      this.trabajoEnCurso = true;
    } else {
      // Si está trabajando y se pulsa otra vez, significa que se pausó
      this.Trabajoservice.stop();
      this.botonTrabajoTexto = 'Renaudar'; // Cambiar texto a "Empezar"
      this.trabajoEnCurso = false;
    }
  }

  // Iniciar o pausar descanso
  startBreak() {
    if (!this.descansoEnCurso) {
      this.Trabajoservice.startBreak();
      this.botonPausaTexto = 'Descansando'; // Cambiar texto a "Descansando"
      this.descansoEnCurso = true;
    } else {
      // Si está descansando y se pulsa otra vez, significa que se reanuda
      this.Trabajoservice.stop();
      this.botonPausaTexto = 'Reanudar'; // Cambiar texto a "Reanudar"
      this.descansoEnCurso = false;
    }
  }
}
