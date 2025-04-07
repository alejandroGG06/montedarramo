import { Component } from '@angular/core';
import { HeadComponent } from "../head/head.component";
import { NgFor } from '@angular/common';
import { TrabajoComponent } from "../trabajo/trabajo.component";
import { EmpleadoService } from './empleadosservice';
import { Trabajoservice } from '../trabajo/trabajoservice';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-body',
  imports: [HeadComponent, NgFor, TrabajoComponent,CommonModule],
  templateUrl: './body.component.html',
  styleUrl: './body.component.css'
})
export class empleadoscomponent {

  empleados:any=[];

  empleado:any= null;

  workHours: number = 8; // Puedes cambiar esto según tu lógica
  breakHours: number = 1; // 

   

  constructor(private empleadoService: EmpleadoService, private Trabajoservice:Trabajoservice) { }
  
  
  
  ngOnInit(): void {
    this.empleados = this.empleadoService.getEmpleados(); // Obtener los empleados del servicio
    this.empleado=this.empleadoService.getEmpleado();

    this.Trabajoservice.workProgress$.subscribe(() => {
      this.workHours = this.Trabajoservice.getRemainingWorkHours();
      console.log('Horas de trabajo restantes:', this.workHours);
    });

    // Suscribirse a los cambios en el progreso de descanso
    this.Trabajoservice.breakProgress$.subscribe(() => {
      this.breakHours = this.Trabajoservice.getRemainingBreakHours();
      console.log('Horas de descanso restantes:', this.breakHours);
    });
  
    
  }


}
