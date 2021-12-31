import { Component, Injectable, OnInit } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { User } from '../shared/app.model';
import { Token } from '../shared/token';
import { UserApiRestService } from '../shared/user-api-rest.service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css'],
})
@Injectable({ providedIn: 'root' })
export class UserListComponent implements OnInit, CanActivate {
  Users: User[];
  showMessage: boolean;
  message: string;
  enableUsers: Number[] = [];
  // Inyectamos los servicios
  constructor(
    private clienteApiRest: UserApiRestService,
    private router: Router,
    private token: Token
  ) {}

  canActivate(): boolean {
    if(this.token.tokenExpired()) {
      localStorage.setItem('token', '');
      localStorage.setItem('email', '');
      this.router.navigate(['login']);
      console.log('Token expired, loging out!');
    }
    return !this.token.tokenExpired();
  }

  //método ejecutado tras la construcción del componente. Es el lugar adecuado para cargar datos
  ngOnInit() {
    this.token.checkTokenExpired();
    let email = localStorage.getItem('email');
    document.getElementById('logout')!.textContent = 'Logout: ' + email;
    this.getUsers_AccesoResponse();
  }

  getUsers_AccesoResponse() {
    this.token.checkTokenExpired();
    this.clienteApiRest.getAllUsers_ConResponse().subscribe(
      (resp) => {
        if (resp.status < 400) {
          // Si no hay error en la respuesta
          this.Users = resp.body as User[]; // se accede al cuerpo de la respuesta
        } else {
          this.message = 'Error al acceder a los datos';
          this.showMessage = true;
        }
      },
      (err) => {
        console.log('Error al traer la lista: ' + err.message);
        throw err;
      }
    );
  }
  delete(id: Number) {
    this.token.checkTokenExpired();
    this.clienteApiRest.borrarUser(String(id)).subscribe(
      (resp) => {
        if (resp.status < 400) {
          // Si no hay error en la respuesta
          // actualizamos variable compartida
          this.showMessage = true;
          // actualizamos variable compartida
          this.message = resp.body; // mostramos el mensaje retornado por el API
          //Actualizamos la lista de usuarios en la vista
          this.getUsers_AccesoResponse();
        } else {
          this.showMessage = true;
          this.message = 'Error al eliminar registro';
        }
      },
      (err) => {
        console.log('Error al borrar: ' + err.message);
        throw err;
      }
    );
  }

  getUser(id: Number) {
    this.token.checkTokenExpired();
    this.clienteApiRest.getUser(id).subscribe(
      (resp) => {
        if (resp.status < 400) {
          // Si no hay error en la respuesta
          // actualizamos variable compartida
          this.showMessage = true;
          //Actualizamos la lista de usuarios en la vista
          this.Users = [];
          this.Users.push(resp.body!);
          // se accede al cuerpo de la respuesta
        } else {
          this.showMessage = true;
          this.message = 'Error al eliminar registro';
        }
      },
      (err) => {
        this.Users = [];
        console.log('Error al getUser: ' + err.message);
        throw err;
      }
    );
  }

  getUserByEmail(email: String) {
    this.token.checkTokenExpired();
    this.clienteApiRest.getUserByEmail(email).subscribe(
      (resp) => {
        if (resp.status < 400) {
          // Si no hay error en la respuesta
          // actualizamos variable compartida
          this.showMessage = true;
          //Actualizamos la lista de usuarios en la vista
          this.Users = resp.body as unknown as User[];
        } else {
          this.showMessage = true;
          this.message = 'Error al eliminar registro';
        }
      },
      (err) => {
        this.Users = [];
        console.log('Error al getUser: ' + err.message);
        throw err;
      }
    );
  }

  getUsersEnabled(enabled: boolean) {
    this.token.checkTokenExpired();
    this.clienteApiRest.getUsersEnabled(enabled).subscribe(
      (resp) => {
        if (resp.status < 400) {
          // Si no hay error en la respuesta
          this.Users = resp.body as User[]; // se accede al cuerpo de la respuesta
        } else {
          this.message = 'Error al acceder a los datos';
          this.showMessage = true;
        }
      },
      (err) => {
        console.log('Error al traer la lista: ' + err.message);
        throw err;
      }
    );
  }

  setEnabled(enabled: boolean) {
    this.token.checkTokenExpired();
    let idsString = '';
    for (let i = 0; i < this.enableUsers.length; i++) {
      if (i != this.enableUsers.length - 1)
        idsString += this.enableUsers[i] + ',';
      else idsString += this.enableUsers[i];
    }
    this.enableUsers = [];

    this.clienteApiRest.setEnabled(enabled, idsString).subscribe(
      (resp) => {
        if (resp.status < 400) {
          // Si no hay error en la respuesta
          this.getUsers_AccesoResponse();
        } else {
          this.message = 'Error al acceder a los datos';
          this.showMessage = true;
        }
      },
      (err) => {
        console.log('Error al traer la lista: ' + err.message);
        throw err;
      }
    );
  }

  addEnabled(user: Number) {
    this.token.checkTokenExpired();
    if (this.enableUsers.includes(user)) {
      this.enableUsers.splice(this.enableUsers.indexOf(user), 1);
    } else this.enableUsers.push(user);
  }

  addNew() {
    this.token.checkTokenExpired();
    this.router.navigate(['users/new']);
  }

  logout() {
    localStorage.setItem('token', '');
    localStorage.setItem('email', '');
    this.router.navigate(['login']);
  }
}
