import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {UserListComponent} from './component/user-list/user-list.component';
import {UserFormComponent} from './component/user-form/user-form.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {UserService} from "./service/user.service";
import {AssociationListComponent} from './component/association-list/association-list.component';
import {AssociationService} from "./service/association.service";
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {SharedModule} from "./shared/shared.module";
import {HeaderComponent} from './shared/header/header.component';
import {FooterComponent} from './shared/footer/footer.component';
import {HomeComponent} from './component/home/home.component';
import {LoginComponent} from './component/login/login.component';
import {AboutComponent} from './component/about/about.component';
import {ContactComponent} from './component/contact/contact.component';
import {ServicesComponent} from './component/services/services.component';
import {RegisterComponent} from './register/register.component';
import {MatInputModule} from "@angular/material/input";
import {MatButtonModule} from "@angular/material/button";
import {MatCardModule} from "@angular/material/card";
import {MatAutocompleteModule} from "@angular/material/autocomplete";
import {PasswordRecoveryComponent} from './password-recovery/password-recovery.component';
import {MatIconModule} from "@angular/material/icon";
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";
import {JWT_OPTIONS, JwtHelperService} from "@auth0/angular-jwt";
import {NgbActiveModal, NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {ModalConfirmComponent} from "./shared/modal-confirm/modal-confirm.component";
import {ToastrModule} from "ngx-toastr";

@NgModule({
    declarations: [
        AppComponent,
        UserListComponent,
        UserFormComponent,
        AssociationListComponent,
        HeaderComponent,
        FooterComponent,
        HomeComponent,
        LoginComponent,
        AboutComponent,
        ContactComponent,
        ServicesComponent,
        RegisterComponent,
        PasswordRecoveryComponent,
        ModalConfirmComponent
    ],
    imports: [
        ToastrModule.forRoot(),
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        FormsModule,
        BrowserAnimationsModule,
        SharedModule,
        MatInputModule,
        MatButtonModule,
        MatCardModule,
        MatAutocompleteModule,
        MatIconModule,
        MatProgressSpinnerModule,
        ReactiveFormsModule,
        NgbModule
    ],
    providers: [UserService, AssociationService, {provide: JWT_OPTIONS, useValue: JWT_OPTIONS}, JwtHelperService,
      NgbActiveModal, ModalConfirmComponent],
    bootstrap: [AppComponent]
})
export class AppModule {
}
