import { Component, OnInit } from '@angular/core';
import {StudentService} from "../services/student.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Student} from "../services/student";

@Component({
  selector: 'app-student-form',
  templateUrl: './student-form.component.html',
  styleUrls: ['./student-form.component.scss']
})
export class StudentFormComponent implements OnInit {

  public student = new Student(null, "", "", "", "")

  constructor(private service: StudentService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.route.params.subscribe(param => {
      if(param['id'] == "new"){
        this.student = new Student(null, "", "", "", "")
      } else {
        this.service.findById(param[`id`]).subscribe(student => {
          this.student = student
        }, error => console.log(error))
      }
    })
  }

  onSubmit(): void {
    this.route.params.subscribe(param => {
      if(param['id'] == "new"){
        this.service.addNewStudent(this.student).subscribe(data => {
          console.log(data.id);
          this.router.navigate(["/student"])
        });
      } else {
        this.service.updateStudent(this.student).subscribe(data => {
          console.log(data.id);
          this.router.navigate(["/student"])
        });
      }
    })

  }
}
