import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MenuComponent } from './menu/menu.component';
import { ErrorComponent } from './error/error.component';
import { ContenidoComponent } from './contenido/contenido.component';
import { LoginComponent } from './auth/login/login.component';
import { httpInterceptorProviders } from './_helpers/http.interceptor';
import { PersonasComponent } from './contenido/personas/personas.component';
import { ComunasComponent } from './contenido/comunas/comunas.component';
import { RegionesComponent } from './contenido/regiones/regiones.component';
import { ComunaComponent } from './contenido/comunas/comuna/comuna.component';
import { RegionComponent } from './contenido/regiones/region/region.component';
import { PersonaComponent } from './contenido/personas/persona/persona.component';
import { AgregarPersonaComponent } from './contenido/personas/agregar-persona/agregar-persona.component';
import { Error403Component } from './contenido/error403/error403.component';


@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    ErrorComponent,
    ContenidoComponent,
    LoginComponent,
    PersonasComponent,
    ComunasComponent,
    RegionesComponent,
    ComunaComponent,
    RegionComponent,
    PersonaComponent,
    AgregarPersonaComponent,
    Error403Component
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [httpInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
