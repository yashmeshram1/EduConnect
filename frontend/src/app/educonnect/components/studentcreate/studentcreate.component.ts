import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';
import { Student } from '../../models/Student';
import { EduConnectService } from '../../services/educonnect.service';

@Component({
  selector: 'app-studentcreate',
  templateUrl: './studentcreate.component.html',
  styleUrls: ['./studentcreate.component.scss']
})
export class StudentCreateComponent implements OnInit {
  student: Student;
  studentForm: FormGroup;
  successMessage: string | null = null;
  errorMessage: string | null = null;

  constructor(
    private fb: FormBuilder,
    private educonnectService: EduConnectService
  ) {
    this.student = new Student(0, '', null, '', '', '');
    this.studentForm = this.fb.group({
      fullName: ['', [Validators.required, Validators.minLength(2)]],
      dateOfBirth: ['', Validators.required],
      contactNumber: ['', [Validators.required, Validators.pattern(/^\d{10}$/)]],
      email: ['', [Validators.required, Validators.email]],
      address: ['', [Validators.required, Validators.minLength(5)]]
    });
  }

  ngOnInit(): void {
    this.initializeForm();
  }

  initializeForm(): void {
    this.studentForm = this.fb.group({
      fullName: [this.student.fullName, [Validators.required, Validators.minLength(2)]],
      dateOfBirth: [this.student.dateOfBirth, Validators.required],
      contactNumber: [this.student.contactNumber, [Validators.required, Validators.pattern(/^\d{10}$/)]],
      email: [this.student.email, [Validators.required, Validators.email]],
      address: [this.student.address, [Validators.required, Validators.minLength(5)]]
    });
  }

  onSubmit(): void {
    this.successMessage = null;
    this.errorMessage = null;

    if (this.studentForm.valid || this.isFormValid()) {
      
      if (this.studentForm.valid) {
        const formValue = this.studentForm.value;
        this.student.fullName = formValue.fullName;
        this.student.dateOfBirth = formValue.dateOfBirth;
        this.student.contactNumber = formValue.contactNumber;
        this.student.email = formValue.email;
        this.student.address = formValue.address;
      }

      this.educonnectService.addStudent(this.student).subscribe(
        (response: Student) => {
          this.successMessage = 'Student created successfully!';
          console.log('Student created:', response);
          this.resetForm();
        },
        (error: HttpErrorResponse) => {
          this.handleError(error);
        }
      );
    } else {
      this.errorMessage = 'Please fill in all required fields correctly.';
    }
  }

  resetForm(): void {
    this.student = new Student(0, '', null, '', '', '');
    this.studentForm.reset();
    this.successMessage = null;
    this.errorMessage = null;
  }

  private handleError(error: HttpErrorResponse): void {
    console.error('Error creating student:', error);
    if (error.status === 400) {
      this.errorMessage = 'Invalid data provided. Please check your input.';
    } else if (error.status === 409) {
      this.errorMessage = 'Student with this email already exists.';
    } else {
      this.errorMessage = 'An error occurred while creating the student. Please try again.';
    }
  }

  private isFormValid(): boolean {
    return !!(
      this.student.fullName && 
      this.student.fullName.trim() !== '' &&
      this.student.contactNumber && 
      this.student.contactNumber.trim() !== '' &&
      this.student.email && 
      this.student.email.trim() !== '' &&
      this.student.address && 
      this.student.address.trim() !== ''
    );
  }
}