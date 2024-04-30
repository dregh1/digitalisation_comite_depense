import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../authentication copy/authentication.service';
import { Direction } from 'src/app/models/Direction';
import { ValidationService } from './validation.service';
import { Periode } from 'src/app/models/Periode';
import { Demande } from 'src/app/models/Demande';
import { DetailDemande } from 'src/app/models/DetailDemande';
import { Fournisseur } from 'src/app/models/Fournisseur';
//import * as XLSX from 'xlsx';
@Component({
  selector: 'app-validation',
  templateUrl: './validation.component.html',
  styleUrls: ['./validation.component.scss']
})
export class ValidationComponent implements OnInit {
  isUp1 = false; // Initial state for first button
  errorStatus = false;
  role : string | null='';
  token : string | null = '' ;
  DetailDemande : DetailDemande [] = [];
  direction = new Direction();
  nomDirection : string | null ='';
  fournisseurs : Fournisseur[] = [];
  periodes: Periode[]=[];
  demandes= new Demande();
  donnee=new Demande();   texte:String='';
   d = new Demande ();
  demande
  ={
    id:'',
      estRegularisation    :false,
      periode:'',
      idRubrique:'',
      sousRubrique : '',
      motif               : '',
      devise : '',
      typeDevise : '',
      comsPrescripteur :'',
      idDirection:'',
      idTitreDepense    : '',
      nomReference : '',
      titre:'',
      idFournisseur      :'',
      montantHt          :'',
      fournisseur:'',
      idPeriode          : '',
      validationPrescripteur:false,
      validationAchat:false,
      validationCdg:false,
      typeReference: '',
     idDemande:'',
    idperiode:'',
    comsCd:'',etatFinal:''
  }
  detail={
    comsCd:'',
    idperiode:''
  }
  errorMessage:string='';
  toggleUp() {
    this.isUp1 = !this.isUp1;
  }
  comsCd : string | null='';idPeriode : string | null='';etatfinal: string | null='';
detaildemande =new DetailDemande();
  constructor(private autheticationServ:AuthenticationService,private ValidationService:ValidationService) { 
    this.token = sessionStorage.getItem("token");
    if(this.token !== null )
    {
      this.autheticationServ.getUserInfo(this.token);
      this.role = sessionStorage.getItem("role");


      this.nomDirection = sessionStorage.getItem('direction');
                    
      if(this.nomDirection !== null)
      {
        this.autheticationServ.getDirectionByName(this.nomDirection).subscribe(response =>{ this.direction = response})
        this.direction.id = this.direction.id;    
      }
    }
    

    
  }

  ngOnInit(): void {
    ///maka detaildemande
    this.ValidationService.getBrouillon().subscribe(DetailDemande => {
      this.DetailDemande = DetailDemande;
      //maka par detail
      console.log(DetailDemande);
    });
     // maka ny periode
     this.ValidationService.getPeriode().subscribe(data => {
      this.periodes = data;
    });
    // this.detailvalues.forEach((detail) => {
    //   this.detailvalues[detail.id]=detail.coms;
    // });
   ///maka decision
    // this.ValidationService.getcomsCdByid(this.id).subscribe(response=> {
    //   this.Decision = response;
    //   console.log(response,"////////////////");
    //   this.decision.commentaireCd = this.Decision ;
  
    //////////Affichage du commentaire Cdg
  //   this.ValidationService.getCdgById(this.DetailDemande.).subscribe(response=> {
  //     this.aviscdgs = response;
    
  //   // maka avisAchat
  //   this.ValidationService.getAchatById(this.id).subscribe(response=> {
  //     this.avisAchat = response;
  //     });
  // }

  //setdemande
  //set selected option
  setTimeout(()=>{
    this.setSelected("13", "idPeriode");
  }, 1000);
    }
getid(idperiode:any){
}

 
    //set SELECTED OPTION
    setSelected(id : string,idHtml : string)
  {
    console.log("ato!");

    const selectelement = document.getElementById(idHtml);
    if(selectelement!==null)
    {
    console.log("Ito!");

      
      const lesOptions = selectelement?.querySelectorAll('option');
        for(let i = 0 ; i < lesOptions.length ; i++)
        {
          const unOption =  lesOptions[i];
          if(unOption.value === id) 
          {
            console.log("TEHAKA",id);
            unOption.selected = true;
            // this.demande.idTitreDepense = unOption.value; 
          }
        }
    };
  }
  onChampModifie(index: any, nouvelleValeur: string| undefined,idPeriode:any,de:any,etatFinal:any) {
 setTimeout(() => {   // Optionally, clear the error message
   
    this.DetailDemande[index].comsCd = nouvelleValeur;
    this.comsCd=nouvelleValeur??"";
    this.idPeriode=idPeriode;
    this.etatfinal=etatFinal;
    this.enregistrer(de)
    //console.log("la valeur ",nouvelleValeur);
    this.enregistrer(de);
  }, 3000);
  }
  ///modication demande
    enregistrer(de:any){
         //maka par detail
    this.ValidationService.getdemande(de).subscribe(response=> {
      this.demandes= response;
      //console.log(response,"////////////////");
      this.demande.estRegularisation = Boolean(this.demandes.estRegularisation ?? "");
      this.demande.idTitreDepense = this.demandes.idTitreDepense ?.toString() ??"";
      this.demande.typeReference= this.demandes.typeReference?? "";
      // this.demande.typereference=this.demande.typereference;
      this.demande.nomReference = this.demandes.nomReference??"" ;
      this.demande.motif = this.demandes.motif ?? "";
      this.demande.typeDevise = this.demandes.typeDevise ?? "";
      this.demande.comsPrescripteur = this.demandes.comsPrescripteur??"" ;
      this.demande.idFournisseur = this.demandes.idFournisseur?.toString() ??"" ;
      this.demande.montantHt = this.demandes.montantHt?.toString() ??"" ;
      this.demande.idDirection = this.demandes.idDirection?.toString() ?? "";  
      this.demande.sousRubrique=this.demandes.sousRubrique?.toString()?? "";
      this.demande.idRubrique=this.demandes.idRubrique?.toString() ?? "";
      this.demande.validationPrescripteur=Boolean(this.demandes.validationPrescripteur??"");
      this.demande.validationCdg=Boolean(this.demandes.validationCdg??"");
      this.demande.validationAchat=Boolean(this.demandes.validationAchat??"");
      this.demande.idPeriode=this.idPeriode?.toString() ?? "";
      this.demande.comsCd=this.comsCd??"";
      this.demande.etatFinal=this.etatfinal??"";
      console.log("ITO zanii",this.demande.comsCd);
      console.log("ito id",this.demande.idPeriode);
      console.log("etainal",this.demande.etatFinal);
      
      //this.setSelected(this.demandes.idPeriode?.toString() ?? "", "idPeriode");
      //console.log(this.demande);
    this.ValidationService.update(de,this.demande).subscribe(Response=>{
     console.log(Response);
     this.errorMessage='enregistré';
     setTimeout(() => {
      this.errorStatus = false; // Hide the message by setting errorStatus to false
      this.errorMessage = '';    // Optionally, clear the error message
    }, 3000);

     // this.errorMessage='Demande modifié!';
      });
      // this.d =  this.demande;
   // console.log(this.demande);
    //  this.ValidationService.updatexhr(parseFloat(de),this.demande);
 });
       

   
    }
////exportation excel
exportToExcel() {
  const table = document.getElementById('myTable');
  const data = XLSX.utils.table_to_sheet(table);
  const workbook = XLSX.utils.book_new();
  XLSX.utils.sheet_add_to_book(workbook, data, 'Feuille1');
  const excelBuffer = XLSX.write(workbook, { type: 'buffer' });
  const blob = new Blob([excelBuffer], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' });
  const file = new File([blob], 'data.xlsx');

  //saveAs(file);
}
}

