import { Component } from '@angular/core';
import { Comuna } from '../models/comuna.model';
import { AuthService } from '../_services/auth.service';
import { ComunaService } from '../_services/comunas.service';
import { StorageService } from '../_services/storage.service';

@Component({
  selector: 'app-contenido',
  templateUrl: './contenido.component.html',
  styleUrls: ['./contenido.component.css']
})
export class ContenidoComponent {

  constructor(private authService : AuthService, private storageService : StorageService, private comunaService : ComunaService) { }
  isLoggedIn = false;
  username = "";
  comunas?: Comuna[];
  ngOnInit(): void {
    this.isLoggedIn = this.storageService.isLoggedIn();
    this.username = this.storageService.getUserName();
  }
  RecibirComunas(): void {
    this.comunaService.getAll()
    .subscribe({
      next: (data) => {
        this.comunas = data;
        console.log(data);
      },
      error: (e) => console.error(e)
    });
  }
}
