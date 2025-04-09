// logica.service.ts
import { Injectable } from '@angular/core';
import { BehaviorSubject, interval } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class Trabajoservice {
  // Configuración
  private workHours = 8; // Horas de trabajo
  private breakHours = 1; // Horas de descanso
  private workProgress = new BehaviorSubject<number>(0);
  private breakProgress = new BehaviorSubject<number>(0);
  private timer: any;

  private intervaloTrabajo: any;
  private intervaloPausa: any;

  workProgress$ = this.workProgress.asObservable();
  breakProgress$ = this.breakProgress.asObservable();

  startWork() {
    //this.resetProgress();
    this.clearTrabajo();
    this.timer = interval(1000).subscribe(() => {
      if (this.workProgress.value < this.workHours * 3600) {
        this.workProgress.next(this.workProgress.value + 1);
      } else {
        this.stop();
      }
    });
  }

  private clearTrabajo(){
    clearInterval(this.intervaloTrabajo)
    this.intervaloTrabajo=null;
  }


  startBreak() {
      //this.resetProgress();
    this.clearPause();
    this.timer = interval(1000).subscribe(() => {
      if (this.breakProgress.value < this.breakHours * 3600) {
        this.breakProgress.next(this.breakProgress.value + 1);
      } else {
        this.stop();
      }
    });
  }

  private clearPause(){
    clearInterval(this.intervaloPausa);
    this.intervaloPausa=null;
  }

  stop() {
    if (this.timer) {
      this.timer.unsubscribe();
    }
  }

  resetProgress() {
    this.workProgress.next(0);
    this.breakProgress.next(0);
  }

  getRemainingWorkHours(): number {
    const totalWorkSeconds = this.workHours * 3600; // Total de segundos de trabajo
    const elapsedWorkSeconds = this.workProgress.value; // Segundos trabajados
    return Math.max(0, (totalWorkSeconds - elapsedWorkSeconds) / 3600); // Devuelve horas restantes
  }

  getRemainingBreakHours(): number {
    const totalBreakSeconds = this.breakHours * 3600; // Total de segundos de descanso
    const elapsedBreakSeconds = this.breakProgress.value; // Segundos de descanso
    return Math.max(0, (totalBreakSeconds - elapsedBreakSeconds) / 3600); // Devuelve horas restantes
  }
}
