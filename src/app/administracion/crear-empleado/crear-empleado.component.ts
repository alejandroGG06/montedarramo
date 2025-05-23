import { Component, OnInit } from '@angular/core';
import { Empleadoss } from './empleado.model';
import { EmpleadoService } from './EmpeladoService';
import { FormGroup,FormBuilder,ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-crear-empleado',
  imports: [ReactiveFormsModule,CommonModule],
  templateUrl: './crear-empleado.component.html',
  styleUrl: './crear-empleado.component.css'
})
export class CrearEmpleadoComponent implements OnInit {

    formEmpleado!: FormGroup;

  constructor(  private fb: FormBuilder, private empleadoService: EmpleadoService
  ) {}

  ngOnInit(): void {
    this.formEmpleado = this.fb.group({
      nombre: [''],
      apellido: [''],
      correo: [''],
      rol: [''],
      equipo: [''],
      fotoUrl: ['']
    });
  }

  onSubmit(): void {
    if (this.formEmpleado.valid) {
      this.empleadoService.crearEmpleado(this.formEmpleado.value).subscribe({
        next: res => console.log('Empleado creado:', res),
        error: err => console.error('Error al crear empleado:', err)
      });
    }
  }
}