import { Component, OnInit } from '@angular/core';
import {StudentService} from "../services/student.service";
import {Student} from "../services/student";

@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
  styleUrls: ['./student-list.component.scss']
})
export class StudentListComponent implements OnInit {

  students : Student[] = []
  constructor(private service: StudentService) { }

  ngOnInit(): void {
    this.service.findAll().subscribe(students => this.students = students, error => console.log(error))
  }

  onDeleteClick(id: any) {
    this.service.deleteById(id)
    window.location.reload()
  }
}
