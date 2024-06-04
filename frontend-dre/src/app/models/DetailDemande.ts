export class DetailDemande {
  constructor(
    public id?: number,
    public idtitre?: number,
    public titre?: string,
    public motif?: string,
    public montantht?: string,
    public typereference?: string,
    public reference?: string,
    public nomReference?: string,
    public estregularisation?: boolean,
    public iddirection?: number,
    // public direction?: string,
    public comsprescripteur?: string,
    public idrubrique?: number,
    public fournisseur?: string,
    public nomRubrique?: string,
    public sousrubrique?: string,
    public idperiode?: number,
    public periode?: string,
    public devise?: string,
    public depense?: string,
    public validationprescripteur?: boolean,
    public validationcdg?: boolean,
    public validationachat?: boolean,
    public idfournisseur?: number,
    public comsAchat?: string,
    public comsCdg?: string,
    public comsCd?: string,
    public etatFinal?: string,
    public montantMga?: string,
    public idSession?: string,
    public estRefuseCdg?:boolean,
    public estRefuseAchat?:boolean,
    public estsoumis?:boolean,
    public identifiant?: string,
    public dateCreation?: Date,
    public dateSoumission?: Date,
  ) {}
}
