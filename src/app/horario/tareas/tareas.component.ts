import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-tareas',
  standalone: true,
  imports: [CommonModule,FormsModule],
  templateUrl: './tareas.component.html',
  styleUrl: './tareas.component.css'
})
export class TareasComponent {

  statuses = ['todo', 'inProgress', 'done'];

  statusLabels: Record<string, string> = {
    todo: 'Por hacer',
    inProgress: 'En proceso',
    done: 'Hecho'
  };

  tasks = [
    { id: 1, title: 'Diseñar login', status: 'todo' },
    { id: 2, title: 'Conectar base de datos', status: 'inProgress' },
    { id: 3, title: 'Deploy a producción', status: 'done' }
  ];

  newTaskTitles: Record<string, string> = {
    todo: '',
    inProgress: '',
    done: ''
  };

  onInputChange(event: Event, status: string) {
    const input = event.target as HTMLInputElement;
    this.newTaskTitles[status] = input.value;
  }

  addTask(status: string) {
    const title = this.newTaskTitles[status]?.trim();
    if (!title) return;

    const newTask = {
      id: this.tasks.length + 1,
      title,
      status
    };

    this.tasks.push(newTask);
    this.newTaskTitles[status] = '';
  }
  
  getTasksByStatus(status: string) {
    return this.tasks.filter(t => t.status === status);
  }
}
