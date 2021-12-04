import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DataService } from '../shared/data.service';
import { UserApiRestService } from '../shared/user-api-rest.service';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css'],
})
export class UserLoginComponent implements OnInit {
  constructor(
    private router: Router,
    private clienteApiRest: UserApiRestService,
    private datos: DataService
  ) {}

  ngOnInit(): void {}

  // logs user in and saves jwt token and email in local storage for session persistance
  login(email: string, password: string) {
    let jsonString =
      '{"email":"' + email + '", "password": "' + password + '"}';
    let json = JSON.parse(jsonString);
    this.clienteApiRest.sendLogin(json).subscribe(
      (resp) => {
        if (resp.status < 400) {
          this.datos.changeShowMessage(true);
          this.datos.changeMessage(resp.body); // Mostramos el mensaje retornado por el API
          localStorage.setItem('token', resp.body);
          localStorage.setItem('email', email);
          console.log('Token: ' + localStorage.getItem('token'));
          if (
            localStorage.getItem('token') != null &&
            localStorage.getItem('token') != ''
          )
            this.router.navigate(['users']);
        } else {
          this.datos.changeShowMessage(true);
          this.datos.changeMessage('Error al añadir user');
        }
      },
      (err) => {
        console.log('Error al añadir: ' + err.message);
        throw err;
      }
    );
  }
}
