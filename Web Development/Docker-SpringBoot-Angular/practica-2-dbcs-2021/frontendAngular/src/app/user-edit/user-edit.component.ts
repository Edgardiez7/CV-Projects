import { Component, Injectable, OnInit } from '@angular/core';
import { ActivatedRoute, CanActivate, Router } from '@angular/router';
import { User } from '../shared/app.model';
import { DataService } from '../shared/data.service';
import { Token } from '../shared/token';
import { UserApiRestService } from '../shared/user-api-rest.service';
@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: ['./user-edit.component.css'],
})
@Injectable({ providedIn: 'root' })
export class UserEditComponent implements OnInit, CanActivate {
  userVacio = {
    id: 0,
    nick: '',
    firstName: '',
    lastName: '',
    email: '',
    password: '',
    enabled: false,
    createdAt: new Date(0),
    updatedAt: new Date(0, 0, 0),
  };
  user = this.userVacio as User;
  id!: Number;
  operacion!: String;

  constructor(
    private ruta: ActivatedRoute,
    private router: Router,
    private clienteApiRest: UserApiRestService,
    private datos: DataService,
    private token: Token
  ) {}

  canActivate(): boolean {
    if (this.token.tokenExpired()) {
      this.router.navigate(['login']);
      localStorage.setItem('email', '');
      localStorage.setItem('token', '');
      console.log('Token expired, loging out!');
    }
    return !this.token.tokenExpired();
  }

  ngOnInit() {
    this.token.checkTokenExpired();
    this.operacion =
      this.ruta.snapshot.url[this.ruta.snapshot.url.length - 1].path;
    if (this.operacion == 'edit') {
      this.ruta.paramMap.subscribe(
        (params) => {
          this.id = parseInt(params.get('id')!); // Se usa "!" para evitar error en compilacion.
        },
        (err) => console.log('Error al leer id para editar: ' + err)
      );
      this.clienteApiRest.getUser(this.id).subscribe(
        (resp) => {
          this.user = resp.body!;
        },
        (err) => {
          console.log('Error al traer el cliente: ' + err.message);
          throw err;
        }
      );
    }
  }

  onSubmit() {
    this.token.checkTokenExpired();
    if (this.id) {
      this.clienteApiRest.modifyUser(String(this.user.id), this.user).subscribe(
        (resp) => {
          if (resp.status < 400) {
            this.datos.changeShowMessage(true);
            this.datos.changeMessage(resp.body);
          } else {
            this.datos.changeShowMessage(true);
            this.datos.changeMessage('Error al modificar comentario');
          }
          this.router.navigate(['users']);
        },
        (err) => {
          console.log('Error al editar: ' + err.message);
          throw err;
        }
      );
    } else {
      this.clienteApiRest.anadirUser(this.user).subscribe(
        (resp) => {
          if (resp.status < 400) {
            this.datos.changeShowMessage(true);
            this.datos.changeMessage(resp.body);
          } else {
            this.datos.changeShowMessage(true);
            this.datos.changeMessage('Error al añadir user');
          }
          this.router.navigate(['users']);
        },
        (err) => {
          console.log('Error al añadir: ' + err.message);
          throw err;
        }
      );
    }
  }
}
