import { Component, OnInit } from '@angular/core';
import { User } from '../shared/app.model';
import { DataService } from '../shared/data.service';
import { UserApiRestService } from '../shared/user-api-rest.service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css'],
})
export class UserListComponent implements OnInit {
  Users: User[];
  showMessage: boolean;
  message: string;
  enableUsers: Number[] = [];
  // Inyectamos los servicios
  constructor(
    private clienteApiRest: UserApiRestService,
    private datos: DataService
  ) {}
  //método ejecutado tras la construcción del componente. Es el lugar adecuado para cargar datos
  ngOnInit() {
    console.log('Dentro funcion ngOnInit de List');
    /*** Cargamos vinos accediendo a la respuesta */
    this.getUsers_AccesoResponse();
  }

  getUsers_AccesoResponse() {
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
    this.clienteApiRest.borrarUser(String(id)).subscribe(
      (resp) => {
        if (resp.status < 400) {
          // Si no hay error en la respuesta
          // actualizamos variable compartida
          this.showMessage = true;
          // actualizamos variable compartida
          this.message = resp.body; // mostramos el mensaje retornado por el API
          //Actualizamos la lista de vinos en la vista
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
    console.log(id);
    this.clienteApiRest.getUser(id).subscribe(
      (resp) => {
        if (resp.status < 400) {
          // Si no hay error en la respuesta
          // actualizamos variable compartida
          this.showMessage = true;
          // actualizamos variable compartida
          //this.message = resp.body; // mostramos el mensaje retornado por el API
          //Actualizamos la lista de vinos en la vista
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

  getUsersEnabled(enabled: boolean) {
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
    if (this.enableUsers.includes(user)) {
      this.enableUsers.splice(this.enableUsers.indexOf(user), 1);
    } else this.enableUsers.push(user);
  }
}
