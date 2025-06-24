import { Routes } from '@angular/router';
import {ConnexionComponent} from './login/connexion/connexion.component';
import {HomeComponent} from './home/home.component';
import {authGuard} from './shared/guard/auth.guard';
import {ListStudentComponent} from './students/list-student/list-student.component';
import {StudentDetailsComponent} from './students/student-details/student-details.component';
import {AddStudentFormComponent} from './add-student-form/add-student-form.component';

export const routes: Routes = [
  {path:"", component: ConnexionComponent},
  {path:"home", component: HomeComponent, children: [
      {path:"student/:id/profile", component:StudentDetailsComponent, canActivate:[authGuard]},
      {path:"", component: ListStudentComponent, canActivate:[authGuard]},
      {path:"ajoute-etudiant", component: AddStudentFormComponent, canActivate:[authGuard] }
    ], canActivate:[authGuard]},
];
