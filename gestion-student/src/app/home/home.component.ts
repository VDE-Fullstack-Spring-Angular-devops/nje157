import {Component, inject, Input, OnInit} from '@angular/core';
import {DashboardComponent} from '../dashboard/dashboard.component';
import {StudentsService} from '../shared/services/students.service';
import {RouterOutlet} from '@angular/router';

@Component({
  selector: 'app-home',
  imports: [
    DashboardComponent,
    RouterOutlet
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent implements OnInit{
  private studentService = inject(StudentsService)
  @Input() student: any

  ngOnInit() {
    this.getStudent()
  }

  getStudent(){
    this.studentService.getAllStudents().subscribe( data => {
      this.student = data;
      console.log(this.student)
    }
    )
  }
}
