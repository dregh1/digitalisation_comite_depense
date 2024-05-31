export class User {
    public id : string;
    public firstName :string;
    public lastName : string ;
    public email: string;
    
    constructor(
      id: string, firstName: string, lastName: string
      ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email= "";
    }

    // User(id: string, firstName: string, lastName: string) {
    //   this.id = id;
    //   this.firstName = firstName;
    //   this.lastName = lastName;
    // }
      
  }