import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable, of, tap, catchError } from 'rxjs';
import { AuthService } from '../../services/auth.service';

@Component({
    selector: 'app-registration',
    templateUrl: './registration.component.html',
    styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit {
    registrationForm!: FormGroup;
    registrationError$: Observable<{ [key: string]: string }>;
    successMessage: string | null = null;
    errorMessage: string | null = null;
    selectedRole: string | null = null;

    constructor(private formBuilder: FormBuilder, private authService: AuthService) { }

    ngOnInit(): void {
        this.registrationForm = this.formBuilder.group({
            role: ['', Validators.required],
            username: ['', [Validators.required, Validators.minLength(3), Validators.pattern(/^[a-zA-Z0-9]+$/)]],
            email: ['', [Validators.required, Validators.email]],
            password: ['', [Validators.required, Validators.minLength(8), Validators.pattern(/^(?=.*[A-Z])(?=.*\d).+$/)]],
            fullName: ['', Validators.required],
            contactNumber: ['', [Validators.required, Validators.pattern(/^[0-9]{10}$/)]],
        
            studentClass: [''],
            teacherSubject: [''],
          
            subject: [''],
            yearsOfExperience: [null],
            dateOfBirth: [null],
            address: ['']
        });
    }

    onRoleChange(event: Event): void {
        const role = (event.target as HTMLSelectElement).value;
        this.selectedRole = role;
        
        if (role === 'STUDENT') {
            this.registrationForm.get('studentClass')?.setValidators([Validators.required]);
            this.registrationForm.get('teacherSubject')?.clearValidators();
            this.registrationForm.get('teacherSubject')?.setValue('');
        } else if (role === 'TEACHER') {
            this.registrationForm.get('teacherSubject')?.setValidators([Validators.required]);
            this.registrationForm.get('studentClass')?.clearValidators();
            this.registrationForm.get('studentClass')?.setValue('');
        } else {
         
            this.registrationForm.get('studentClass')?.clearValidators();
            this.registrationForm.get('teacherSubject')?.clearValidators();
            this.registrationForm.get('studentClass')?.setValue('');
            this.registrationForm.get('teacherSubject')?.setValue('');
        }
        
        this.registrationForm.get('studentClass')?.updateValueAndValidity();
        this.registrationForm.get('teacherSubject')?.updateValueAndValidity();
    }

    onSubmit(): void {
        if (this.registrationForm.invalid) {
            this.registrationError$ = of({
                message: "Please make sure you have filled all the required fields correctly",
            });
            this.errorMessage = 'Please fill out all fields correctly.';
            this.successMessage = null;
            return;
        } else {
            const formValue = this.registrationForm.value;
            this.registrationError$ = this.authService
                .createUser(formValue)
                .pipe(
                    tap((response) => {
                        console.log('Registration successful:', response);
                        this.successMessage = 'Registration successful!';
                        this.errorMessage = null;
                        this.resetForm();
                    }),
                    catchError((error) => {
                        console.error("Registration error:", error);
                        this.errorMessage = 'Registration failed. Please try again.';
                        this.successMessage = null;
                        return of({ message: "Registration failed: " + error });
                    })
                );
        }
    }

    resetForm(): void {
        this.registrationForm.reset();
        this.selectedRole = null;
        
       
        this.registrationForm.get('studentClass')?.clearValidators();
        this.registrationForm.get('teacherSubject')?.clearValidators();
        this.registrationForm.get('studentClass')?.updateValueAndValidity();
        this.registrationForm.get('teacherSubject')?.updateValueAndValidity();
    }
}