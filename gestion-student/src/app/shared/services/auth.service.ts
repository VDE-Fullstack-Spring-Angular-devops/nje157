import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {BehaviorSubject, Observable, tap} from 'rxjs';
import {TokenStorageService} from './token-storage.service';

const AUTH_API = 'http://localhost:9000/login';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  isLogged$: BehaviorSubject<boolean> = new BehaviorSubject(false)


  constructor(private http: HttpClient, private tokenService: TokenStorageService) { }

  login(credentials: { login: string | null | undefined; password: string | null | undefined }): Observable<any> {
    return this.http.post(AUTH_API, {
      login: credentials.login,
      password: credentials.password,
    },httpOptions).pipe(
      tap( () => {
        this.isLogged$.next(true)
      })
    );
  }

  logout(){
    this.isLogged$.next(false)
    this.tokenService.signOut()
  }
}
