import { Routes } from '@angular/router';
import { provideRouter } from '@angular/router';
import { SesionComponent } from './sesion/sesion.component';
import { empleadoscomponent } from './principal/empelados/body.component';

export const routes: Routes = [
    { path: 'sesion', component: SesionComponent },
    { path: 'empleados', component: empleadoscomponent },
    { path: '', redirectTo: '/sesion', pathMatch: 'full' } // Redirección por defecto
];

export const appRoutingProviders = [
    provideRouter(routes)
];
