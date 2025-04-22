import { Injectable } from "@angular/core";
import { Reunion } from "./reunion.model";

@Injectable({
  providedIn: 'root'
})
export class ReunionesService {
  constructor() {}

  private reuniones: Reunion[] = [];

  getReuniones(): Promise<Reunion[]> {
    return new Promise((resolve) => {
      // Simulate API call delay
      setTimeout(() => {
        resolve(this.reuniones);
      }, 200);
    });
  }

  crearReunion(reunion: Omit<Reunion, 'id'>): Promise<Reunion> {
    return new Promise((resolve) => {
      // Simulate API call delay
      setTimeout(() => {
        // Validate hora format (HH:MM)
        if (!this.validarFormatoHora(reunion.hora)) {
          throw new Error('Formato de hora inválido. Use HH:MM');
        }

        const nuevaReunion: Reunion = {
          ...reunion,
          id: this.generarIdUnico(),
        };
        this.reuniones.push(nuevaReunion);
        resolve(nuevaReunion);
      }, 200);
    });
  }

  eliminarReunion(id: number): Promise<void> {
    return new Promise((resolve) => {
      // Simulate API call delay
      setTimeout(() => {
        this.reuniones = this.reuniones.filter(r => r.id !== id);
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
