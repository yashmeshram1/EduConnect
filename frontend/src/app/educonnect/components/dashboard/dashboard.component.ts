import { Component, OnInit } from '@angular/core';
import { EduConnectService } from '../../services/educonnect.service';
import { Teacher } from '../../models/Teacher';
import { Course } from '../../models/Course';
import { Student } from '../../models/Student';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  role!: string;
  userId!: number;
  teacherId!: number;
  teacherDetails!: Teacher;
  courses: Course[] = [];
  students: Student[] = [];

  constructor(private service: EduConnectService) {}

  ngOnInit(): void {
    this.role = localStorage.getItem('role') || '';
    this.userId = Number(localStorage.getItem('user_id'));
    this.teacherId = Number(localStorage.getItem('teacher_id'));

    if (this.role === 'TEACHER') {
      this.loadTeacherData();
    }
  }

  loadTeacherData(): void {
    this.service.getTeacherById(this.teacherId).subscribe((res) => {
      this.teacherDetails = res;
    });

    this.service.getCoursesByTeacherId(this.teacherId).subscribe((res) => {
      this.courses = res;
    });

    this.service.getAllStudents().subscribe((res) => {
      this.students = res;
    });
  }
}
