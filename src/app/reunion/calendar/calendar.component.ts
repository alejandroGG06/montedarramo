import { Component, signal, OnInit, ViewEncapsulation } from '@angular/core';
import { CommonModule } from '@angular/common';
import { addMonths, getDaysInMonth, getDay, startOfMonth, isToday, isSameMonth, isSameDay } from 'date-fns';
import { FormsModule } from '@angular/forms';
import { ReunionesService } from '../reuniones/reuniones.service';

interface EventoCalendario {
  id: number;
  titulo: string;
  fecha: Date;
  color: string;
}

@Component({
  selector: 'app-calendar',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.css'],
  encapsulation: ViewEncapsulation.None
})

export class CalendarComponent implements OnInit {
  fechaActual = signal(new Date());
  mesActual = signal(new Date());
  eventos = signal<EventoCalendario[]>([]);
  fechaSeleccionada = signal<Date | null>(null);
  nuevoTituloReunion = signal('');
  horaReunion = signal('');

  // Días de la semana empezando por Lunes
  diasSemana = ['Lun', 'Mar', 'Mié', 'Jue', 'Vie', 'Sáb', 'Dom'];

  constructor(private reunionesService: ReunionesService) {}

  ngOnInit() {
    this.cargarEventos();
  }

  async cargarEventos() {
    try {
      const reuniones = await this.reunionesService.getReuniones();
      const eventos = reuniones.map(reunion => ({
        id: reunion.id,
        titulo: reunion.titulo,
        fecha: new Date(reunion.fecha),
        hora: reunion.hora,
        color: this.generarColorAleatorio()
      }));
      this.eventos.set(eventos);
    } catch (error) {
      console.error('Error al cargar reuniones:', error);
    }
  }

  generarColorAleatorio(): string {
    const colores = ['#007AFF', '#FF2D55', '#34C759', '#FF9500', '#AF52DE', '#5856D6'];
    return colores[Math.floor(Math.random() * colores.length)];
  }

  get diasEnMes() {
    const fecha = this.mesActual();
    return getDaysInMonth(fecha);
  }

  get diaInicioSemana() {
    const fecha = startOfMonth(this.mesActual());
    return (getDay(fecha) + 6) % 7; // Convertir Domingo (0) a 6, Lunes (1) a 0, etc.
  }

  get semanas() {
    const dias = [];
    const diasEnMes = this.diasEnMes;
    const diaInicio = this.diaInicioSemana;
    const hoy = new Date();

    // Añadir celdas vacías para días antes del 1er día del mes
    for (let i = 0; i < diaInicio; i++) {
      dias.push(null);
    }

    // Añadir días del mes
    for (let i = 1; i <= diasEnMes; i++) {
      const fecha = new Date(
        this.mesActual().getFullYear(),
        this.mesActual().getMonth(),
        i
      );
      dias.push(fecha);
    }

    // Agrupar en semanas
    const semanas = [];
    for (let i = 0; i < dias.length; i += 7) {
      semanas.push(dias.slice(i, i + 7));
    }

    // Asegurar que la última semana tenga 7 días
    const ultimaSemana = semanas[semanas.length - 1];
    if (ultimaSemana.length < 7) {
      const necesarios = 7 - ultimaSemana.length;
      for (let i = 0; i < necesarios; i++) {
        ultimaSemana.push(null);
      }
    }

    return semanas;
  }

  get nombreMes() {
    const meses = ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio',
                  'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'];
    return `${meses[this.mesActual().getMonth()]} ${this.mesActual().getFullYear()}`;
  }

  mesAnterior() {
    this.mesActual.set(addMonths(this.mesActual(), -1));
  }

  mesSiguiente() {
    this.mesActual.set(addMonths(this.mesActual(), 1));
  }

  seleccionarFecha(fecha: Date) {
    this.fechaSeleccionada.set(fecha!);
  }

  async agregarEvento() {
    if (this.nuevoTituloReunion() && this.fechaSeleccionada()) {
      try {
        await this.reunionesService.crearReunion({
          titulo: this.nuevoTituloReunion(),
          fecha: this.fechaSeleccionada()!,
          hora: this.horaReunion(),
        });
        this.cargarEventos(); // Recargar eventos después de añadir uno nuevo
        this.nuevoTituloReunion.set('');
      } catch (error) {
        console.error('Error al crear reunión:', error);
      }
    }
  }

  async eliminarEvento(id: number) {
    try {
      await this.reunionesService.eliminarReunion(id);
      this.cargarEventos(); // Recargar eventos después de eliminar
    } catch (error) {
      console.error('Error al eliminar reunión:', error);
    }
  }

  getEventosParaFecha(fecha: Date | null) {
    if (!fecha) return [];
    return this.eventos().filter(evento => isSameDay(evento.fecha, fecha));
  }

  esHoy(fecha: Date) {
    return isToday(fecha);
  }

  estaSeleccionado(fecha: Date) {
    return this.fechaSeleccionada() && isSameDay(fecha, this.fechaSeleccionada()!);
  }

  esMesActual(fecha: Date) {
    return isSameMonth(fecha, this.mesActual());
  }
}
