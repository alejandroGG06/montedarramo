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

  workHours: any = 8; // Puedes cambiar esto según tu lógica
  breakHours: any = 1; // 

  formatHoras(minutosTotales: number): string {
    const horas = Math.floor(minutosTotales / 60);
    const minutos = Math.floor(minutosTotales % 60);
    const horasStr = horas < 10 ? '0' + horas : horas.toString();
    const minutosStr = minutos < 10 ? '0' + minutos : minutos.toString();
    return `${horasStr}:${minutosStr}`;
  }
  

  constructor(private empleadoService: EmpleadoService, private Trabajoservice:Trabajoservice) { }
  
  
  
  ngOnInit(): void {
    this.empleados = this.empleadoService.getEmpleados(); // Obtener los empleados del servicio
    this.empleado=this.empleadoService.getEmpleado();

    this.Trabajoservice.workProgress$.subscribe(() => {
      const horas = this.Trabajoservice.getRemainingWorkHours();
      const minutosTotales = horas * 60;
      this.workHours = this.formatHoras(minutosTotales);
    });
    

    // Suscribirse a los cambios en el progreso de descanso
    this.Trabajoservice.breakProgress$.subscribe(() => {
      const horas = this.Trabajoservice.getRemainingBreakHours();
      const minutosTotales = horas * 60;
      this.breakHours = this.formatHoras(minutosTotales);
});
  
    
  }


}
