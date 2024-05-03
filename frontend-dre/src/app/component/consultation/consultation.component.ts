import { Component, OnInit } from '@angular/core';
import { Fournisseur } from 'src/app/models/Fournisseur';
import { ConsultationService } from './consultation.service';
import { DetailDemande } from 'src/app/models/DetailDemande';
import { MonFournisseur } from 'src/app/models/MonFournisseur';
import { Observable } from 'rxjs';
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
  data$: Observable<MonFournisseur[]> | undefined;

allDetails: DetailDemande[] = [];
fournisseurs : Fournisseur[] = [];


title = 'angular-excel-export';

data = [
  { name: 'John Doe', age: 30, city: 'New York' },
  { name: 'Jane Doe', age: 25, city: 'Los Angeles' }
];



  constructor(private consultationService:ConsultationService) 
  {

  }


  exportToExcel(): void {
    this.consultationService.exportToExcel(this.allDetails, 'MyData.xlsx');
  }


  ngOnInit(): void {
    this.consultationService.getMonFournisseur();

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
