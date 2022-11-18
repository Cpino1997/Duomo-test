import { Component, ElementRef, Input, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Comuna } from 'src/app/models/comuna.model';
import { Persona } from 'src/app/models/persona.model';
import { Region } from 'src/app/models/region.model';
import { AuthService } from 'src/app/_services/auth.service';
import { ComunaService } from 'src/app/_services/comunas.service';
import { PersonaService } from 'src/app/_services/personas.service';
import { RegionesService } from 'src/app/_services/regiones.service';
import { StorageService } from 'src/app/_services/storage.service';

@Component({
  selector: 'app-persona',
  templateUrl: './persona.component.html',
  styleUrls: ['./persona.component.css']
})
export class PersonaComponent {

  comunas?: Comuna[];

  regiones?: Region[];
  @Input() viewMode = false;

  @Input() currentPersona: Persona = {

  };

  message = '';
  isLoggedIn = false;
  roles: string[] = [];

  constructor(
    private personaService: PersonaService,
    private route: ActivatedRoute, private storageService: StorageService, private regionService : RegionesService, private comunaService : ComunaService) { }

  ngOnInit(): void {
    this.recibirRegiones();
    if (this.storageService.isLoggedIn()) {
      this.isLoggedIn = true;
      this.roles = this.storageService.getUser().roles;
    }
    if (!this.viewMode) {
      this.message = '';
      this.getPersona(this.route.snapshot.params["id"]);
    }
  }


  recibirRegiones(): void {
    this.regionService.getAll()
    .subscribe({
      next: (data) => {
        this.regiones = data;
        console.log(data);
      },
      error: (e) => console.error(e)
    });
  }
  getPersona(id: number): void {
    this.personaService.get(id)
      .subscribe({
        next: (data) => {
          this.currentPersona = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }
  @ViewChild("idReg")
  idReg!: ElementRef;

  getValue() {
    this.recibirComunas(this.idReg.nativeElement.value);
  }
  recibirComunas(idRegion: number): void {
    this.comunaService.getByRegion(idRegion)
    .subscribe({
      next: (data) => {
        this.comunas = data;
        console.log(data);
      },
      error: (e) => console.error(e)
    });
  }
  update(): void {
    this.message = '';

    this.personaService.update(this.currentPersona.id, this.currentPersona)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.message = res.message ? res.message : 'Esta persona a sido modificado con exito!';
        },
        error: (e) => console.error(e)
      });
  }
  delete(): void {
    this.personaService.delete(this.currentPersona.id)
      .subscribe({
        next: (res) => {
          console.log(res);
          window.location.reload();
        },
        error: (e) => console.error(e)
      });
  }

}
