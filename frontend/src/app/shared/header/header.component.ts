import {Component} from '@angular/core';
import {AuthService} from "../../service/auth.service";
import {Router} from "@angular/router";
import {RegisterComponent} from "../../component/register/register.component";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {
  successMessage = '';

  constructor(private authService: AuthService, private modalService: NgbModal, private router: Router) {
  }

  isLoggedIn(): boolean {
    return this.authService.isAuthenticated()
  }

  logout() {
    this.authService.logout()
    this.router.navigate(['/login']).then(r => console.log("Logged out = " + r))
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
}
