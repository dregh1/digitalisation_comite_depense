import { Component, OnInit } from '@angular/core';
import { SuperAdminService } from './super-admin.service';
import { User } from 'src/app/models/User';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { UtilitaireService } from 'src/app/service/utilitaire.service';
import { Role } from 'src/app/models/Role';
import { NEVER, never } from 'rxjs';

@Component({
  selector: 'app-super-admin',
  templateUrl: './super-admin.component.html',
  styleUrls: ['./super-admin.component.scss']
})
export class SuperAdminComponent implements OnInit {
joindreGroupe() {
  this.groupe = "DTI";

}
  alertInsertion = false;
  
  gestionUser = true;
  gestionDonnee = false;
  currentPage = 1 ;

  listUserExistant : User[] =[] ;     //list users existant chez ldap via keycloak 

  nomAchercher : string = '';

  resultatRecherche : User[] = [];
  // 0: Object { id: "01ee97f2-5131-440e-8e59-9e404ee7a27b", name: "CDG", description: "Controlleur de Gestion", … }
  ​
  // 1: Object { id: "9f5c4696-7e73-4030-b5eb-2c079a649437", name: "default-roles-oma", description: "${role_default-roles}", … }
  ​
  // 2: Object { id: "095fcfba-8d6e-4abf-abcd-c6a21709c955", name: "PRS", description: "prescripteur", … }
  ​
  // 3: Object { id: "6a68b6d7-448c-423d-801d-75f30ffc9a67", name: "ACH", 

  roleList : Role [] = [];

  role = "";
  groupe = "";


  roleToAssign = "";
  // role


  rola : string = 'ACH';
  constructor(private superAdm : SuperAdminService, private http: HttpClient , private utilitaire : UtilitaireService) { }

  ngOnInit(): void {
    this.superAdm.getTokenAdmin();
    this.rechercherAll();
    this.getAllRole();
    
  }


  afficherGestionUser(){
    this.gestionUser=true;
    this.gestionDonnee =false; 
  }

    afficherGestionDonnee(){
      this.gestionUser=false;
      this.gestionDonnee =true;   
    }

  rechercherAll()  // recuperer les users 
  {
    // this.superAdm.getTokenAdmin();
    this.superAdm.getAllUser().subscribe(
      (data)=>
          {

            const users : User[] =[];
            for(let i = 0 ; i<data.length ; i++)
            { 
              const user = new User();
              user.id = data[i].id;
              user.lastName = data[i].lastName;
              user.firstName = data[i].firstName;
              users.push(user);
            }
            this.listUserExistant = users;
            console.log(users);
            
          },
      (error)=>{console.error(error)}
    
    );
  }

  uploadFile() {
    // Récupère le fichier sélectionné par l'utilisateur via l'élément input
    const fileInput = document.getElementById('file-input') as HTMLInputElement;
    const file = fileInput.files? fileInput.files[0] : null;
  
    // Vérifie si un fichier a été sélectionné
    if (!file) {
      alert('Veuillez sélectionner un fichier.');
      return;
    }

    this.onFileSelected({ target: fileInput } as unknown as Event);
   
     const reader = new FileReader();
    reader.onload = (e: ProgressEvent<FileReader>) => {
      let csvData = e.target?.result;
      
      if (csvData && e.target) {
        // Traiter les données CSV ici
            if (typeof e.target.result === 'string') {
              csvData = e.target.result;
            } else if (e.target.result instanceof ArrayBuffer) {
              // Convertir ArrayBuffer en String
              const decoder = new TextDecoder();
              csvData = decoder.decode(e.target.result);
            } else {
              console.error("Le résultat n'est ni une chaîne de caractères ni un ArrayBuffer.");
              return;
            }


        // Exemple de traitement
        let lines = csvData.split('\n');
       
        // console.log(lines);
        

        let correctLines : string [] = [];
        
        for( let i =0; i< lines.length ; i++)
        {
          correctLines.push(lines[i].replace("\r","")) ;
        }
            console.log(correctLines);
            
            // insertion en masse des rubrique
            this.superAdm.insertionRubriques(correctLines).subscribe(
              (response)=>{console.log(response);}, 
              (error)=>{console.error(error)}, 
            );
            
            
      } else {
        console.error("Erreur lors de la lecture du fichier");
      }
    };
    reader.readAsText(file);
    // Appelle la méthode onFileSelected pour traiter le fichier
    this.alertInsertion = true;

  }

  onFileSelected(event :Event) {
    const file = (event.target as HTMLInputElement).files?.[0];
    
    if (!file) {
      console.error("Aucun fichier sélectionné");
      return;
    }

    
  }

  rechercheUser()
  {
    const tableUser : User[] =[];

    // exp regulier ignorant les majuscul
    const regex = new RegExp(this.nomAchercher.toLowerCase(),'i');

    this.resultatRecherche = this.listUserExistant.filter(user =>
          regex.test( user.firstName.toLowerCase() ) ||
          regex.test( user.lastName?.toLowerCase() ) 
      );

   console.log(this.resultatRecherche);
    
  }


  //ASSIGNER ROLE
  // assigneRole ()
  // {
  //   var token = sessionStorage.getItem('tokenAdmin');
  //   var data = JSON.stringify([
  //     {
  //       "id": "6a68b6d7-448c-423d-801d-75f30ffc9a67",
  //       "name": "ACH"
  //     }
  //   ]);
    
  //   var xhr = new XMLHttpRequest();
  //   xhr.withCredentials = true;
    
  //   xhr.addEventListener("readystatechange", function() {
  //     if(this.readyState === 4) {
  //       console.log(this.responseText);
  //     }
  //   });
    
  //   xhr.open("POST", "http://localhost:8083/admin/realms/oma/users/13634f98-71b2-4122-b530-b258629fa7f5/role-mappings/realm");
  //   xhr.setRequestHeader("Content-Type", "application/json");
  //   xhr.setRequestHeader("Authorization", "Bearer "+token);
    
  //   xhr.send(data);
  // }


  iduser : string ="13634f98-71b2-4122-b530-b258629fa7f5";

    onChange()
    {
      this.resultatRecherche = [];
      this.role = "";
      this.groupe = "";
    }

  assigneRole() {

    const token = sessionStorage.getItem('tokenAdmin');
    let roleTableToSend : [] = [];

    this.role = this.roleToAssign ;
    console.log("*-*-**-*-*-*-*-*-*");
    console.log(this.roleToAssign);
    
    let roleToSend = this.getRoleById(this.roleToAssign);

      // if(roleToSend ) 
      
      // roleTableToSend.push(roleToSend);
    
    // const data = [
    //   {
    //     "id": "6a68b6d7-448c-423d-801d-75f30ffc9a67",
    //     "name": "ACH"
    //   }
    // ];

    // // Création d'un objet HttpHeaders pour définir les en-têtes nécessaires
    // const headers = new HttpHeaders({
    //   'Content-Type': 'application/json',
    //   'Authorization': `Bearer ${token}`
    // });

    // // Utilisation de HttpClient pour envoyer la requête POSt
    // this.http.post('http://localhost:8083/admin/realms/oma/users/'+this.iduser+'/role-mappings/realm', data, { headers })
    //  .subscribe(response => {
    //     console.log(response);
    //   }, error => {
    //     console.error(error);
    //   });

    this.roleToAssign
  }

  getIdUser(id : string)
  {
    this.iduser=id;

    console.log("------A------");
    console.log(this.iduser);
    
  }

  //teste envoye mail
  envoyerMail(){
    this.utilitaire.sendMail();
  }


  // fonction get all roles
  getAllRole()
  {
    this.superAdm.getAllRoles().subscribe(
      (response)=>{  this.roleList = response ; console.log(response ); console.log("/////////////////////////" );
      },
      (error)=>{})
  }



  getRoleById( id : string ) : Role | null
  {
    
    for(let item of this.roleList  )
    {
        if(item.id === id)
        return item
    }
    
    return null;

  }
}
