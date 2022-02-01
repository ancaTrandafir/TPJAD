import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {User} from '../models/User';


@Injectable()
export class RegisterService {
  constructor (private http: HttpClient) {}

  sendUser(user:User) {
    let url = "http://localhost:8888/api/register/";
    let headers1 = new HttpHeaders({'Content-Type': 'application/json', 'Access-Control-Allow-Origin': 'http://localhost:8888', 'Access-Control-Allow-Methods': 'GET, OPTIONS, POST, PUT', 'Access-Control-Allow-Headers': 'X-Token'});
    console.log(user.firstName);
    return this.http.post(url, JSON.stringify(user), {headers: headers1});
  }
}
