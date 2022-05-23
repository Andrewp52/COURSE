import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Student} from "./student";

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  constructor(public http: HttpClient) { }

  ngOnInit(): void {

  }

  public findAll(){
    return this.http.get<Student[]>("/api/students")
  }

  public findById(id:number){
    return this.http.get<Student>(`/api/students/${id}`)
  }

  public addNewStudent(student: Student){
    const headers = { 'Content-Type': 'application/json' };
    return this.http.post<Student>("/api/students", student, {headers : headers})
  }
  public deleteById(id:number){
    this.http.delete(`/api/students/${id}`).subscribe();
  }

  updateStudent(student: Student) {
    const headers = { 'Content-Type': 'application/json' };
    return this.http.put<Student>("/api/students", student, {headers : headers})
  }
}
