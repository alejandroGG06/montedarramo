import { Component } from '@angular/core';
import { HeadComponent } from "../head/head.component";
import { NgFor, NgIfContext } from '@angular/common';
import { TrabajoComponent } from "../trabajo/trabajo.component";

@Component({
  selector: 'app-body',
  imports: [HeadComponent, NgFor, TrabajoComponent],
  templateUrl: './body.component.html',
  styleUrl: './body.component.css'
})
export class empleadoscomponent {

    empleado={
      nombre: 'alejandro',
      apellido: 'grajales',
      correo: 'alejandro@montedarramo.com',
      Pass:'1234',
      roll: 'Junior Developer',
      foto: 'assents/princi.png',
    };

      getEmpleados() {
        return this.empleado;
      }
    
      verificarLogin(usuario: string, password: string):boolean {
        return this.empleado.correo===usuario&&this.empleado.Pass===password;
      }


  empleados=[
    {nombre: 'carlos', apellido: 'rodriguez', correo:'carlosR@montedarramo.com',foto:'assents/n1.png'},
    {nombre: 'ana', apellido: 'pacheco', correo:'anaP@montedarramo.com',foto:'assents/n1.png'},
    {nombre: 'camila', apellido: 'sanchez', correo:'camiS@montedarramo.com',foto:'assents/n1.png'},
  ];

}
