import {Component, inject, Input, OnInit} from '@angular/core';
import {TokenStorageService} from '../../shared/services/token-storage.service';
import {StudentsService} from '../../shared/services/students.service';
import {mergeMap, tap} from 'rxjs';
import {Router, RouterLink} from '@angular/router';

@Component({
  selector: 'app-list-student',
  imports: [
    RouterLink
  ],
  templateUrl: './list-student.component.html',
  styleUrl: './list-student.component.scss'
})
export class ListStudentComponent implements OnInit{
  students!: any;
  studentService = inject(StudentsService)
  router = inject(Router)
  //@ViewChild('templeFormAddStudent') templateFormAddStudent : TemplateRef<any> | undefined;
  tokenService = inject(TokenStorageService)
  userRole = this.tokenService.getUserRole()
  studentsServices = inject(StudentsService)

  ngOnInit() {
    this.getStudents()
  }
  deleteStudent(idStudent: number) {
    this.studentsServices.deleteStudent(idStudent).pipe(
      mergeMap(() => this.studentsServices.getAllStudents().pipe(
        tap( data => this.students = data)
      ))
    ).subscribe()
  }

  getStudents(){
    this.studentService.getAllStudents().pipe(
      tap( students => this.students = students)
    ).subscribe()
  }

  addStudent(){
    this.router.navigateByUrl('home/ajoute-etudiant')
  }

  showStudentDétails() {
    this.router.navigateByUrl('home/student-profile')
  }



}
