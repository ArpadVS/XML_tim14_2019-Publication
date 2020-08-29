export class RegisterModel {
    username: string;
    password: string;
    // tslint:disable-next-line: variable-name
    first_name: string;
    // tslint:disable-next-line: variable-name
    last_name: string;
    email: string;
    role: number;
  
    constructor(username: string, password: string, name: string, lastName: string, email: string, role: number) {
      this.username = username;
      this.password = password;
      this.first_name = name;
      this.last_name = lastName;
      this.email = email;
      this.role = role;
    }
  }
  