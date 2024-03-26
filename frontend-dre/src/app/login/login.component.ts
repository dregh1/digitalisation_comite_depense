import { Component, OnInit } from '@angular/core';
import { LoginService } from './login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  showmesg=false;
  errorStatus = false;
  errorMessage ='' ;
  logindata = {
   
    username: '',
    password: '',
    
  }
  // LoginService: any;
  constructor(private loginService: LoginService, private router: Router )
  {
   
  }

  ngOnInit(): void {
  }
  login(): void {
    // Rediriger l'utilisateur vers l'URL d'authentification de Keycloak
    // window.location.href = 'http://localhost:8081/realms/oma/protocol/openid-connect/token';

   window.location.href = 'http://localhost:8081/auth/realms/quarkus/protocol/openid-connect/auth?response_type=code&client_id=quarkus-app&redirect_uri=http://localhost:8080/redirect';
  }

  //// start------------ TY IZY RAHA HAMPIASA LOGIN TANANA
  onSubmit(): void {

      
    // etape vers quarkus
    this.loginService.post(this.logindata)
      .subscribe((response: any)  => {
        
        // Traitez la réponse du backend si nécessaire
        
        // condition si l'utisateur est autorisé 
        if (response.message === 'Authentification réussie') {
          // Rediriger l'utilisateur vers la page d'accueil
          this.router.navigate(['/accueil']);
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


}
// setTimeout(() => {
        //   this.showmesg=false;
        // }, 2000);

        
        //window.location.reload();

          //// start------------ TY IZY RAHA HAMPIASA KEYCLOAK
  //post
  // onSubmit(): void {
  //   const obj= {
  //     username: 'Charles Andrea',
  //     password: 'password',
  //     client_id: 'frontclient',
  //     client_secret:'r2RC9wcCqyrNAMjbC1pB7VllrU7lVzeH',
  //     grant_type :'password'

  //   }
  //   // this.LoginService.post(this.personnel)
  //   this.loginService.post(obj)
  //     .subscribe((response: any)  => {
  //       // Traitez la réponse du backend si nécessaire
  //       console.log("TY LE reponse");
  //       console.log(response);
  //       console.log("VITA LE reponse");

  //       this.showmesg=true;
        
  //       setTimeout(() => {
  //         this.showmesg=false;
  //       }, 2000);
  //       window.location.reload();
  //     });
  // }
  ////  TY IZY RAHA HAMPIASA KEYCLOAK ------------ end