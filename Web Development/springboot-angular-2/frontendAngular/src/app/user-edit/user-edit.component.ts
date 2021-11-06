import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../shared/app.model';
import { DataService } from '../shared/data.service';
import { UserApiRestService } from '../shared/user-api-rest.service';
@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: ['./user-edit.component.css'],
})
export class UserEditComponent implements OnInit {
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
  user = this.userVacio as User; // Hay que darle valor inicial, si no salta una
  //excepcion en el template (html), ya que solo en el
  //caso de editar se le daría valor inicial a la variable
  // Ponemos el simbolo "!" para evitar el error en compilacion debido a la no incializacion
  // Van a tener valor seguro, asi que no hay problema en no inicializarlo
  id!: Number; // para guardar el id del vino a editar
  operacion!: String; // para guardar la operación (añadir/editar) a realizar
  //Inyectamos:
  // * ruta activa, mediante ActivatedRoute
  // * servicio de enrutamiento Router
  // * nuestro servicio de acceso al servicio REST cliente-api-rest
  // * nuestro servicio para compartir mensajes entre componentes
  constructor(
    private ruta: ActivatedRoute,
    private router: Router,
    private clienteApiRest: UserApiRestService,
    private datos: DataService
  ) {}
  ngOnInit() {
    console.log('En editar-user');
    // Operacion: va en el ultimo string (parte) de la URL
    this.operacion =
      this.ruta.snapshot.url[this.ruta.snapshot.url.length - 1].path;
    if (this.operacion == 'edit') {
      // Si la operacion es editar se captura el id de la URL
      //y se trae el json con el vino, para mostrarlo en el
      //HTML. Si no es “editar”, será “nuevo” y la operacion de
      //traer vino no se realizara y el formulario aparecerá vacio
      console.log('En Editar');
      this.ruta.paramMap.subscribe(
        // Capturamos el id de la URL
        (params) => {
          this.id = parseInt(params.get('id')!); // Se usa "!" para evitar error en compilacion.
          // No va a ser null nunca
        },
        (err) => console.log('Error al leer id para editar: ' + err)
      );
      // console.log("Id: " + this.id);
      this.clienteApiRest.getUser(this.id).subscribe(
        // Leemos de la base de datos vía API
        (resp) => {
          this.user = resp.body!; // No comprobamos “status”. El vino que existe seguro
          // Se usa “!” por la misma razón que antes
        },
        (err) => {
          console.log('Error al traer el vino: ' + err.message);
          throw err;
        }
      );
    }
  }
  //Se ejecuta al realizar en el template la acción de enviar el formulario
  onSubmit() {
    console.log('Enviado formulario');
    if (this.id) {
      // si existe id estamos en edicion, si no en añadir
      //console.log("Nuevo valor del precio: " + this.vino.precio);
      this.clienteApiRest.modifyUser(String(this.user.id), this.user).subscribe(
        (resp) => {
          if (resp.status < 400) {
            // Si no hay error en la operacion por parte del servicio
            this.datos.changeShowMessage(true);
            this.datos.changeMessage(resp.body); // Mostramos el mensaje enviado por el API
          } else {
            this.datos.changeShowMessage(true);
            this.datos.changeMessage('Error al modificar comentario');
          }
          this.router.navigate(['users']); // Volvemos a la vista 1 (listado de vinos)
        },
        (err) => {
          console.log('Error al editar: ' + err.message);
          throw err;
        }
      );
    } else {
      // Parte añadir user
      this.clienteApiRest.anadirUser(this.user).subscribe(
        (resp) => {
          if (resp.status < 400) {
            this.datos.changeShowMessage(true);
            this.datos.changeMessage(resp.body); // Mostramos el mensaje retornado por el API
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
