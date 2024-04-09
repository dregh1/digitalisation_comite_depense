import { Component, OnInit } from '@angular/core';
import { MenuService } from './menu.service';
import { Brouillon } from 'src/app/models/Brouillon';
import { AuthenticationService } from '../authentication/authentication.service';
@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit {
  heure:Date=new Date();
  
    
  role : string | null='';
  token : string | null = '' ;

  brouillons : Brouillon [] = [];

  activeDmds : Brouillon [] = [];

  constructor(private MenuSerice:MenuService , private autheticationServ : AuthenticationService){
      this.token = sessionStorage.getItem("token");
      if(this.token !== null )
      {
        this.autheticationServ.getUserInfo(this.token);
        this.role = sessionStorage.getItem("role");
        // sessionStorage.removeItem("role");
        // window.location.reload();
      }
  }
 
  ngOnInit(): void {
   
    //MAKA BROUILLON
      this.MenuSerice.getBrouillon().subscribe(brouillons => {
        this.brouillons = brouillons;
        // window.location.reload();
        //maka direction sy role
        //Direction
        //Role
      });

    //MAKA ACTIVE_DMD
      this.MenuSerice.getActiveDmd().subscribe(active_dmd => {
        this.activeDmds = active_dmd;
        console.log("active_dmd:");
        console.log(active_dmd);
      });
   
     
      
    // const nomdir = sessionStorage.getItem("direction");
    // if(nomdir)
    // {
    //   console.log("nomdir:");
    //   console.log(nomdir);

    //   // const iddirection = this.autheticationServ.getIdDirectionByName(nomdir); 
    //   // console.log("iddirection:");
    //   // console.log(iddirection);
    //   this.getIdOfDirection(nomdir);
      
      
    // }

    
    
}

getIdOfDirection (nomDirection  : string ) 
    {
      const nomDirectionn  : string = "DTI";
      
      this.autheticationServ.getIdDirectionByName(nomDirection)
              .subscribe(response => {
                              console.log( response);
                            }
                        ); 
      console.log(nomDirection);

    }

}
