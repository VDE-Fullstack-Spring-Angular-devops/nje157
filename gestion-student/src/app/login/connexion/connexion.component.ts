import {Component, inject} from '@angular/core';
import {FormBuilder, ReactiveFormsModule, Validators} from '@angular/forms';
import {AuthService} from '../../shared/services/auth.service';
import {TokenStorageService} from '../../shared/services/token-storage.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-connexion',
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './connexion.component.html',
  styleUrl: './connexion.component.scss'
})
export class ConnexionComponent {
  private formBuilder = inject(FormBuilder);
  private router = inject(Router);
  authService =  inject(AuthService)
  tokenStorage =  inject(TokenStorageService)
  constructor() { }

  loginForm = this.formBuilder.group({
    login: ['',[Validators.required]],
    password: ['',[Validators.required]]
  })

  onSubmit() {
    if(this.loginForm.valid){
      this.authService.login({ login: this.loginForm.value.login, password: this.loginForm.value.password })
        .subscribe({
          next: data => {
            console.log(data);
            if (data && data.token) {
              const token = data.token;
              console.log(token)
              this.tokenStorage.saveToken(token);
              this.router.navigateByUrl("home");
            }
          },
          error: err => {
            console.error("Erreur de connexion : ", err);
          }
        });
    }else{
      console.log(" login ou mot de passe incorrect")
    }
  }
}
