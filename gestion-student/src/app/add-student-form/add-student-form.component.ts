import {Component, inject} from '@angular/core';
import {EmailValidator, FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {StudentsService} from '../shared/services/students.service';
import {Router} from '@angular/router';
import {switchMap, tap} from 'rxjs';
import {Etudiant} from '../shared/interfaces/Etudiant';

@Component({
  selector: 'app-add-student-form',
    imports: [
        ReactiveFormsModule
    ],
  templateUrl: './add-student-form.component.html',
  styleUrl: './add-student-form.component.scss'
})
export class AddStudentFormComponent {
  private studentService = inject(StudentsService)
  private router = inject(Router)
  private formBuilder = inject(FormBuilder)


  addStudentForm =  this.formBuilder.group({
    email: ['', [Validators.required, Validators.email]],
    lastname: ['', Validators.required],
    firstname: ['', Validators.required],
    }
  )

  onSubmit() {
    if(this.addStudentForm.valid){
      const etudiant = this.addStudentForm.value as Etudiant;
      this.studentService.addStudent({ email: etudiant.email, firstname:etudiant.firstname, lastname:etudiant.lastname}).pipe(
        switchMap( data  => this.router.navigateByUrl('/home'))
      ).subscribe()
    }
    console.log(this.addStudentForm.value)
  }
}
