import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({ providedIn: 'root' })
export class Token {
  constructor(private router: Router) {}

  //returns true or false if the token hasn't / has expired 
  tokenExpired() {
    let token = localStorage.getItem('token');
    if (token == null || token == '') return true;
    const expiry = JSON.parse(atob(token.split('.')[1])).exp;
    return Math.floor(new Date().getTime() / 1000) >= expiry;
  }

  // returns json jwt token from string token
  decodeToken() {
    let tokenS = localStorage.getItem('token');
    const _decodeToken = (tokenS: string) => {
      try {
        return JSON.parse(atob(tokenS));
      } catch {
        return;
      }
    };
    return tokenS!
      .split('.')
      .map((tokenS) => _decodeToken(tokenS))
      .reduce((acc, curr) => {
        if (!!curr) acc = { ...acc, ...curr };
        return acc;
      }, Object.create(null));
  }  

  checkTokenExpired(){
    if (this.tokenExpired()) {
      localStorage.setItem('token', '');
      localStorage.setItem('email', '');
      this.router.navigate(['login']);
      console.log('Token expired, loging out!');
    }
  }
}
