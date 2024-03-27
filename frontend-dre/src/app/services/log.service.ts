import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
//// start------------ post vers KEYCLOAK
export class LogService {
  private baseUrl1 = 'http://localhost:8081/realms/oma/protocol/openid-connect/token';
  //  window.location.href = 'http://localhost:8081/auth/realms/quarkus/protocol/openid-connect/auth?response_type=code&client_id=quarkus-app&redirect_uri=http://localhost:8080/redirect';
  private baseUrl = 'http://localhost:8080/authent';  
  
  constructor(private http: HttpClient) { }
  //mandefa login sy mdp any amin quarkus
  post(formData: any): Observable<any> {
    return this.http.post<any>(this.baseUrl+'/check', formData);
  }


  //postKeycloak avec l'en-tête Content-Type défini à application/x-www-form-urlencoded dans votre requête HTTP.
  postKey(formData: any): Observable <any>{
    console.log("mbola maheno");
    
    return this.http.post<any>(this.baseUrl1, formData);
  }
}



  // //mandefa login sy mdp any amin quarkus
  // post(formData: any): Observable<any> {
  //   return this.http.post<any>(this.baseUrl+'/check', formData);
  // }