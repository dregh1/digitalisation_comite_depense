import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../Authentication/authentication.service';
import { Direction } from 'src/app/models/Direction';
import { ValidationService } from './validation.service';
import { Periode } from 'src/app/models/Periode';
import { Demande } from 'src/app/models/Demande';
import { DetailDemande } from 'src/app/models/DetailDemande';
import { Fournisseur } from 'src/app/models/Fournisseur';
import { DonneeExcel } from 'src/app/models/DonneExcel';
import * as XLSX from 'xlsx';
import { UtilitaireService } from 'src/app/service/utilitaire.service';
import { SessionCd } from 'src/app/models/SessionCd';

// import { DetailDemandeParTitre } from 'src/app/models/DetailParTitre';
// import {Item}from 'src/app/models/Item';
@Component({
  selector: 'app-validation',
  templateUrl: './validation.component.html',
  styleUrls: ['./validation.component.scss'],
})
export class ValidationComponent implements OnInit {
  [x: string]: any;
    
  // VALEURS PAR DEFAUT

          isUp1 = false; // Initial state for first button
          isUp2 = false; 
          errorStatus = false;
          role: string | null = '';
          token: string | null = '';
          DetailDemande: DetailDemande[] = [];
          direction = new Direction();
          nomDirection: string | null = '';
          fournisseurs: Fournisseur[] = [];
          periodes: Periode[] = [];
          demandes = new Demande();
          donnee = new Demande();
          texte: String = '';
          d = new Demande();
          DonneExcels: DonneeExcel[] = [];

          demande = {
            id: '',
            estRegularisation: false,
            periode: '',
            idRubrique: '',
            sousRubrique: '',
            motif: '',
            devise: '',
            typeDevise: '',
            comsPrescripteur: '',
            idDirection: '',
            idTitreDepense: '',
            nomReference: '',
            titre: '',
            idFournisseur: '',
            montantHt: '',
            fournisseur: '',
            idPeriode: '',
            validationPrescripteur: false,
            validationAchat: false,
            validationCdg: false,
            typeReference: '',
            idDemande: '',
            idperiode: '',
            comsCd: '',
            etatFinal: '',
          };
          detail = {
            comsCd: '',
            idperiode: '',
          };
          errorMessage: string = '';
          // toggleUp() {
          //   this.isUp1 = !this.isUp1;
          // }
          toggleUp(groupTitleKey: string): void {
            const collapseElement = document.getElementById(`collapseExample-${groupTitleKey}`);
            if (collapseElement) {
              collapseElement.classList.toggle('show');
            }
          }
         
          comsCd: string | null = '';
          idPeriode: string | null = '';
          etatfinal: string | null = '';
          detaildemande = new DetailDemande();

          listeDirections :  Direction [] =[];             /* liste direction  */
          listeSessions :  SessionCd [] =[];               /* liste sesssion  */
          
    //parametrage du filtre
          filtre_Session : string ='';
          filtre_idDirection : string ='';
          session=new SessionCd();
          idsession:string ='';
          montantSeuil : string ='5000000';

    //session active
          sessionActive  =  new SessionCd ()  ;
          dateClotureSession!: Date;
          //groupedDemandes: { [key: string]: DetailDemande[] } = {};
          
           groupedDetailDemandes: { [titre: string]: DetailDemande[] } = {};
  constructor(
      private authenticationService: AuthenticationService,
      private ValidationService: ValidationService,
      private utilitaires: UtilitaireService
  ) { 
   
   }

  ngOnInit(): void {
    // this.groupedDetailDemandes.sort((a, b) => {
    //   if (a.key === 'sans titre') {
    //     return 1; // Move groups with 'sans titre' key to the end
    //   } else if (b.key === 'sans titre') {
    //     return -1; // Move groups with 'sans titre' key to the end
    //   }
    //   return a.key.localeCompare(b.key); // Default alphabetical sorting for other keys
    // });
  
  // initialisation des données par defaut
        this.token = sessionStorage.getItem('token');
        if(this.token !== null )
          {
            /*  ajout nom direction dans la sessionStorage */
              this.authenticationService.getUserInformation().subscribe(response =>
                {

                    /* recuperation de l'id direction */
                    this.nomDirection = this.authenticationService.getDirection(response['direction'])  ;
                    if(this.nomDirection !== null)
                      {
                        
                              this.authenticationService.getDirectionByName(this.nomDirection).subscribe(response =>{
                                this.direction = response;
                                  this.direction.id = response.id;  
                                  console.log('blaoohi',response);
                  
                                        
                        //RECUPERATION id session


                                      this.utilitaires.getSessionByDirection(this.direction.id?.toString() ??'' ).subscribe((data) => {
                                        if(data !== null)
                                        {
                                              console.log("------------ session ------------");
                                              console.log(data);
                                              
                                              this.session = data;
                                              this.idsession = data.id?.toString() ?? '';
                                            console.log(this.idsession,'sessionnnnnnnnnnnnnnnnnn');
                                          
                                
                                              /* OBTENIR detaildemande getFiltreDetailDemande */
                                              // this.ValidationService.getBrouillon().subscribe((DetailDemande) => {
                                                this.ValidationService.getFiltreDetailDemande(this.direction.id?.toString()?? '',this.montantSeuil?? '')
                                                .subscribe((filtreDetailDemande) => {
                                                  console.log(this.direction.id?.toString()?? '',this.idsession + '-----------------');
                                                  
                                                  this.DetailDemande = filtreDetailDemande;
                                                  
                                                  console.log(this.DetailDemande,'retookkggg');
                                                  
                                                   this.groupedDetailDemandes = filtreDetailDemande.reduce((acc, item) => {
                                                    if (item.titre) {
                                                      if (!acc[item.titre]) {
                                                        acc[item.titre] = [];
                                                      }
                                                      acc[item.titre].push(item);
                                                    }
                                                    return acc;
                                                  }, {} as { [titre: string]: DetailDemande[] });
                                              
                                                  // Now you have groupedDetailDemandes which is an object with titles as keys and arrays of DetailDemande objects as values
                                                  console.log(this.groupedDetailDemandes,'regroupenet detadeùande'); // Output: { "Titre 1": [...], "Titre 2": [...] } (depending on your data)
                                                  
                                                  //recuperation par detail
                                                  // console.log("grr");
                                                  
                                                  // console.log(DetailDemande);
                                                  
                                                }) ;    
                                              }       
                                });
                              // }
                    });
                  }
                });
          }
      

        /* recuperation de tous les direction */
        this.utilitaires.getDirection().subscribe(  ( result ) => {this.listeDirections =  result;} );
        
        /* recuperation de tous les session */
        this.utilitaires.getSession().subscribe(  ( result ) => {this.listeSessions =  result;} );
   
        // recuperation ny periode
        this.ValidationService.getPeriode().subscribe((data) => {
          this.periodes = data;
        });


        // setTimeout(() => {
        //   this.setSelected('13', 'idPeriode');
        // }, 1000);

   // this.groupedDemandes = this.groupByTitle(this.DetailDemande);
        
      }
      // regroupement par titre des demandes 
      groupByTitle(demandes: DetailDemande[]): { [key: string]: DetailDemande[] } {
        const grouped: { [key: string]: DetailDemande[] } = {};
        demandes.forEach(demande => {
          const titre = demande.titre;
          if (typeof titre === 'string') { // Vérification de type
            if (!grouped[titre]) {
              grouped[titre] = [];
            }
            grouped[titre].push(demande);
          } else {
            // Gérer le cas où titre n'est pas une chaîne de caractères
          }
        });
        return grouped;
      }
  getid(idperiode: any) {}
  // sortGroupsByKey(data: groupedDetailDemandes): groupedDetailDemandes {
  //   const keyToFind = 'sans titre'; // Replace with your actual key
  
  //   const filteredIndex = data.findIndex(group => group.key === keyToFind);
  
  //   if (filteredIndex !== -1) {
  //     const matchedGroup = data.splice(filteredIndex, 1)[0];
  //     data.push(matchedGroup);
  //   }
  
  //   return data;
  // }
  //set SELECTED OPTION
  setSelected(id: string, idHtml: string) {
    console.log('ato!');

    const selectelement = document.getElementById(idHtml);
    if (selectelement !== null) {
      console.log('Ito!');

      const lesOptions = selectelement?.querySelectorAll('option');
      for (let i = 0; i < lesOptions.length; i++) {
        const unOption = lesOptions[i];
        if (unOption.value === id) {
          console.log('TEHAKA', id);
          unOption.selected = true;
          // this.demande.idTitreDepense = unOption.value;
        }
      }
    }
  }
  onChampModifie(
    index: any,
    nouvelleValeur: string | undefined,
    idPeriode: any,
    de: any,
    etatFinal: any
  ) {
    setTimeout(() => {
      // Optionally, clear the error message

      this.DetailDemande[index].comsCd = nouvelleValeur;
      this.comsCd = nouvelleValeur ?? '';
      this.idPeriode = idPeriode;
      this.etatfinal = etatFinal;
      this.enregistrer(de);
      //console.log("la valeur ",nouvelleValeur);
      this.enregistrer(de);
    }, 3000);
  }
  ///modication demande
  enregistrer(de: any) {
    //recuperation par detail
    this.ValidationService.getdemande(de).subscribe((response) => {
      this.demandes = response;
      //console.log(response,"////////////////");
      this.demande.estRegularisation = Boolean(
        this.demandes.estregularisation ?? ''
      );
      this.demande.idTitreDepense =
        this.demandes.idTitreDepense?.toString() ?? '';
      this.demande.typeReference = this.demandes.typereference ?? '';
      // this.demande.typereference=this.demande.typereference;
      this.demande.nomReference = this.demandes.nomReference ?? '';
      this.demande.motif = this.demandes.motif ?? '';
      this.demande.typeDevise = this.demandes.typeDevise ?? '';
      this.demande.comsPrescripteur = this.demandes.comsprescripteur ?? '';
      this.demande.idFournisseur =
        //this.demandes.idfournisseur?.toString() ?? '';
      this.demande.montantHt = this.demandes.montantht?.toString() ?? '';
      this.demande.idDirection = this.demandes.iddirection?.toString() ?? '';
      this.demande.sousRubrique = this.demandes.sousRubrique?.toString() ?? '';
      this.demande.idRubrique = this.demandes.idrubrique?.toString() ?? '';
      this.demande.validationPrescripteur = Boolean(
        this.demandes.validationprescripteur ?? ''
      );
      this.demande.validationCdg = Boolean(this.demandes.validationCdg ?? '');
      this.demande.validationAchat = Boolean(
        this.demandes.validationAchat ?? ''
      );
      this.demande.idPeriode = this.idPeriode?.toString() ?? '';
      this.demande.comsCd = this.comsCd ?? '';
      this.demande.etatFinal = this.etatfinal ?? '';
      console.log('ITO zanii', this.demande.comsCd);
      console.log('ito id', this.demande.idPeriode);
      console.log('etainal', this.demande.etatFinal);

      //this.setSelected(this.demandes.idPeriode?.toString() ?? "", "idPeriode");
      //console.log(this.demande);
      this.ValidationService.update(de, this.demande).subscribe((Response) => {
        console.log(Response);
        this.errorMessage = 'enregistré';
        setTimeout(() => {
          this.errorStatus = false; // Hide the message by setting errorStatus to false
          this.errorMessage = ''; // Optionally, clear the error message
        }, 3000);

        // this.errorMessage='Demande modifié!';
      });
      // this.d =  this.demande;
      // console.log(this.demande);
      //  this.ValidationService.updatexhr(parseFloat(de),this.demande);
    });
  }
  //exporter excel
  //  exportToExcel(): void {
  //   //aectation données eccdel
  //   for (const detail of this.DetailDemande) {
  //     this.DonneExcels.push({
  //       Typereference: detail.typereference,
  //       Motif:detail.motif,
  //       Fournisseur:detail.fournisseur,
  //       Devise:detail.devise,
  //       MontantHt:detail.montantht,
  //       Commentaireprescripteur:detail.comsprescripteur,
  //       Periode:detail.periode,
  //       Regularisation:detail.estregularisation,
  //       commentaireCdg:detail.comsCdg,
  //       commentaireAchat:detail.comsAchat,
  //       Decision:detail.etatFinal,
  //       commentaireCd:detail.comsCd,
  //       MontantMga:detail.montantMga
  //     });
  //   }
  //   this.ValidationService.exportToExcel(this.DonneExcels, 'MyData.xlsx');
  // }


  //FILTRATION ligne de validation
  filtreValidation(){
    
    this.ValidationService.getFiltreDetailDemande(this.filtre_idDirection,this.montantSeuil).subscribe((resultatFiltre) => {
      this.DetailDemande = resultatFiltre;

      this.groupedDetailDemandes = resultatFiltre.reduce((acc, item) => {
        if (item.titre) {
          if (!acc[item.titre]) {
            acc[item.titre] = [];
          }
          acc[item.titre].push(item);
        }
        return acc;
      }, {} as { [titre: string]: DetailDemande[] });
    });
  }
  actualiser() {
    this.filtre_idDirection = '';
    this.montantSeuil = '5000000';
    this.filtreValidation();
  }
  //get de la session active
  getActiveSession()  {

    this.utilitaires.getSessionByDirection(this.direction.id?.toString() ?? '')
    .subscribe(
      (result) =>{
       console.log(result);
        this.sessionActive = result;
      });
  }
  updateSession(){
    if(this.sessionActive.id !== undefined)
    { 
      this.utilitaires.updateSession(this.sessionActive.id ,this.sessionActive )  
      .subscribe(
        response => {
          console.log('Mise à jour réussie:', response);
        },
        error => {
          console.error('Erreur lors de la mise à jour:', error);
        }
      );   
  }
  }
}