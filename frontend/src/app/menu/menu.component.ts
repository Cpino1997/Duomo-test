import { Component } from '@angular/core';
import { Subscription } from 'rxjs';
import { AuthService } from '../_services/auth.service';
import { StorageService } from '../_services/storage.service';
import { EventBusService } from '../_shared/event-bus.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent {
  private roles: string[] = [];
  isLoggedIn = false;
  showUserBoard = false;
  showAdminBoard = false;
  showModBoard = false;
  showHome = true;
  username?: string;


  eventBusSub?: Subscription;

  constructor(
    private storageService: StorageService,
    private authService: AuthService,
    private eventBusService: EventBusService) {}

  ngOnInit(): void {
    this.isLoggedIn = this.storageService.isLoggedIn();

    if (this.isLoggedIn) {
      const user = this.storageService.getUser();
      this.roles = user.roles;
      this.showHome = false; //sacar
      this.showUserBoard = this.roles.includes('ROLE_USER');
      this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
      this.showModBoard = this.roles.includes('ROLE_MODERATOR');

      this.username = user.username;
    }

    this.eventBusSub = this.eventBusService.on('logout', () => {
      this.salir();
    });
  }

  salir(): void {
    this.authService.logout().subscribe({
      next: res => {
        console.log(res);
        this.storageService.clean();
        alert('sesion cerrada con exito!')
        window.location.reload();
      },
      error: err => {
        console.log(err);
      }
    });
  }
}
