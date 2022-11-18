import { Component, OnInit } from '@angular/core';
import { Comuna } from 'src/app/models/comuna.model';
import { Region } from 'src/app/models/region.model';
import { AuthService } from 'src/app/_services/auth.service';
import { ComunaService } from 'src/app/_services/comunas.service';
import { StorageService } from 'src/app/_services/storage.service';

@Component({
  selector: 'app-comunas',
  templateUrl: './comunas.component.html',
  styleUrls: ['./comunas.component.css']
})
export class ComunasComponent implements OnInit {
  comunas?: Comuna[];
  currentComuna: Comuna = {};
  id = -1;
  nombre = '';
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];
  regionid?:any;
  constructor(private comunaService: ComunaService,private authService: AuthService, private storageService: StorageService) { }


  ngOnInit(): void {
    this.recibirComunas();
    if (this.storageService.isLoggedIn()) {
      this.isLoggedIn = true;
      this.roles = this.storageService.getUser().roles;
    }
  }


  recibirComunas(): void {
    this.comunaService.getAll()
    .subscribe({
      next: (data) => {
        this.comunas = data;
        console.log(data);
      },
      error: (e) => console.error(e)
    });
  }
  refreshList(): void {
    this.recibirComunas();
    this.currentComuna = {};
    this.id = -1;
  }
  setActive(comuna: Comuna, id: number): void {
    this.currentComuna = comuna;
    this.id = id;
  }
  recibirComunasByRegion(id:number){
    this.comunaService.getByRegion(id)
    .subscribe({
      next: (res) => {
        console.log(res);
        this.refreshList();
      },
      error: (e) => console.error(e)
    });
}

  removeRegion(idComuna : number): void {
    this.comunaService.delete(idComuna)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.refreshList();
        },
        error: (e) => console.error(e)
      });
  }
}

