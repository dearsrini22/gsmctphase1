import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {JwtHelperService} from "@auth0/angular-jwt";

const AUTH_API = 'http://localhost:8090/auth/';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient, private jwtHelper: JwtHelperService) {
  }

  login(email: string, password: string): Observable<any> {
    return this.http.post(AUTH_API + 'signin', {email, password}, httpOptions);
  }

  register(username: string, email: string, password: string): Observable<any> {
    return this.http.post(AUTH_API + 'signup', {
      username,
      email,
      password
    }, httpOptions);
  }

  getToken(): string | null {
    return sessionStorage.getItem('auth-token')
  }

  isAuthenticated(): boolean {
    const auth_token = sessionStorage.getItem('auth-token');
    if (auth_token == null)
      return false
    return !this.jwtHelper.isTokenExpired(auth_token);
  }

  logout() {
    sessionStorage.removeItem('auth-token');
  }
}
