import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  //Usamos msgs para mostrar el resultado de la operacion
  private mensaje = new BehaviorSubject('Lista de Vinos'); //hay que inicializarlo
  mensajeActual = this.mensaje.asObservable(); //lo exponemos como observable

  //usamos esta variable para indicar si hay que mostrar o no el msg
  private mostrarMensaje = new BehaviorSubject<boolean>(false);
  mostrarMensajeActual = this.mostrarMensaje.asObservable();

  constructor() { }

  //para actualizar msg
  cambiarMensaje(mensaje: string){
    this.mensaje.next(mensaje);
  }

  cambiarMostrarMensaje(valor: boolean){
    this.mostrarMensaje.next(valor);
  }
}
