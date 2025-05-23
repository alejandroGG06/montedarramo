// src/app/services/empleado.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Empleadoss } from './empleado.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmpleadoService {
  private apiUrl = 'http://34.175.211.12:8080/api/empleados';

  constructor(private http: HttpClient) {}

  obtenerEmpleados(): Observable<Empleadoss[]> {
    return this.http.get<Empleadoss[]>(this.apiUrl);
  }

  obtenerEmpleadoPorId(id: number): Observable<Empleadoss> {
    return this.http.get<Empleadoss>(`${this.apiUrl}/${id}`);
  }

  crearEmpleado(empleado: Empleadoss): Observable<Empleadoss> {
    return this.http.post<Empleadoss>(this.apiUrl, empleado);
  }

  actualizarEmpleado(id: number, empleado: Empleadoss): Observable<Empleadoss> {
    return this.http.put<Empleadoss>(`${this.apiUrl}/${id}`, empleado);
  }

  eliminarEmpleado(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
