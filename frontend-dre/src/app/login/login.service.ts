import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Personnel } from '../models/Personnel';


const httpsOptions = {
  headers : new HttpHeaders({'Content-Type':'application/json'})
};


@Injectable({
  providedIn: 'root'
})
  


//// start------------ TY IZY RAHA HAMPIASA KEYCLOAK
export class LoginService {
  // private baseUrl = 'http://localhost:8081/realms/oma/protocol/openid-connect/token';
  private baseUrl = 'http://localhost:8080/authent';
  constructor(private http: HttpClient) { }
  
  //mandefa login sy mdp any amin quarkus
  post(formData: any): Observable<any> {
    return this.http.post<any>(this.baseUrl+'/check', formData);
  }
}
