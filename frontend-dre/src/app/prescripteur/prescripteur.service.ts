import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, timestamp } from 'rxjs';
import { Periode } from '../models/Periode';
import { Rubrique } from '../models/Rubrique';
import { Fournisseur } from '../models/Fournisseur';
import { Sousrubrique } from '../models/Sousrubrique';
import { Brouillon } from '../models/Brouillon';
import { Active_dmd } from '../models/Active_dmd';

@Injectable({
  providedIn: 'root'
})
export class PrescripteurService {
  private url = 'http://localhost:8080';

  private baseUrl = 'http://localhost:8080/teste';
  private baseUrl2 = 'http://localhost:8080/prescripteur';
 
  constructor(private http: HttpClient) { }

  // maka ny _ periode_demande
  // session_cd = {
  //   id   : Number,
  //   ref      :String,
  //   date_cloture      :timestamp,
  //   is_deleted  : Boolean,
  //   id_direction  : Number,  
  //   taux_eur  : Number,
  //   taux_usd  : Number,
  //   taux_gbp  :Number,
  //   taux_mga  :Number,
  //   is_closed :Boolean
  // }
  


// maka periode
  getPeriode(): Observable<Periode[]> {
    return this.http.get<Periode[]>(this.baseUrl+'/periode/get');
  }
  
// maka rubrique
  getRubrique(): Observable<Rubrique[]> {
    return this.http.get<Rubrique[]>(this.baseUrl+'/rubrique/get');
  }
// maka periode
  getFournisseur(): Observable<Fournisseur[]> {
  return this.http.get<Fournisseur[]>(this.baseUrl+'/fournisseur/get');
}

// maka periode
getSousrubrique(): Observable<Sousrubrique[]> {
  return this.http.get<Sousrubrique[]>(this.baseUrl+'/sousrubrique/get');
}

// maka ny brouillon
getBrouillon():  Observable<Brouillon[]> {
  return this.http.get<Brouillon[]>(this.baseUrl2+'/brouillon/get');
}

// maka ny brouillon
getActive():  Observable<Active_dmd[]> {
  return this.http.get<Active_dmd[]>(this.baseUrl2+'/active_dmd/get');
}

  //  CREATE DEMANDE
createDemande(formData: any): Observable<any> {
    return this.http.post<any>(this.baseUrl+'/demande/create', formData);
  }
  
  post(formData: any): Observable<any> {
    // console.log()    
    return this.http.get<any>(this.baseUrl+'/get');
  }

  // set commentaire achat
  setComsAchat(formData: any): Observable<any> {
    return this.http.post<any>(this.baseUrl2+'/achat/commentaire/create', formData);
  }
  // get devise
  // maka ny brouillon
  getDevise():  Observable<FormData[]> {
   return this.http.get<FormData[]>(this.baseUrl2+'/devise/get'); 
  }

  // maka ny reference
  getReference():  Observable<FormData[]> {
    return this.http.get<FormData[]>(this.baseUrl2+'/reference/get'); 
   }

}