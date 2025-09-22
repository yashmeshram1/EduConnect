import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';
import { EduConnectService } from '../../services/educonnect.service';
import { Teacher } from '../../models/Teacher';

@Component({
    selector: 'app-teacher-create',
    templateUrl: './teachercreate.component.html',
    styleUrls: ['./teachercreate.component.scss']
})
export class TeacherCreateComponent implements OnInit {
    teacherForm: FormGroup;
    submitted = false;
    successMessage: string | null = '';
    errorMessage: string | null = '';

    constructor(
        private fb: FormBuilder,
        private educonnectService: EduConnectService
    ) {
        this.teacherForm = this.fb.group({
            teacherId: [0],
            fullName: ['', Validators.required],
            contactNumber: ['', [Validators.required, Validators.pattern(/^\d{10}$/)]],
            email: ['', [Validators.required, Validators.email]],
            subject: ['', Validators.required],
            yearsOfExperience: [0, [Validators.required, Validators.min(1)]]
        });
    }

    ngOnInit(): void {
       
    }

    get f() {
        return this.teacherForm.controls;
    }

    onSubmit(): void {
        this.submitted = true;
        this.successMessage = '';
        this.errorMessage = '';

        if (this.teacherForm.invalid) {
            this.errorMessage = 'Please correct the errors in the form.';
            return;
        }

        const teacher = this.teacherForm.value;
        console.log('Teacher Created:', teacher);

        this.educonnectService.addTeacher(teacher).subscribe(
            (response: Teacher) => {
                this.successMessage = 'Teacher created successfully!';
                this.teacherForm.reset();
                this.submitted = false;
            },
            (error: HttpErrorResponse) => {
                this.handleError(error);
            }
        );
    }

    resetForm(): void {
        this.teacherForm.reset();
        this.submitted = false;
        this.successMessage = '';
        this.errorMessage = '';
    }

    private handleError(error: HttpErrorResponse): void {
        console.error('Error creating teacher:', error);
        if (error.status === 400) {
            this.errorMessage = 'Invalid data provided. Please check your input.';
        } else if (error.status === 409) {
            this.errorMessage = 'Teacher with this email already exists.';
        } else {
            this.errorMessage = 'An error occurred while creating the teacher. Please try again.';
        }
    }
}