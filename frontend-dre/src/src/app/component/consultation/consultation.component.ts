import { Component, OnInit } from '@angular/core';
import { Fournisseur } from 'src/app/models/Fournisseur';
import { ConsultationService } from './consultation.service';
import { DetailDemande } from 'src/app/models/DetailDemande';
@Component({
  selector: 'app-consultation',
  templateUrl: './consultation.component.html',
  styleUrls: ['./consultation.component.scss']
})
export class ConsultationComponent implements OnInit {
demande={
  direction:'',
  statut:'',
  SessionCd:'',
  idFournisseur:'',
  fournisseurs:'',
  motif:'',
  datedebut:'',
  datefin:''
} 

allDetails: DetailDemande[] = [];
fournisseurs : Fournisseur[] = [];
  constructor(private consultationService:ConsultationService) 
  {

  }

  ngOnInit(): void {
     // maka ny fournisseur
     this.consultationService.getFournisseur().subscribe(data => {
      this.fournisseurs = data;
    });

    //GET all CONSULTATION (detailDemande)
      // this.consultationService.search("1","ok","on","2024-01-01","2024-08-01","CD-15121551","1").subscribe(data =>
        this.consultationService.search("1","","","","","","").subscribe(data =>
          {
              this.allDetails = data;
          }
        )

  }



  supprimer(){
    this.demande.direction = '';
    this.demande.statut='';
    this.demande.SessionCd='';
    this.demande.idFournisseur='';
    this.demande.motif='';
    this.demande.datedebut='';
    this.demande.datefin='';
    console.log('mety');
  }


  
   

}
