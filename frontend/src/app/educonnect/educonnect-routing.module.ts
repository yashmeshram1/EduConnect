import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { TeacherCreateComponent } from "./components/teachercreate/teachercreate.component";
import { CourseCreateComponent } from "./components/coursecreate/coursecreate.component";
import { StudentCreateComponent } from "./components/studentcreate/studentcreate.component";
import { EnrollmentComponent } from "./components/enrollment/enrollment.component";

const routes: Routes = [
  { path: 'teacher/create', component: TeacherCreateComponent },
  { path: 'course/create', component: CourseCreateComponent },
  { path: 'student/create', component: StudentCreateComponent },
  { path: 'enrollment', component: EnrollmentComponent },
  { path: '', redirectTo: '/teacher/create', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EduConnectRoutingModule {}