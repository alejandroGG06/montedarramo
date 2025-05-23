import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

/**
 * Servicio para interactuar con una instancia de MySQL en la nube a través de una API REST.
 * Este servicio proporciona métodos para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * en la base de datos MySQL mediante llamadas HTTP.
 */
@Injectable({
  providedIn: 'root' // Disponible en toda la aplicación sin necesidad de incluirlo en providers
})
export class DatabaseService {
  // URL base para todas las llamadas API.
  private apiUrl = 'https://gestionhorarios/api'; // TODO: Cambiar esto por la URL de la API.

  // Inyección del servicio HttpClient para realizar peticiones HTTP
  constructor(private http: HttpClient) { }

  /**
   * Obtiene todos los registros de una tabla
   * @param table Nombre de la tabla a consultar
   * @returns Observable con un array de registros
   */
  getAll(table: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/${table}`);
  }

  /**
   * Obtiene un registro específico por su ID
   * @param table Nombre de la tabla
   * @param id Identificador único del registro
   * @returns Observable con el registro solicitado
   */
  getOne(table: string, id: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/${table}/${id}`);
  }

  /**
   * Crea un nuevo registro en la tabla especificada
   * @param table Nombre de la tabla
   * @param data Datos a guardar en el nuevo registro
   * @returns Observable con el ID del registro creado
   */
  create(table: string, data: any): Observable<{ id: string }> {
    return this.http.post<{ id: string }>(`${this.apiUrl}/${table}`, data);
  }

  /**
   * Actualiza un registro existente
   * @param table Nombre de la tabla
   * @param id Identificador único del registro
   * @param data Nuevos datos para actualizar el registro
   * @returns Observable indicando si la operación fue exitosa
   */
  update(table: string, id: string, data: any): Observable<{ success: boolean }> {
    return this.http.put<{ success: boolean }>(`${this.apiUrl}/${table}/${id}`, data);
  }

  /**
   * Elimina un registro de la tabla
   * @param table Nombre de la tabla
   * @param id Identificador único del registro a eliminar
   * @returns Observable indicando si la operación fue exitosa
   */
  delete(table: string, id: string): Observable<{ success: boolean }> {
    return this.http.delete<{ success: boolean }>(`${this.apiUrl}/${table}/${id}`);
  }
}
