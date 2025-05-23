import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { DatabaseService } from '../../api/database.service'; // Importa el servicio genérico para acceso a la API
import { Empleados } from './empleado.model'; // Modelo TypeScript para los empleados

@Component({
  selector: 'app-crear-empleado',
  imports: [ReactiveFormsModule, CommonModule], // Importa los módulos necesarios para formularios y directivas comunes
  templateUrl: './crear-empleado.component.html',
  styleUrl: './crear-empleado.component.css'
})
export class CrearEmpleadoComponent implements OnInit {

  // Instancia del formulario reactivo para crear empleados
  formEmpleado!: FormGroup;

  // Inyección de dependencias:
  // - FormBuilder: para construir el formulario reactivo
  // - DatabaseService: servicio genérico para operaciones CRUD vía API REST
  constructor(
    private fb: FormBuilder,
    private databaseService: DatabaseService
  ) {}

  /**
   * Método del ciclo de vida de Angular. Se ejecuta al inicializar el componente.
   * Aquí se construye el formulario con los campos y valores iniciales.
   */
  ngOnInit(): void {
    this.formEmpleado = this.fb.group({
      nombre: [''],     // Campo para el nombre del empleado
      apellido: [''],   // Campo para el apellido
      correo: [''],     // Campo para el correo electrónico
      rol: [''],        // Campo para el rol del empleado
      equipo: [''],     // Campo para el equipo o departamento
      fotoUrl: ['']     // Campo para la URL de la foto
    });
  }

  /**
   * Acción que se ejecuta al enviar el formulario.
   * Si el formulario es válido, llama al método "create" del servicio genérico,
   * especificando la tabla 'empleados' y usando el modelo tipado.
   */
  onSubmit(): void {
    if (this.formEmpleado.valid) {
      // Llama al método "create" del servicio genérico para crear un nuevo registro de empleado
      this.databaseService.create('empleados', this.formEmpleado.value).subscribe({
        next: (res: any) => console.log('Empleado creado:', res), // Éxito: muestra el resultado en consola
        error: (err: any) => console.error('Error al crear empleado:', err) // Error: muestra el error en consola
      });
    }
  }
}