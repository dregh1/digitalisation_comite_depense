import { HttpParams, HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { MyMail } from 'src/app/models/MyMail';
import { Role } from 'src/app/models/Role';

@Injectable({
  providedIn: 'root'
})
export class SuperAdminService {

  private baseUrl = 'http://localhost:8080/teste';

  constructor(private http: HttpClient) { }

  // private getHeadersAdmin(): HttpHeaders {
              
  //   const token = sessionStorage.getItem('tokenAdmin');                  // Recuperation token
  
  //       if (token) {
  //         return new HttpHeaders({ Authorization: `Bearer ${token}` });
  //       } else {
  //         // Handle the case where no token is found (e.g., throw an error or redirect to login)
  //         throw new Error('No authorization token found');
  //       }
  // }

   //insertion de rubrique en masse
   insertionRubriques(formData: string[]): Observable<any> {

    const headers = this.getHeaders();
    return this.http.post<string[]>(this.baseUrl + '/rubrique/add', formData, {
      headers,
    });

  }

  private getHeaders(): HttpHeaders {
    const token = sessionStorage.getItem('token'); // Replace with your token retrieval logic

    if (token) {
      return new HttpHeaders({ Authorization: `Bearer ${token}` });
    } else {
      // Handle the case where no token is found (e.g., throw an error or redirect to login)
      throw new Error('No authorization token found');
    }
  }

  private getHeadersAdmin(): HttpHeaders {
              
    const token = sessionStorage.getItem('tokenAdmin');                  // Recuperation token
  
        if (token) {
          return new HttpHeaders({ Authorization: `Bearer ${token}` });
        } else {
          // Handle the case where no token is found (e.g., throw an error or redirect to login)
          throw new Error('No authorization token found');
        }
  }
  getTokenAdmin()
  {

    const url = 'http://localhost:8083/realms/oma/protocol/openid-connect/token';

    const params = new HttpParams()
    .set('grant_type', 'password')
    .set('client_id', 'angular-client')
    .set('client_secret', 'F6ONL3ox63NBv1h1J5wmmibHlDhLA1MI')
    .set('username', 'charlesandrea')
    .set('password', 'password');



    // GET TOKEN ADMIN
    return this.http
    .post( url, params.toString(), 
        {  
          headers: new HttpHeaders().set('Content-Type','application/x-www-form-urlencoded'),
        }
      ).subscribe((response: any) =>
          {  


            //recuperation du token de l'admin keycloak
            if (response.hasOwnProperty('access_token') ) {
              const tokenAdmin = response.access_token;
              sessionStorage.setItem('tokenAdmin',tokenAdmin);
              
              
            } else 
            {
              console.error("Authentification admin failed");
              
            }
          }) ;
    

  }

  //GET ALL USERS
  getAllUser()
  {
    const url = "http://localhost:8083/admin/realms/oma/users";
  
    const headers = this.getHeadersAdmin();
    return this.http.get<any[]>(url,{headers});
    
  }

  // les CDG
  //http://localhost:8083/admin/realms/oma/users/13634f98-71b2-4122-b530-b258629fa7f5/groups
  
  // les ACH
  //http://localhost:8083/admin/realms/oma/roles/ACH/users
  

  async getEmailSoumission(): Promise<MyMail[]> {
  
    const urlAchat = "http://localhost:8083/admin/realms/oma/roles/ACH/users";
    const urlCdg = "http://localhost:8083/admin/realms/oma/roles/CDG/users";
    
    const ursPRS = "http://localhost:8083/admin/realms/oma/roles/PRS/users";
  
    const headers = this.getHeadersAdmin();
    try {
      const httpResponse = await this.http.get<any>(urlAchat, { headers }).toPromise();
      const httpResponseCdg = await this.http.get<any>(urlCdg, { headers }).toPromise();
      
      if (!httpResponse  ) {
        // console.error('Aucune réponse valide reçue', httpResponse);
        throw new Error('Aucune réponse valide reçue pour la liste ACH');
      }

      if (!httpResponseCdg  ) {
        // console.error('Aucune réponse valide reçue', httpResponseCdg);
        throw new Error('Aucune réponse valide reçue pour la liste CDG');
      }

      const responseAch = httpResponse; // Assurez-vous que la réponse contient les données attendues
      const responseCdg = httpResponseCdg; // Assurez-vous que la réponse contient les données attendues

      const mails: MyMail[] = [];
      for (let i = 0; i < responseAch.length; i++) {
        if (responseAch[i].hasOwnProperty('email') && responseAch[i].hasOwnProperty('username')) {
          const oneMail = new MyMail(responseAch[i].username, responseAch[i].email);
          mails.push(oneMail);
        }
      }

      for (let i = 0; i < responseCdg.length; i++) {
        if (responseCdg[i].hasOwnProperty('email') && responseCdg[i].hasOwnProperty('username')) {
          const oneMail = new MyMail( responseCdg[i].username,responseCdg[i].email);
          // const oneMail = new MyMail();
          // oneMail.email = responseCdg[i].email;
          // oneMail.username = responseCdg[i].username;
          mails.push(oneMail);
        }
      }


      console.log('_-_-_-_-_-_-_-_-_-_-__-_-_-_-_-_-_-_-_-_-_');
      
      console.log(mails); // Pour le débogage
      return mails; // Retourne le tableau de MyMail
    } catch (error) {
      console.error(error);
      throw error; // Propage l'erreur si nécessaire
    }
  }


  getAllRoles (): Observable<Role[]>
  {
      return this.http.get<Role[]>(this.baseUrl + '/roles');
  }
}
