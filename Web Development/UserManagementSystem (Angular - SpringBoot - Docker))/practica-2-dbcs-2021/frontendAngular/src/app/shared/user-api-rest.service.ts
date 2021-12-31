import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from './app.model';
@Injectable({ providedIn: 'root' })
export class UserApiRestService {
  private static readonly BASE_URI = 'http://localhost:8080/users/';
  private static readonly BASE_URI_LOGIN = 'http://localhost:8090/login';

  constructor(private http: HttpClient) {} 

  getAllUsers() {
    let url = UserApiRestService.BASE_URI;
    return this.http.get<User[]>(url); 
  }
 
  getAllUsers_ConResponse(): Observable<HttpResponse<User[]>> {
    let url = UserApiRestService.BASE_URI;
    return this.http.get<User[]>(url, { observe: 'response' });
  }
  borrarUser(id: String): Observable<HttpResponse<any>> {
    let url = UserApiRestService.BASE_URI + id;
    return this.http.delete(url, { observe: 'response', responseType: 'text' });
  }
  anadirUser(user: User): Observable<HttpResponse<any>> {
    let url = UserApiRestService.BASE_URI;
    return this.http.post(url, user, {
      observe: 'response',
      responseType: 'text',
    });
  }
  modifyUser(id: String, user: User): Observable<HttpResponse<any>> {
    let url = UserApiRestService.BASE_URI + id;
    return this.http.put(url, user, {
      observe: 'response',
      responseType: 'text',
    });
  }
  getUser(id: Number): Observable<HttpResponse<User>> {
    let url = UserApiRestService.BASE_URI + id;
    return this.http.get<User>(url, { observe: 'response' });
  }
  getUserByEmail(email: String): Observable<HttpResponse<User>> {
    let url = UserApiRestService.BASE_URI;
    url = url.substring(0, url.length - 1);
    url += '?email=' + email;
    return this.http.get<User>(url, { observe: 'response' });
  }
  getUsersEnabled(enabled: boolean): Observable<HttpResponse<any>> {
    let url = UserApiRestService.BASE_URI + '?enabled=' + enabled;
    return this.http.get<User[]>(url, { observe: 'response' });
  }

  setEnabled(enabled: boolean, ids: String): Observable<HttpResponse<any>> {
    let url;
    if (enabled) url = UserApiRestService.BASE_URI + 'enable?user_id=' + ids;
    else url = UserApiRestService.BASE_URI + 'disable?user_id=' + ids;
    return this.http.put(url, null, {
      observe: 'response',
      responseType: 'text',
    });
  }

  sendLogin(user: string): Observable<HttpResponse<any>> {
    let url = UserApiRestService.BASE_URI_LOGIN;
    return this.http.post(url, user, {
      observe: 'response',
      responseType: 'text',
    });
  }
}
