import {CanActivateFn, Router} from '@angular/router';
import {AuthService} from '../services/auth.service';
import {inject} from '@angular/core';
import {TokenStorageService} from '../services/token-storage.service';

export const authGuard: CanActivateFn = (route, state) => {
    const router = inject(Router)
    const tokenService = inject(TokenStorageService)
    const token = tokenService.getToken();
      if (token) {
        return true;
      } else {
        router.navigateByUrl('');
        return false;
      }

};
