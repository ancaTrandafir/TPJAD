import {Component, OnInit} from '@angular/core';
import {LoginService} from '../services/login.service';
import {UserService} from '../services/user.service';
import {User} from '../models/user';

@Component({
  selector: 'nav-bar',
  templateUrl:'./nav-bar.component.html'
})
export class NavBar implements OnInit{
  user: User;
  adminLoggedIn: boolean;
  myLocalStorage;

  constructor (public loginService:LoginService, public userService:UserService) {
    this.myLocalStorage=localStorage;
  }

  ngOnInit(){
    this.checkAdmin();
  }

  onClick() {
    if (this.loginService.checkLogin()) {
      this.loginService.logout();
    }
  }

  checkAdmin() {
  this.userService.getUserByName(localStorage.getItem("currentUserName")|| '{}').subscribe(
        user => {
          this.user = JSON.parse(JSON.stringify(user));
          if (this.user.roles.find(x => x.name === "ROLE_ADMIN"))
            this.adminLoggedIn = true;
          else this.adminLoggedIn = false;
        },
        error => console.log(error)
      );
  return this.adminLoggedIn;
  }
}
