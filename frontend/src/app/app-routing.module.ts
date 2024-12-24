import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserListComponent } from './component/user-list/user-list.component';
import { UserFormComponent } from './component/user-form/user-form.component';
import {HomeComponent} from "./component/home/home.component";
import {AboutComponent} from "./component/about/about.component";
import {ContactComponent} from "./component/contact/contact.component";
import {ServicesComponent} from "./component/services/services.component";
import {LoginComponent} from "./component/login/login.component";
import {RegisterComponent} from "./register/register.component";
import {PasswordRecoveryComponent} from "./password-recovery/password-recovery.component";
import {DashboardComponent} from "./dashboard/dashboard.component";
import {authGuard} from "./guards/auth.guard";
import {ViewAssociationComponent} from "./component/view-association/view-association.component";
import {EditAssociationComponent} from "./component/edit-association/edit-association.component";

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'password-recovery', component: PasswordRecoveryComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'dashboard', component: DashboardComponent, canActivate: [authGuard] },
  { path: 'add-association', component: EditAssociationComponent, canActivate: [authGuard] },
  { path: 'view-association/:associationId', component: ViewAssociationComponent, canActivate: [authGuard] },
  { path: 'edit-association/:associationId', component: EditAssociationComponent, canActivate: [authGuard] },
  { path: 'services', component: ServicesComponent },
  { path: 'about', component: AboutComponent },
  { path: 'contact', component: ContactComponent },
  { path: 'users', component: UserListComponent },
  { path: 'adduser', component: UserFormComponent },
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
