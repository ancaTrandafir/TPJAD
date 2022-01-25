import {User} from '../models/user';
import {Injectable} from '@angular/core';
import {Photo} from '../models/photo';
import {Role} from '../models/role';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable()
export class UserService {
  users: User[];

  constructor (private http: HttpClient) {}

  getUsers() {
    let tokenUrl = "http://localhost:8888/rest/user/users";
    let headers = new HttpHeaders({'Content-Type': 'application/json', 'Authorization': 'Bearer '+localStorage.getItem("token")});
    return this.http.get(tokenUrl, {headers: headers});
  }

  getUserById(id: string) {
  }

  getUserByName(username: string) {
    let tokenUrl = "http://localhost:8888/rest/user/userName";
    let headers = new HttpHeaders({'Content-Type': 'application/json', 'Authorization': 'Bearer '+localStorage.getItem("token")});
    return this.http.post(tokenUrl, username, {headers: headers});
  }

  updateUser(user: User) {
    let tokenUrl1 = "http://localhost:8888/rest/user/update";
    let headers1 = new HttpHeaders({'Content-Type': 'application/json', 'Authorization': 'Bearer '+localStorage.getItem("token")});
    return this.http.post(tokenUrl1, JSON.stringify(user), {headers: headers1});
  }

  deleteUser(user: User) {
    let tokenUrl = "http://localhost:8888/rest/user/delete";
    const options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json', 'Authorization': 'Bearer ' + localStorage.getItem("token"),
      }),
      body: JSON.stringify(user),
    };
    return this.http.delete(
      tokenUrl, options);
  }

}
