export class SessionCd {
    
  constructor(
    public id?:number,
      public ref? : string,
      public dateCloture? : Date ,
      public idDirection? : Number,
      public tauxEur? : Number,
      public tauxUsd? : Number,
      public tauxGbp?  : Number,
      public tauxMga?  : Number,

  ) {}
}