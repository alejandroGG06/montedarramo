// Importaciones de Angular y librerías externas
import { Component, signal, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { addDays, addMonths, format, getDaysInMonth, getDay, startOfMonth, isToday, isSameMonth, isSameDay } from 'date-fns';
import { FormsModule } from '@angular/forms';
import { ReunionesService } from '../reuniones/reuniones.service';

// Interfaz que define la estructura de un evento en el calendario
interface EventoCalendario {
  id: number;
  titulo: string;
  fecha: Date;
  color: string;
}

@Component({
  selector: 'app-calendar',
  standalone: true,
  imports: [CommonModule, FormsModule], // Módulos necesarios
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.css']
})
export class CalendarComponent implements OnInit {
  // Señales reactivas para manejar el estado del componente
  fechaActual = signal(new Date()); // Fecha actual
  mesActual = signal(new Date()); // Mes que se está mostrando
  eventos = signal<EventoCalendario[]>([]); // Lista de eventos/reuniones
  fechaSeleccionada = signal<Date | null>(null); // Fecha seleccionada por el usuario
  nuevoTituloReunion = signal(''); // Título de nueva reunión (formulario)
  horaReunion = signal(''); // Hora de nueva reunión (formulario)

  // Días de la semana ordenados empezando por Lunes
  diasSemana = ['Lun', 'Mar', 'Mié', 'Jue', 'Vie', 'Sáb', 'Dom'];

  constructor(private reunionesService: ReunionesService) {}

  // Al inicializar el componente, cargamos los eventos
  ngOnInit() {
    this.cargarEventos();
  }

  /**
   * Carga los eventos/reuniones desde el servicio
   * y los transforma al formato que necesita el calendario
   */
  async cargarEventos() {
    try {
      const reuniones = await this.reunionesService.getReuniones();
      const eventos = reuniones.map(reunion => ({
        id: reunion.id,
        titulo: reunion.titulo,
        fecha: new Date(reunion.fecha),
        hora: reunion.hora,
        color: this.generarColorAleatorio() // Asigna un color único
      }));
      this.eventos.set(eventos);
    } catch (error) {
      console.error('Error al cargar reuniones:', error);
    }
  }

  /**
   * Genera un color aleatorio para los eventos
   * @returns Código hexadecimal de color
   */
  generarColorAleatorio(): string {
    const colores = ['#007AFF', '#FF2D55', '#34C759', '#FF9500', '#AF52DE', '#5856D6'];
    return colores[Math.floor(Math.random() * colores.length)];
  }

  // Calcula cuántos días tiene el mes actual
  get diasEnMes() {
    const fecha = this.mesActual();
    return getDaysInMonth(fecha);
  }

  /**
   * Calcula qué día de la semana es el primer día del mes
   * Ajustado para que la semana empiece en Lunes (0)
   */
  get diaInicioSemana() {
    const fecha = startOfMonth(this.mesActual());
    return (getDay(fecha) + 6) % 7; // Ajuste para que Lunes sea 0
  }

  /**
   * Genera la estructura de semanas para el mes actual
   * @returns Array de semanas con los días del mes
   */
  get semanas() {
    const dias = [];
    const diasEnMes = this.diasEnMes;
    const diaInicio = this.diaInicioSemana;

    // Añadir días vacíos para alinear el primer día del mes
    for (let i = 0; i < diaInicio; i++) {
      dias.push(null);
    }

    // Añadir todos los días del mes
    for (let i = 1; i <= diasEnMes; i++) {
      const fecha = new Date(
        this.mesActual().getFullYear(),
        this.mesActual().getMonth(),
        i
      );
      dias.push(fecha);
    }

    // Dividir en semanas de 7 días
    const semanas = [];
    for (let i = 0; i < dias.length; i += 7) {
      semanas.push(dias.slice(i, i + 7));
    }

    // Completar la última semana si no tiene 7 días
    const ultimaSemana = semanas[semanas.length - 1];
    if (ultimaSemana.length < 7) {
      const necesarios = 7 - ultimaSemana.length;
      for (let i = 0; i < necesarios; i++) {
        ultimaSemana.push(null);
      }
    }

    return semanas;
  }

  // Devuelve el nombre del mes y año actual (ej. "Enero 2023")
  get nombreMes() {
    const meses = ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio',
                  'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'];
    return `${meses[this.mesActual().getMonth()]} ${this.mesActual().getFullYear()}`;
  }

  // Navegación entre meses
  mesAnterior() {
    this.mesActual.set(addMonths(this.mesActual(), -1));
  }

  mesSiguiente() {
    this.mesActual.set(addMonths(this.mesActual(), 1));
  }

  // Selecciona una fecha en el calendario
  seleccionarFecha(fecha: Date) {
    this.fechaSeleccionada.set(fecha);
  }

  /**
   * Crea una nueva reunión
   * Valida que haya título y fecha seleccionada
   */
  async agregarEvento() {
    if (this.nuevoTituloReunion() && this.fechaSeleccionada()) {
      try {
        await this.reunionesService.crearReunion({
          titulo: this.nuevoTituloReunion(),
          fecha: this.fechaSeleccionada()!,
          hora: this.horaReunion(),
        });
        this.cargarEventos(); // Recargar eventos después de añadir
        this.nuevoTituloReunion.set(''); // Limpiar formulario
      } catch (error) {
        console.error('Error al crear reunión:', error);
      }
    }
  }

  // Elimina una reunión por su ID
  async eliminarEvento(id: number) {
    try {
      await this.reunionesService.eliminarReunion(id);
      this.cargarEventos(); // Recargar eventos después de eliminar
    } catch (error) {
      console.error('Error al eliminar reunión:', error);
    }
  }

  /**
   * Filtra los eventos para una fecha específica
   * @param fecha Fecha a filtrar
   * @returns Array de eventos para esa fecha
   */
  getEventosParaFecha(fecha: Date | null) {
    if (!fecha) return [];
    return this.eventos().filter(evento => isSameDay(evento.fecha, fecha));
  }

  // Comprueba si una fecha es hoy
  esHoy(fecha: Date) {
    return isToday(fecha);
  }

  // Comprueba si una fecha está seleccionada
  estaSeleccionado(fecha: Date) {
    return this.fechaSeleccionada() && isSameDay(fecha, this.fechaSeleccionada()!);
  }

  // Comprueba si una fecha pertenece al mes actual
  esMesActual(fecha: Date) {
    return isSameMonth(fecha, this.mesActual());
  }
}
