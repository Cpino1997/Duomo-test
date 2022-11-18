import { Component, OnInit } from '@angular/core';
import { Region } from 'src/app/models/region.model';
import { AuthService } from 'src/app/_services/auth.service';
import { RegionesService } from 'src/app/_services/regiones.service';
import { StorageService } from 'src/app/_services/storage.service';

@Component({
  selector: 'app-regiones',
  templateUrl: './regiones.component.html',
  styleUrls: ['./regiones.component.css']
})
export class RegionesComponent implements OnInit {
  regiones?: Region[];
  currentRegion: Region = {};
  id = -1;
  nombre = '';
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];
  constructor(private regionService: RegionesService,private authService: AuthService, private storageService: StorageService) { }

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
  refreshList(): void {
    this.recibirRegiones();
    this.currentRegion = {};
    this.id = -1;
  }
  setActive(region: Region, id: number): void {
    this.currentRegion = region;
    this.id = id;
  }
  removeRegion(idRegion : number): void {
    this.regionService.delete(idRegion)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.refreshList();
        },
        error: (e) => console.error(e)
      });
  }
}
