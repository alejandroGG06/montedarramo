import { Component, OnInit } from '@angular/core';
import { HeadComponent } from '../principal/head/head.component';
import { CommonModule, NgFor } from '@angular/common';
import { ReunionesService } from './reuniones.service';
import { Reunion } from './reunion.model';
import { CalendarComponent } from '../calendar/calendar.component';
import { isToday } from 'date-fns';

@Component({
  selector: 'app-reuniones',
  standalone: true,
  imports: [HeadComponent, CalendarComponent, CommonModule, NgFor],
  templateUrl: './reuniones.component.html',
  styleUrls: ['./reuniones.component.css']
})
export class ReunionesComponent implements OnInit {
  constructor(private reunionesService: ReunionesService) {}

  reuniones: Reunion[] = [];          // All meetings
  reunionesDiarias: Reunion[] = [];   // Filtered meetings for today

  async ngOnInit(): Promise<void> {
    try {
      this.reuniones = await this.reunionesService.getReuniones();
      this.filtrarReunionesDeHoy();
    } catch (error) {
      console.error('Error loading reuniones:', error);
    }
  }

  filtrarReunionesDeHoy() {
    this.reunionesDiarias = this.reuniones.filter(reunion => {
      const fechaReunion = new Date(reunion.fecha);
      return isToday(fechaReunion);
    });

    this.reunionesDiarias.sort((a, b) => {
      const timeA = this.convertTimeToMinutes(a.hora);
      const timeB = this.convertTimeToMinutes(b.hora);
      return timeA - timeB;
    });
  }

  private convertTimeToMinutes(timeString: string): number {
    const [hours, minutes] = timeString.split(':').map(Number);
    return hours * 60 + minutes;
  }

  // Helper method to format time for display (optional)
  formatTime(timeString: string): string {
    const [hours, minutes] = timeString.split(':');
    return `${hours}:${minutes}`;
  }
}
