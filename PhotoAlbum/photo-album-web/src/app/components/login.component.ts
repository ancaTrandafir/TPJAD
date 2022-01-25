import {Component} from '@angular/core';
import {Observable}  from 'rxjs';
import {LoginService} from '../services/login.service';

@Component({
  selector: 'login',
  templateUrl: './login.component.html'
})
export class Login {
  public model = {'username':'', 'password':''};
  public currentUserName;

  constructor (public loginService: LoginService){
    this.currentUserName=localStorage.getItem("currentUserName") || '{}';
  }

  onSubmit() {
    this.loginService.sendCredential(this.model).subscribe(
      data => {
                localStorage.setItem("token", (JSON.stringify(data['body'])).replace('"', '').replace('"', ''));
                console.log("tokn " +localStorage.getItem("token"));
                this.loginService.getToken(localStorage.getItem("token")).subscribe(
                  data => {
                            this.currentUserName=this.model.username;
                            localStorage.setItem("currentUserName", this.model.username);
                            this.model.username='';
                            this.model.password='';
                          },
                  error => {
                    alert("Invalid login. Please check your name and password.");
                    console.log(error);
                  });
              },
      error => console.log(error)
    );

  }


}
