import { Injectable } from "@angular/core";
import { Reunion } from "./reunion.model";
import { BehaviorSubject } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ReunionesService {
  private reuniones: Reunion[] = [];
  private reunionesSubject = new BehaviorSubject<Reunion[]>([]);
  reuniones$ = this.reunionesSubject.asObservable();

  constructor() {}

  private actualizarReuniones() {
    this.reunionesSubject.next([...this.reuniones]);
  }

  getReuniones(): Promise<Reunion[]> {
    return new Promise(resolve => {
      setTimeout(() => {
        resolve([...this.reuniones]);
      }, 200);
    });
  }

  crearReunion(reunion: Omit<Reunion, 'id'>): Promise<Reunion> {
    return new Promise((resolve) => {
      setTimeout(() => {
        if (!this.validarFormatoHora(reunion.hora)) {
          throw new Error('Formato de hora inválido. Use HH:MM');
        }
        const nuevaReunion: Reunion = {
          ...reunion,
          id: this.generarIdUnico(),
        };
        this.reuniones.push(nuevaReunion);
        this.actualizarReuniones();
        resolve(nuevaReunion);
      }, 200);
    });
  }

  eliminarReunion(id: number): Promise<void> {
    return new Promise((resolve) => {
      setTimeout(() => {
        this.reuniones = this.reuniones.filter(r => r.id !== id);
        this.actualizarReuniones();
        resolve();
      }, 200);
    });
  }

  private generarIdUnico(): number {
    return Math.random();
  }

  private validarFormatoHora(hora: string): boolean {
    const regex = /^([01]\d|2[0-3]):([0-5]\d)$/;
    return regex.test(hora);
  }
}
