import { Component } from '@angular/core';
import { HeadComponent } from '../../principal/head/head.component';
import { CalendarComponent } from "../../reunion/calendar/calendar.component";
import { SesionComponent } from "../../sesion/sesion.component";
import { TareasComponent } from "../tareas/tareas.component";
@Component({
  selector: 'app-horarios',
  imports: [HeadComponent, CalendarComponent, SesionComponent, TareasComponent],
  templateUrl: './horarios.component.html',
  styleUrl: './horarios.component.css'
})
export class HorariosComponent {

}
