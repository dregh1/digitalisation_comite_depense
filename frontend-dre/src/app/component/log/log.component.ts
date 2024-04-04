import { Component, OnInit, Renderer2, ElementRef, AfterViewInit ,ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { Personnel } from 'src/app/models/Personnel';
import { PersonnelService } from 'src/app/services/personnel.service';
import { LogService } from '../../services/log.service';
import { HttpParams, HttpHeaders } from '@angular/common/http';


@Component({
  selector: 'app-log',
  templateUrl: './log.component.html',
  styleUrls: ['./log.component.scss']
})
export class LogComponent implements OnInit, AfterViewInit {
  errorStatus = false;
  errorMessage ='' ;

  personnels: Personnel[] = []; 
  showPassword=false;
  showmesg = false; 

  personnel = {
    id: '' ,
    nom: '',
    prenom: '',
    age: ''
  };
  logindata = {
   
    username: '',
    password: '',
    
  }
  http: any;



  constructor(private logService: LogService, private router: Router ,private personnelService: PersonnelService, private renderer: Renderer2, private el: ElementRef) {}

  ngOnInit(): void {
    this.personnelService.get().subscribe(data => {
      this.personnels = data;
    });
  }

  
//ENVOYE LOGIN & MDP > KEYCLOAK
sendToKc(){
  const body = new HttpParams()
  .set('username', this.logindata.username)
  .set('password', this.logindata.password)
  .set('grant_type', 'password')
  .set('client_id', 'angular-client')
  .set('client_secret', 'eIRXkLaEnLubyFr1mqwv6bu862oHIIn9');

  return this.http.post('http://localhost:8081/realms/oma/protocol/openid-connect/token', body.toString(), {
    headers: new HttpHeaders()
      .set('Content-Type', 'application/x-www-form-urlencoded')
  })
  // // TY RAHA ERREUR TOKEN TSOTRA
  // // .subscribe(
  // //   response  => {
  // //     // Gérer la réponse du jeton avec succès
  // //     console.log('Jeton reçu:', response);
  // //     console.log('\n\n\n\n\n\n');
      
  // //   },
  // //   error => {
  // //     // Gérer les erreurs pendant la requête
  // //     console.error('Erreur lors de l\'obtention du jeton:', error);
  // //     this.logError();
  // //   }
  // );
  .subscribe((response: any) => {
    // Si la requête est réussie, le token est accessible ici
    if (response.hasOwnProperty('access_token')) {
      const token = response.access_token;
  
      // Stockez le token dans le stockage du navigateur ou utilisez-le directement
      console.log('\n\n\n\n\n\n Jeton reçu: \n\n\n\n\n\n ', token);

      // Stocker le jeton dans la session storage du navigateur
      sessionStorage.setItem('token', token);

      // recherche ny role
      this.getUserInfo(token);
      //redirection
     // window.location.href = '/home';

      
    } else {
      // Une erreur s'est produite
      console.error('Erreur lors de l\'obtention du jeton:', response);
      this.logError();

    }
  });
}
getUserInfo(token : string){
  var data = "";

    var xhr = new XMLHttpRequest();
    xhr.withCredentials = true;

    xhr.addEventListener("readystatechange", function() {
      if(this.readyState === 4  && this.status == 200) {
        console.log('ATO: ');
        console.log(this.response);
        const data = JSON.parse(xhr.responseText);
        if(data.hasOwnProperty('groups'))
        {
          const tableRole = data.groups;

          console.log('GROUPE: '+tableRole);

          console.log(data.groups);
          //return this.response.groups;

          for(let i =0 ; i <tableRole.length ; i++)
          {
            if(tableRole[i]==='CDG')
            {
              console.log('C EST UN CDG!!!!!!!!!!!!!!!!!!!!!!');
              window.location.href = '/cdg';
              break;
            }else
            if(tableRole[i]==='PRS')
            {
              console.log('C EST UN PRESCRIPTEUR!!!!!!!!!!!!!!!!!!!!!!');
              window.location.href = '/prescripteur';
              break;
            }else
            if(tableRole[i]==='ACH')
            {
              console.log('C EST UN ACHAT!!!!!!!!!!!!!!!!!!!!!!');
              window.location.href = '/achat';
              break;

            }else
            {
              window.location.href = '/user';
            }

          }
          //sessionStorage.setItem("role",data);

        }else console.log('TSIS ROLE');
      }
    });

    xhr.open("GET", "http://localhost:8081/realms/oma/protocol/openid-connect/userinfo");
    xhr.setRequestHeader("Authorization", "Bearer "+token);

    xhr.send(data);
}


logError()  : void
{
    this.errorStatus = true;
    this.errorMessage = 'Identifiants incorrects';
}
// verifier login  //

verifierLogin(): void {

      
  // etape vers quarkus
  this.logService.post(this.logindata)
    .subscribe((response: any)  => {
      
      // Traitez la réponse du backend si nécessaire
      
      // condition si l'utisateur est autorisé 
      if (response.message === 'Authentification réussie') {
        // Rediriger l'utilisateur vers la page d'accueil
        this.router.navigate(['/home']);
    }else{
      // Afficher un message d'erreur à l'utilisateur
      console.log("DISO A");
      this.errorStatus = true;
      this.errorMessage = 'Identifiants incorrects';
  }


      console.log(response);
      
      this.showmesg=true;
      
      
    });
}
  onSubmit(): void {
    this.personnelService.post(this.personnel).subscribe(response => {
      console.log(response);
      this.showmesg = true;

      setTimeout(() => {
        this.showmesg = false;
      }, 2000);
      window.location.reload();
    });
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
  ///////////////////////////Animation slide///////////////////////////////////////
  ngAfterViewInit() {
    const signup = this.el.nativeElement.querySelector('.signup');
    const login = this.el.nativeElement.querySelector('.login');
    //const slider = this.el.nativeElement.querySelector('.slider');
    const formSection = this.el.nativeElement.querySelector('.form-section');

    signup.addEventListener('click', () => {
    //  this.renderer.addClass(slider, 'moveslider');
      this.renderer.addClass(formSection, 'form-section-move');
    });

    login.addEventListener('click', () => {
      //this.renderer.removeClass(slider, 'moveslider');
      this.renderer.removeClass(formSection, 'form-section-move');
    });
  }

}
