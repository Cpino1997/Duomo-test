
import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Comuna } from 'src/app/models/comuna.model';
import { AuthService } from 'src/app/_services/auth.service';
import { ComunaService } from 'src/app/_services/comunas.service';
import { RegionesService } from 'src/app/_services/regiones.service';
import { StorageService } from 'src/app/_services/storage.service';

@Component({
  selector: 'app-comuna',
  templateUrl: './comuna.component.html',
  styleUrls: ['./comuna.component.css']
})
export class ComunaComponent implements OnInit {

  isLoggedIn = false;
  isLoginFailed = false;
  roles: string[] = [];

  constructor(
    private comunaService: ComunaService,
    private route: ActivatedRoute,
    private regionService: RegionesService,
    private router: Router,private authService: AuthService, private storageService: StorageService) { }

  @Input() viewMode = false;

  @Input() currentComuna: Comuna = {
    nombre: '',
  };

  ngOnInit(): void {
    if (this.storageService.isLoggedIn()) {
      this.isLoggedIn = true;
      this.roles = this.storageService.getUser().roles;
    }
    if (!this.viewMode) {
      this.message = '';
      this.getComuna(this.route.snapshot.params["id"]);
      this.recibirRegiones();
    }
  }
  message = '';
  seleccionado?: number;
  regiones?:any;

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
  getComuna(id: number): void {
    this.comunaService.get(id)
      .subscribe({
        next: (data) => {
          this.currentComuna = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

  updateComuna(): void {
    this.message = '';

    this.comunaService.update(this.currentComuna.id, this.currentComuna)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.message = res.message ? res.message : 'Esta comuna a sido modificado con exito!';
        },
        error: (e) => console.error(e)
      });
  }
  deleteComuna(): void {
    this.comunaService.delete(this.currentComuna.id)
      .subscribe({
        next: (res) => {
          console.log(res);
          window.location.reload();
        },
        error: (e) => console.error(e)
      });
  }

}
