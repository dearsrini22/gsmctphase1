import {Component} from '@angular/core';
import {AuthService} from "../../service/auth.service";
import {TokenStorageService} from "../../service/token-storage.service";
import {FormControl, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {RegisterComponent} from "../register/register.component";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  email = new FormControl('', [Validators.required, Validators.email]);
  password: FormControl = new FormControl('', [Validators.required]);
  showPassword: boolean = false;

  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  successMessage = '';
  roles: string[] = [];

  constructor(private authService: AuthService, private tokenStorage: TokenStorageService, private modalService: NgbModal,
              private router: Router) {
  }

  login() {
    if (this.email.hasError('required') || this.email.value == null) {
      this.errorMessage = 'Email Required';
    } else if (this.email.hasError('email')) {
      this.errorMessage = 'Not a valid Email';
    } else if (this.password.hasError('required') || this.password.value == null) {
      this.errorMessage = 'Password Required';
    } else {
      this.authService.login(this.email.value, this.password.value).subscribe({
        next: data => {
          this.tokenStorage.saveToken(data.token);
          this.isLoginFailed = false;
          this.isLoggedIn = true;
          this.roles = this.tokenStorage.getUser().roles;
        },
        error: err => {
          this.errorMessage = 'Please check your credentials and try again.';
          this.isLoginFailed = true;
        },
        complete: () => {
          this.router.navigate(['/dashboard']).then(r => console.log('Logged In: ' + r))
          this.clear()
        }
      });
    }
  }

  signUp() {
    this.modalService.open(RegisterComponent, {
      ariaLabelledBy: 'modal-basic-title',
      backdrop: 'static',
      size: 'lg',
      windowClass: 'modal-lg',
      keyboard: false
    }).result.then(data => {
        this.successMessage = data;
      },
      (reason) => {
      });
  }

  clear() {
    this.email.setValue("")
    this.password.setValue("");
    this.errorMessage = ""
  }

  togglePasswordVisibility(): void {
    this.showPassword = !this.showPassword;
  }
}
