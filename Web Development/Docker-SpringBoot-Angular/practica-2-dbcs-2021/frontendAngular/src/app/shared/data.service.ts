import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
@Injectable({ providedIn: 'root' })
export class DataService {
  // We use messages to show the result of the operation
  private mensaje = new BehaviorSubject('User List'); 
  mensajeActual = this.mensaje.asObservable(); // Lo exponemos como un observable

  // We use this variable to indicate if we have to show the message or not
  private showMessage = new BehaviorSubject<boolean>(false);
  showActualMessage = this.showMessage.asObservable();
  constructor() {}
  // Message update
  changeMessage(mensaje: string) {
    this.mensaje.next(mensaje);
  }
  changeShowMessage(valor: boolean) {
    this.showMessage.next(valor);
  }
}
