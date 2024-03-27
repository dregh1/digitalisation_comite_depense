import { HttpParams, HttpHeaders, HttpClient,HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Personnel } from 'src/app/models/Personnel';
import { PersonnelService } from 'src/app/services/personnel.service';
import { HomeService } from './home.service';
import { NumberSymbol } from '@angular/common';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  
//CREATION SESSION
  formData ={
    ref : "",
    date_cloture : Date,
    id_direction : Number,
    taux_eur : Number,
    taux_usd : Number,
    taux_gbp  : Number,
    taux_mga  : Number,
    
  }


  userName='';
  personnels: Personnel[] = []; 
  
  showPassword=false;
  showmesg = false; 

  personnel = {
    id: '' ,
    nom: '',
    prenom: '',
    age: ''
  };
 



  constructor(private homeService: HomeService,private personnelService: PersonnelService,private http: HttpClient) {}
  ngOnInit(): void {
    this.personnelService.get().subscribe(data => {
      this.personnels = data;
    });

    var token = sessionStorage.getItem('token');
    // maka ny momba azy
    if(token !== null)
    {
          const body = new HttpParams()
          .set('token', token )
          .set('client_id', 'angular-client')
          .set('client_secret', 'eIRXkLaEnLubyFr1mqwv6bu862oHIIn9');
      
            this.http.post('http://localhost:8081/realms/oma/protocol/openid-connect/token/introspect', body.toString(), {
            headers: new HttpHeaders()
              .set('Content-Type', 'application/x-www-form-urlencoded')
              
           }).subscribe(
              (data) => {
                // Traiter la réponse du serveur
                console.log('Utilisateur connecté:', data);
              },
              (error) => {
                // Gérer l'erreur
                console.error('Erreur de connexion:', error);
              }
          );
          //  .subscribe(
              // response  => {
              //   // Gérer la réponse du jeton avec succès
              //   // console.log('HOme: Jeton reçu:', response);
              //   console.log('\n\n\n\n\n\n nis response \n\n\n\n\n\n');
                
              // },
              // error => {
              //   // Gérer les erreurs pendant la requête
              //   // console.error('Erreur lors de l\'obtention du jeton balbal:', error);
               
              // }
            // );
          //.subscribe((response: any) => {
          //   // Si la requête est réussie, le token est accessible ici
          //   if (response.hasOwnProperty('name')) {
          //     console.log('ito:');
          //     var token = sessionStorage.getItem('token');
          //     console.log("ty le token\n\n\n\n\n"+ token);
          //     console.log('vita:')

          //     const tokenOwner = response.name;
          //     this.userName = response.name;
          //     // Stockez le token dans le stockage du navigateur ou utilisez-le directement
          //     console.log('\n\n\n\n\n\n Je m appelle: \n\n\n\n\n\n ', tokenOwner);

            

              
          //   } else {
          //     // Une erreur s'est produite
          //     console.error('Vous etes hacker!', response);
              
          //   }
          // });
    }

  }
  deletePersonnel(id: number): void {
    this.personnelService.delete(id).subscribe(response => {
      console.log(response);
      window.location.reload();
    });
  }

  updateRecord(id: any, newData: any): void {
    this.personnelService.update(id, newData).subscribe(response => {
      console.log(response);
      window.location.reload();
    });
  }

  // submit bouton ouvrir session
  openSession(){

    // envoie donnee de session vers quarkus
    
    console.log(this.formData);

      var dateString  = this.formData.date_cloture.toString();
        var parts = dateString.split("T");
        var formatDate = parts[0];
        var date = formatDate.split("-");  
        var ref = "CD-"+date[2]+date[1]+date[0] ;
      
      this.formData.ref=ref;
      // this.formData.id_direction = "1".toString();
      
        console.log("ty le ref: " + this.formData.ref);
      
      console.log("ty le formData");
      console.log(this.formData);

      
        // creation session
    this.homeService.post(this.formData).subscribe
    (response => {
      console.log(response);
      window.location.reload();
    });
    
  }
}
