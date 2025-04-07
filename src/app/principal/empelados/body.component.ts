import { Component } from '@angular/core';
import { HeadComponent } from "../head/head.component";
import { NgFor, NgIf } from '@angular/common';
import { TrabajoComponent } from "../trabajo/trabajo.component";
import { EmpleadoService } from './empleadosservice';

@Component({
  selector: 'app-body',
  imports: [HeadComponent, NgFor,NgIf, TrabajoComponent],
  templateUrl: './body.component.html',
  styleUrl: './body.component.css'
})
export class empleadoscomponent {

  empleados:any=[];

  empleado:any= null;
   

  constructor(private empleadoService: EmpleadoService) { }
  
  
  
  ngOnInit(): void {
    this.empleados = this.empleadoService.getEmpleados(); // Obtener los empleados del servicio
    this.empleado=this.empleadoService.getEmpleado();
    
  }
}
