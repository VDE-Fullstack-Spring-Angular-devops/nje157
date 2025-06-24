import {Component, inject, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {StudentsService} from '../../shared/services/students.service';
import {tap} from 'rxjs';
import {Etudiant} from '../../shared/interfaces/Etudiant';

@Component({
  selector: 'app-student-details',
  imports: [
  ],
  templateUrl: './student-details.component.html',
  styleUrl: './student-details.component.scss'
})
export class StudentDetailsComponent implements OnInit{
  private route = inject(ActivatedRoute)
  private studentService = inject(StudentsService)
  student!: Etudiant
  studentId: number = this.route.snapshot.params['id'];


  ngOnInit() {
    this.getStudent()
    console.log(this.studentId)
  }

  getStudent() {
   this.studentService.getEtudiantById(this.studentId).pipe(
    tap( (student) => {
      this.student = student
      console.log(student)
    })
  ).subscribe()
  }
}
