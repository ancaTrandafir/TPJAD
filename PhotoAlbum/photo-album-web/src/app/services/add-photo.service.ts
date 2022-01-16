import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Photo} from '../models/Photo';
import {Observable}     from 'rxjs';


@Injectable()
export class AddPhotoService {
  constructor (private http: HttpClient) {}

  sendPhoto(photo:Photo) {
    let url = "http://localhost:8888/rest/photo/add";
    let headers1 = new HttpHeaders({'Content-Type': 'application/json', 'Authorization':'Bearer '+localStorage.getItem("token")});
    console.log(url);
    return this.http.post(url, JSON.stringify(photo), {headers: headers1});
  }
}
