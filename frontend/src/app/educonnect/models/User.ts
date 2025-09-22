import { Student } from './Student';
import { Teacher } from './Teacher';

export class User {
  userId: number;
  username: string;
  password: string;
  role: string;
  studentId?: number;
  teacherId?: number;
  student?: Student;
  teacher?: Teacher;

  constructor(userId: number, username: string, password: string, role: string, studentId?: number, teacherId?: number | Teacher) {
    this.userId = userId;
    this.username = username;
    this.password = password;
    this.role = role;
    this.studentId = studentId;
    
    // Handle both number and Teacher object for backward compatibility
    if (typeof teacherId === 'number') {
      this.teacherId = teacherId;
    } else if (teacherId && typeof teacherId === 'object') {
      this.teacher = teacherId;
      this.teacherId = teacherId.teacherId;
    }
  }
}