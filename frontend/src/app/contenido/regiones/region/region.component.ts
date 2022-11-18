import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Region } from 'src/app/models/region.model';
import { RegionesService } from 'src/app/_services/regiones.service';
import { StorageService } from 'src/app/_services/storage.service';

@Component({
  selector: 'app-region',
  templateUrl: './region.component.html',
  styleUrls: ['./region.component.css']
})
export class RegionComponent implements OnInit {
  @Input() viewMode = false;

  @Input() currentRegion: Region = {
    nombre: '',
  };

  isLoggedIn = false
  roles: string[] = [];
  message = '';
  seleccionado?: number;
  regiones?:any;

  constructor(
    private regionService: RegionesService,
    private route: ActivatedRoute,
    private router: Router, private storageService: StorageService) { }

  ngOnInit(): void {

    if (this.storageService.isLoggedIn()) {
      this.isLoggedIn = true;
      this.roles = this.storageService.getUser().roles;
    }
    if (!this.viewMode) {
      this.message = '';
      this.getRegion(this.route.snapshot.params["id"]);
      this.recibirRegiones();
    }
  }
  getRegion(id: number): void {
    this.regionService.get(id)
      .subscribe({
        next: (data) => {
          this.currentRegion = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
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

  updateRegion(): void {
    this.message = '';

    this.regionService.update(this.currentRegion.id, this.currentRegion)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.message = res.message ? res.message : 'Esta region a sido modificado con exito!';
        },
        error: (e) => console.error(e)
      });
  }
  deleteRegion(): void {
    this.regionService.delete(this.currentRegion.id)
      .subscribe({
        next: (res) => {
          console.log(res);
          window.location.reload();
        },
        error: (e) => console.error(e)
      });
  }

}
