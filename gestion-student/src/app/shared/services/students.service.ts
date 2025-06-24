import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {TokenStorageService} from './token-storage.service';
import {Etudiant} from '../interfaces/Etudiant';


const AUTH_API = 'http://localhost:9000/etudiants';

@Injectable({
  providedIn: 'root'
})

export class StudentsService{

  constructor(private http: HttpClient, private tokenStorage : TokenStorageService) {}

  getAllStudents(): Observable<Etudiant[]>{
    return this.http.get<Etudiant[]>(AUTH_API);
  }

  getEtudiantById(etudiantId: number): Observable<Etudiant>{
    return  this.http.get<Etudiant>(`${AUTH_API}/${etudiantId}`)
  }

  deleteStudent(studentId: number): Observable<any>{
    return  this.http.delete(`${AUTH_API}/delete/${studentId}`);
  }

  addStudent(etudiant: Etudiant): Observable<any>{
  return  this.http.post(AUTH_API + "/create", etudiant)
   }
}
