import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable}     from 'rxjs';

@Injectable()
export class LoginService {
  token: string;

  constructor (private http: HttpClient) {}

  sendCredential(model:any) {
    console.log("model "+ model.username);
    let tokenUrl1 = "http://localhost:8888/api/login";
    let headers1 = new HttpHeaders({'Content-Type': 'application/json'}), responseType: 'text';
    return this.http.post(tokenUrl1, JSON.stringify(model), {
       headers: headers1, responseType: 'text',
       observe: 'response' as 'body'
    });
  }


  getToken(token:any) {
    let tokenUrl2 = "http://localhost:8888/rest/user/users";
    console.log('Bearer '+token);
    let getHeaders = new HttpHeaders({'Content-Type': 'application/json', 'Access-Control-Allow-Origin': 'http://localhost:8080', 'Access-Control-Allow-Methods': 'GET, OPTIONS, POST, PUT', 'Access-Control-Allow-Headers': 'X-Token', 'Authorization':'Bearer '+token}), responseType:'text' ;
    return this.http.get(tokenUrl2, {headers: getHeaders})
  }

  logout() {
    localStorage.setItem("token","");
    localStorage.setItem("currentUserName", '');
    alert("You just logged out.");
  }

  checkLogin() {
    if (localStorage.getItem("currentUserName")!=null && localStorage.getItem("currentUserName")!='' && localStorage.getItem("token")!=null && localStorage.getItem("token")!='') {
      return true;
    } else {
      return false;
    }
  }

}
