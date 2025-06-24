import {inject} from '@angular/core';
import {HttpEvent, HttpHandler, HttpHandlerFn, HttpRequest} from '@angular/common/http';
import {TokenStorageService} from '../../shared/services/token-storage.service';
import {Observable} from 'rxjs';

const TOKEN_HEADER_KEY = 'Authorization';       // for Spring Boot back-end
 export function authInterceptor(req: HttpRequest<any>, next: HttpHandlerFn): Observable<HttpEvent<any>> {
  const  authToken =  inject(TokenStorageService)
    let authReq = req;
    const token = authToken.getToken();
    if (token != null) {
      // for Spring Boot back-end
      authReq = req.clone({ headers: req.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + authToken.getToken()) });
    }
    return next(authReq);
  }


