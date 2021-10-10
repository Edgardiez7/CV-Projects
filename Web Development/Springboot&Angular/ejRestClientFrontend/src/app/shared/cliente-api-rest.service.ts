import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Vino } from './app.model';

@Injectable({
  providedIn: 'root',
})
export class ClienteApiRestService {
  private static readonly BASE_URI = 'http://localhost:8080/TiendaVinos/';
  constructor(private http: HttpClient) {} //inyectando el servicio HttpClient

  //ejemplo de llamada retornando el cuerpo de la respuesta
  getAllVinos() {
    console.log('dentro de getAllVinos');
    let url = ClienteApiRestService.BASE_URI;
    return this.http.get<Vino[]>(url); //retorna el cuerpo de la respuesta
  }

  // Ejemplo de llamada retornando toda la respuesta
  getAllVinos_ConResponse(): Observable<HttpResponse<Vino[]>> {
    let url = ClienteApiRestService.BASE_URI;
    return this.http.get<Vino[]>(url, { observe: 'response' });
  }

  borrarVino(id: String): Observable<HttpResponse<any>> {
    let url = ClienteApiRestService.BASE_URI + id;
    return this.http.delete(url, { observe: 'response', responseType: 'text' });
  }

  anadirVino(vino: Vino): Observable<HttpResponse<any>> {
    let url = ClienteApiRestService.BASE_URI;
    return this.http.post(url, vino, {
      observe: 'response',
      responseType: 'text',
    });
  }

  modificarPrecio(id: String, vino: Vino): Observable<HttpResponse<any>> {
    let url = ClienteApiRestService.BASE_URI + id;
    return this.http.put(url, vino, {
      observe: 'response',
      responseType: 'text',
    });
  }
  
  getVino(id: String): Observable<HttpResponse<Vino>> {
    let url = ClienteApiRestService.BASE_URI + id;
    return this.http.get<Vino>(url, { observe: 'response' });
  }
}
