import { Component, OnInit } from '@angular/core';
import { Persona } from 'src/app/models/persona.model';
import { AuthService } from 'src/app/_services/auth.service';
import { PersonaService } from 'src/app/_services/personas.service';
import { StorageService } from 'src/app/_services/storage.service';

@Component({
  selector: 'app-personas',
  templateUrl: './personas.component.html',
  styleUrls: ['./personas.component.css']
})
export class PersonasComponent implements OnInit {


  personas?: Persona[];
  currentPersona: Persona = {};
  id = -1;
  nombre = '';
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];

  constructor(private personaService: PersonaService,private authService: AuthService, private storageService: StorageService) { }

  ngOnInit(): void {
    this.recibirPersonas();
    if (this.storageService.isLoggedIn()) {
      this.isLoggedIn = true;
      this.roles = this.storageService.getUser().roles;
    }
  }
  recibirPersonas(): void {
    this.personaService.getAll()
    .subscribe({
      next: (data) => {
        this.personas = data;
        console.log(data);
      },
      error: (e) => console.error(e)
    });
  }
  refreshList(): void {
    this.recibirPersonas();
    this.currentPersona = {};
    this.id = -1;
  }

  setActive(persona: Persona, id: number): void {
    this.currentPersona = persona;
    this.id = id;
  }
  removerPersona(idPersona : number): void {
    this.personaService.delete(idPersona)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.refreshList();
        },
        error: (e) => console.error(e)
      });
  }
}
