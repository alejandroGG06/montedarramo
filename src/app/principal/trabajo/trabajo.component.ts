import { Component } from '@angular/core';
import { Trabajoservice } from './trabajoservice';

@Component({
  selector: 'app-trabajo',
  imports: [],
  templateUrl: './trabajo.component.html',
  styleUrl: './trabajo.component.css'
})
export class TrabajoComponent {

  workProgress: number = 0;
  breakProgress: number = 0;
  workHours: number = 8; // Horas de trabajo
  breakHours: number = 1; // Horas de descanso

  constructor(private Trabajoservice:Trabajoservice) {
   
    this.Trabajoservice.workProgress$.subscribe(progress => {
      this.workProgress = progress;
    });

    this.Trabajoservice.breakProgress$.subscribe(progress => {
      this.breakProgress = progress;
    });
  }
  startWork() {
    this.Trabajoservice.startWork();
  }

  startBreak() {
    this.Trabajoservice.startBreak();
  }

}
