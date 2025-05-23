// src/app/services/empleado.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Empleados } from './empleado.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmpleadoService {
  private apiUrl = 'http://34.175.211.12:8080/api/empleados';

  constructor(private http: HttpClient) {}

  obtenerEmpleados(): Observable<Empleados[]> {
    return this.http.get<Empleados[]>(this.apiUrl);
  }

  obtenerEmpleadoPorId(id: number): Observable<Empleados> {
    return this.http.get<Empleados>(`${this.apiUrl}/${id}`);
  }

  crearEmpleado(empleado: Empleados): Observable<Empleados> {
    return this.http.post<Empleados>(this.apiUrl, empleado);
  }

  actualizarEmpleado(id: number, empleado: Empleados): Observable<Empleados> {
    return this.http.put<Empleados>(`${this.apiUrl}/${id}`, empleado);
  }

  eliminarEmpleado(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
