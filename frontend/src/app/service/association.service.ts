import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {WebApiService} from "./web-api.service";
import {AssociationSearch} from "../model/association-search";
import {HomeOwner} from "../model/home-owner";

@Injectable({
  providedIn: 'root'
})
export class AssociationService {

  private readonly apiUrl: string;
  private readonly noAuthApiUrl: string;

  constructor(private http: HttpClient, private apiService: WebApiService) {
    this.apiUrl = 'http://localhost:8090/api/';
    this.noAuthApiUrl = 'http://localhost:8090/auth/';
  }

  findAllStates(): Observable<any> {
    return this.apiService.get(this.noAuthApiUrl + 'states');
  }

  findAllAssociations(model: AssociationSearch): Observable<any> {
    return this.apiService.post(this.noAuthApiUrl + 'associations', model);
  }

  register(model: HomeOwner): Observable<any> {
    return this.apiService.post(this.noAuthApiUrl + 'home-owner/register', model);
  }

  searchAssociation(): Observable<any> {
    return this.apiService.post(this.apiUrl + 'search/association', {});
  }

  getAssociation(associationId: number): Observable<any> {
    return this.apiService.get(this.apiUrl + 'association/' + associationId);
  }

  saveAssociation(model: any): Observable<any> {
    return this.apiService.post(this.apiUrl + 'association', model);
  }

  deleteAssociation(associationId: number): Observable<any> {
      return this.apiService.delete(this.apiUrl + 'association/' + associationId);
  }
}
