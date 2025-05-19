import { Injectable } from '@angular/core';
@Injectable({
  providedIn: 'root'
})
export class EmpleadoService {

  empleado :any = [
    {
      nombre: 'alejandro',
      apellido: 'grajales',
      correo: 'alejandro@montedarramo.com',
      Pass: '1234',
      roll: 'Junior Developer',
      foto: 'assents/princi.png'
    },
    {nombre: 'lucas',
      apellido: 'villa',
      correo: 'lucas@montedarramo.com',
      Pass: '1234',
      roll: 'Junior Developer',
      foto: 'assents/princi.png'}
  ]

  empleados: any = [
    {nombre: 'Carlos', apellido: 'Rodríguez', correo: 'carlos.rodriguez@montedarramo.com',roll:'Senior developer', foto: 'assents/n1.png'},
    {nombre: 'Ana', apellido: 'Pacheco', correo: 'ana.pacheco@montedarramo.com',roll:'Data analyst', foto: 'assents/n1.png'},
    {nombre: 'Camila', apellido: 'Sánchez', correo: 'camila.sanchez@montedarramo.com',roll:"Senior developer", foto: 'assents/n1.png'}
  ];



  private empleadoAutenticado: any = null; // Aquí almacenamos al usuario autenticado

  verificarLogin(usuario: string, password: string): boolean {
    const emplea= this.empleado.find( (empleado: { correo: string; Pass: string; }) => empleado.correo === usuario && empleado.Pass===password);{}
    if(emplea){
      this.empleadoAutenticado=emplea
      return true;
    }
    return false;


  }

  constructor() { }


  getEmpleados() {
    return this.empleados;
  }
  getEmpleado() {
    return this.empleadoAutenticado;
  }
}
