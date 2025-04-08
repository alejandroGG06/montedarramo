import { Routes } from '@angular/router';
import { provideRouter } from '@angular/router';
import { SesionComponent } from './sesion/sesion.component';
import { empleadoscomponent } from './principal/empelados/body.component';
import { AdministracionComponent } from './administracion/administracion.component';
import { ReunionesComponent } from './reuniones/reuniones.component';

export const routes: Routes = [
    { path: 'sesion', component: SesionComponent },
    { path: 'empleados', component: empleadoscomponent },
    { path: 'administracion', component: AdministracionComponent },
    { path: 'reuniones', component: ReunionesComponent },
    { path: '', redirectTo: '/sesion', pathMatch: 'full' } // Redirección por defecto
];

export const appRoutingProviders = [
    provideRouter(routes)
];
