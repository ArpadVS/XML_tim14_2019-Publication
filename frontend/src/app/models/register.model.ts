export class RegisterModel {
    username: string;
    password: string;
    name: string;
    lastName: string;
    email: string;
  
    constructor(username: string, password: string, name: string, lastName: string, email: string) {
      this.username = username;
      this.password = password;
      this.name = name;
      this.lastName = lastName;
      this.email = email;
    }
  }
  