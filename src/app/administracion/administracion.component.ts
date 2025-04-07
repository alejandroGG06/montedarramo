import { Component } from '@angular/core';
import { HeadComponent } from '../principal/head/head.component';
import { NgFor } from '@angular/common';
import { EmpleadoService } from '../principal/empelados/empleadosservice';

@Component({
  selector: 'app-administracion',
  imports: [HeadComponent, NgFor],
  templateUrl: './administracion.component.html',
  styleUrl: './administracion.component.css',
})
export class AdministracionComponent {
  empleados: any = [];

  constructor(private empleadoService: EmpleadoService) {}

  ngOnInit(): void {
    this.empleados = this.empleadoService.getEmpleados(); // Obtener los empleados del servicio
  }
}
