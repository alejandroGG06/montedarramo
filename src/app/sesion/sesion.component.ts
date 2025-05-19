import { Component } from '@angular/core';
import { Router } from '@angular/router'; // Importa Router
import { FormsModule } from '@angular/forms'; // Importa FormsModule
import { EmpleadoService } from '../principal/empelados/empleadosservice';

@Component({
  selector: 'app-sesion',
  imports: [FormsModule],
  templateUrl: './sesion.component.html',
  styleUrl: './sesion.component.css'
})
export class SesionComponent {
  usuario = '';
  password = '';
  error = '';



  constructor(private router: Router,  private empleadoService: EmpleadoService) {}

  login() {
    const valido = this.empleadoService.verificarLogin(this.usuario, this.password);

    if (valido) {
      this.router.navigate(['/empleados']);
    } else {
      this.error = 'Credenciales incorrectas';
    }
  }

}


