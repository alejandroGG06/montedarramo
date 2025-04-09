import { Component, OnInit, OnDestroy } from '@angular/core';
import { HeadComponent } from '../principal/head/head.component';
import { CommonModule, NgFor } from '@angular/common';
import { ReunionesService } from './reuniones.service';
import { Reunion } from './reunion.model';
import { CalendarComponent } from '../calendar/calendar.component';
import { isToday } from 'date-fns';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-reuniones',
  standalone: true,
  imports: [HeadComponent, CalendarComponent, CommonModule, NgFor],
  templateUrl: './reuniones.component.html',
  styleUrls: ['./reuniones.component.css']
})
export class ReunionesComponent implements OnInit, OnDestroy {
  // Suscripción para gestionar cambios reactivos en reuniones
  private reunionesSub: Subscription | null = null;

  // Lista de reuniones filtradas solo para el día actual
  reunionesDiarias: Reunion[] = [];

  constructor(private reunionesService: ReunionesService) {}

  ngOnInit(): void {
    // Suscripción al observable, que emite actualizaciones de las reuniones
    this.reunionesSub = this.reunionesService.reuniones$.subscribe(reuniones => {
      this.reunionesDiarias = this.filtrarReunionesDeHoy(reuniones);
    });

    // Obtención de las reuniones iniciales (por si aún no han sido cargadas)
    this.reunionesService.getReuniones().then(() => {
      // Las reuniones serán emitidas automáticamente al observable
    });
  }

  // Cancelar la suscripción al destruir el componente para evitar fugas de memoria
  ngOnDestroy(): void {
    if (this.reunionesSub) {
      this.reunionesSub.unsubscribe();
    }
  }

  // Filtrar reuniones que ocurren hoy
  private filtrarReunionesDeHoy(reuniones: Reunion[]): Reunion[] {
    const reunionesDeHoy = reuniones.filter(reunion => {
      const fechaReunion = new Date(reunion.fecha);
      return isToday(fechaReunion);
    });

    // Ordenar cronológicamente por hora
    return reunionesDeHoy.sort((a, b) => {
      const timeA = this.convertTimeToMinutes(a.hora);
      const timeB = this.convertTimeToMinutes(b.hora);
      return timeA - timeB;
    });
  }

  // Convertir HH:MM a minutos para ordenar
  private convertTimeToMinutes(timeString: string): number {
    const [hours, minutes] = timeString.split(':').map(Number);
    return hours * 60 + minutes;
  }

  // Formatear la hora
  formatTime(timeString: string): string {
    const [hours24, minutes] = timeString.split(':').map(Number);

  // Convertir a formato 12h
  const hours12 = hours24 % 12 === 0 ? 12 : hours24 % 12;
  const ampm = hours24 < 12 ? 'AM' : 'PM';

  // Ajustar formato con ceros a la izquierda
  const paddedHours = hours12.toString().padStart(2, '0');
  const paddedMinutes = minutes.toString().padStart(2, '0');

  return `${paddedHours}:${paddedMinutes} ${ampm}`;
  }

  // Eliminar una reunión al hacer clic sobre ella
async eliminarReunionClick(reunionId: number): Promise<void> {
  try {
    // Llamada al servicio para eliminar por ID.
    await this.reunionesService.eliminarReunion(reunionId);
  } catch (error) {
    console.error('Error al eliminar reunión:', error);
  }
}

}
