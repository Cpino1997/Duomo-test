import { Component, ElementRef, ViewChild } from '@angular/core';
import { FormGroup, FormControl, Validators} from '@angular/forms';
import { Comuna } from 'src/app/models/comuna.model';
import { Region } from 'src/app/models/region.model';
import { AuthService } from 'src/app/_services/auth.service';
import { ComunaService } from 'src/app/_services/comunas.service';
import { PersonaService } from 'src/app/_services/personas.service';
import { RegionesService } from 'src/app/_services/regiones.service';
import { StorageService } from 'src/app/_services/storage.service';
@Component({
  selector: 'app-agregar-persona',
  templateUrl: './agregar-persona.component.html',
  styleUrls: ['./agregar-persona.component.css']
})
export class AgregarPersonaComponent{

  verSeleccion: string        = '';
  comunas?: Comuna[];
  currentComuna: Comuna = {};
  regiones?: Region[];
  currentRegion: Region = {};
  nombre = '';
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];

  constructor(private regionService: RegionesService,private authService: AuthService, private storageService: StorageService, private comunaService: ComunaService, private personaService: PersonaService) { }


  submitted = false;
  form = new FormGroup({
    nombre: new FormControl('', [Validators.required, Validators.minLength(3),Validators.maxLength(10)]),
    apellido: new FormControl('', [Validators.required, Validators.minLength(4),Validators.maxLength(10)]),
    correo: new FormControl('', [Validators.required, Validators.email]),
    nacimiento: new FormControl('',[Validators.required,Validators.nullValidator]),
    telefono: new FormControl('',[Validators.required, Validators.minLength(9), Validators.maxLength(9)]),
    idRegion: new FormControl('',[Validators.required, Validators.nullValidator]),
    idComuna: new FormControl('',[Validators.required])
  });


  ngOnInit(): void {
    this.recibirRegiones();
    if (this.storageService.isLoggedIn()) {
      this.isLoggedIn = true;
      this.roles = this.storageService.getUser().roles;
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
  setActive( idReg: any): void {
    alert(idReg)
  }
  get f(){
    return this.form.controls;
  }
  @ViewChild("idReg")
  idReg!: ElementRef;

  getValue() {
    this.recibirComunas(this.idReg.nativeElement.value);
  }

  newPersona() : void {
    this.submitted = false;
  }

  submit(){
      this.personaService.create(this.form.value)
        .subscribe({
          next: (res) => {
            console.log(res);
            this.submitted = true;
          },
          error: (e) => console.error(e)
        });
    }

}
