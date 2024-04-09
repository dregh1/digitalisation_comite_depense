import { Component, OnInit } from '@angular/core';
import { Demande } from 'src/app/models/Demande';
import { AffichageService } from './affichage.service';
import { HttpClient } from '@angular/common/http';
import { Periode } from 'src/app/models/Periode';
import { Rubrique } from 'src/app/models/Rubrique';
import { Fournisseur } from 'src/app/models/Fournisseur';
import { Sousrubrique } from 'src/app/models/Sousrubrique';
import { Brouillon } from 'src/app/models/Brouillon';
import { Active_dmd } from 'src/app/models/Active_dmd';
import { ActivatedRoute } from '@angular/router';
import { Prescripteur1Service } from '../prescripteur/prescripteur1.service';
@Component({
  selector: 'app-affichage-prescri',
  templateUrl: './affichage-prescri.component.html',
  styleUrls: ['./affichage-prescri.component.scss']
})
export class AffichagePrescriComponent implements OnInit {
 // donnee PRESCRIPTEUR
 periodes: Periode[]=[];
 fournisseurs : Fournisseur[] = [];
 //titres : Titre[] = [];
 brouillons : Brouillon [] = [];
 rubriques: Rubrique [] = [];

 active_dmds : Brouillon [] = [];
 titresBr : any [] = [];
 titresAct : any [] = [];
 selectedTitleBr: string | undefined;
 selectedTitleAct: string | undefined;
 devises : any [] =  [];
 refences : any []= [];
designation:string='';
texte:string='';
 // valeur
 periode:any;
 isregularisation : boolean;  
 id_session : any = 3351;
 id_titre_depense : any =  1;
 motif : any;
 montant_ht : any;

 brouillon = new Brouillon();
  demandes=new Demande();
  role : string | null ;

  id:number; type:string='';devise:string='';

 demande
 ={
  is_valdby_pres:false,
  is_regularisation    :'',
  type_reference : '',
  id_rubrique:'',
  sousrubrique : '',
  motif               : '',
  type_devise : '',
  coms_prescripteur :'',

  id_titre_depense    : '',
  reference : '',

  id_fournisseur      :'',
  montant_ht          :'',

  id_periode          : '',
    titre:'',
    id_direction:'',
    id_titre:'',
    fournisseur:'',
    periode:'',
    nom_reference:''
  } 

titre_depense =
{
  designation :'',
}
item: Demande | null = null;
  errorMessage: string | null = null;
  activatedRoute: any;


  constructor(private AffichageService:  AffichageService,private route: ActivatedRoute,private prescriptService : Prescripteur1Service) { 
    this.isregularisation = false;
    this.role  = sessionStorage.getItem("role");
    this.id = this.activatedRoute.snapshot.params['id'];
  }
  

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id')); // Access ID from route
    // this.apiService.getItemById(id).subscribe({
    //   next: (data) => (this.item = data),
    //   error: (error) => (this.errorMessage = error.message)
    // });
   

    //maka ny active_dmd
    // ny active_demande
    this.prescriptService.getActiveDmdbyId(this.id).subscribe(response=> {
        this.brouillon = response;
        this.demande.is_regularisation = this.brouillon.is_regularisation?.toString() ?? "";
        this.demande.titre = this.brouillon.titre ?? "";
        
        this.demande.type_reference = this.brouillon.type_reference ?? "";
        this.type=this.demande.type_reference;
        this.demande.nom_reference = this.brouillon.reference ?? "";
        this.demande.motif = this.brouillon.motif ?? "";
        this.demande.type_devise = this.brouillon.devise ?? "";
        this.devise=this.demande.type_devise ;
        this.demande.coms_prescripteur = this.brouillon.coms_prescripteur ?? "";
        this.demande.fournisseur = this.brouillon.fournisseur ?? "";
        this.demande.montant_ht = this.brouillon.montant_ht?.toString() ?? "";
        this.demande.periode = this.brouillon.periode ?? ""; 
        this.demande.id_periode = this.brouillon.id_periode?.toString() ?? ""; 
        this.demande.id_direction = this.brouillon.id_direction?.toString() ?? ""; 
        this.demande.id_fournisseur = this.brouillon.id_fournisseur?.toString() ?? ""; 
        this.demande.id_titre_depense = this.brouillon.id_titre?.toString() ?? ""; 
        this.setSelected(this.demande.id_titre_depense);
    });
   
  }
  setSelected(id_titre_depense: string) {
    throw new Error('Method not implemented.');
  }
   //Affichage demande
  // this.AffichageService.getDemandeById(this.DemandeId).subscribe(demande =>{
  //   this.rubrique = demande;
  //   });
    // maka ny periode
  
}
