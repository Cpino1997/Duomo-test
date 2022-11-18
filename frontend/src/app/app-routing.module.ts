import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { ComunaComponent } from './contenido/comunas/comuna/comuna.component';
import { ContenidoComponent } from './contenido/contenido.component';
import { AgregarPersonaComponent } from './contenido/personas/agregar-persona/agregar-persona.component';
import { PersonaComponent } from './contenido/personas/persona/persona.component';
import { RegionComponent } from './contenido/regiones/region/region.component';
import { ErrorComponent } from './error/error.component';

const routes: Routes = [
  { path: 'home', component: ContenidoComponent },
  { path: 'login', component: LoginComponent },
  { path: 'error404', component: ErrorComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'comunas/:id', component: ComunaComponent },
  { path: 'region/:id', component: RegionComponent },
  { path: 'persona/:id', component: PersonaComponent },
  { path: 'agregar-persona', component: AgregarPersonaComponent },
  { path: '**', redirectTo: 'error404', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
