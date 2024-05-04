import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../authentication copy/authentication.service';
import { Direction } from 'src/app/models/Direction';
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  role : string | null='';
  token : string | null = '' ;
  nom : string | null = '' ;
  nomDirection : string | null = '' ;
  direct= new Direction();
  constructor(private autheticationServ:AuthenticationService) { 


  
    this.token = sessionStorage.getItem("token");
    this.token = sessionStorage.getItem("token");
    if(this.token !== null )
    {

      this.autheticationServ.getUserInformation()
      .subscribe(response => {
        console.log("TRAITEMENT USER INFO");
        // console.log(response);
        console.log(response['groups']);      //tableau
        const tableRole = response['groups'];
    
    //chercher ROLE 
        this.role = this.autheticationServ.getRole(tableRole);
  
    //chercher DIRECTION (groups) a partir du token
        const tableGROUPE = response['direction'];

        this.nomDirection = this.autheticationServ.getDirection( response['direction']) ;
          if(this.nomDirection !== null)
          {
            this.autheticationServ.getDirectionByName(this.nomDirection).subscribe(response =>{
              this.direct = response;
              console.log("TTTTTTTTTT");
              console.log(response);
              
            
            });
            // this.direction.id = this.direction.id;    
          }

    //chercher NOM
        if(response['given_name']!==null)
        {
          const tableNOM = response['given_name'];
          this.nom =tableNOM;
          console.log('NOM!: '+tableNOM);
  
      //Metraka nom anaty session
          sessionStorage.removeItem('nom');
          sessionStorage.setItem("nom",tableNOM); 
        }
  
    });


    } 
    // if(this.token !== null )
    // {
    //   sessionStorage.removeItem("role");
    //   sessionStorage.removeItem("nom");
    //   sessionStorage.removeItem("direction");

    //   this.autheticationServ.getUserInformation();
    //   this.role = sessionStorage.getItem("role");
    //   this.nom = sessionStorage.getItem('nom');
    //   this.nomDirection = sessionStorage.getItem('direction');
    // }
    

                    
     
  }

  ngOnInit(): void {
  }

}
