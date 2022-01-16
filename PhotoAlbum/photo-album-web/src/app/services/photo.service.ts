import {Injectable} from '@angular/core';
import {Photo} from '../models/photo';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {User} from '../models/user';

@Injectable()
export class PhotoService {

  constructor (private http:HttpClient){}

  getPhotos() {
    let url = "http://localhost:8888/photo/allPhotos";
    return this.http.get(url);
  }

  removePhoto(photo: Photo) {
    let url = "http://localhost:8888/rest/photo/delete/" + photo.photoId;

    const options = {
      headers: new HttpHeaders({
      'Content-Type': 'application/json', 'Authorization': 'Bearer '+localStorage.getItem("token")
      }),
      body: {
        //photo: photo.photoId,
      },
    };

    return this.http.delete(url, options);
  }

  getPhotoById (photoId: number) {
    let tokenUrl1 = "http://localhost:8888/rest/photo/photoId";
    let headers1 = new HttpHeaders({'Content-Type': 'application/json', 'Authorization': 'Bearer '+localStorage.getItem("token")});
    return this.http.post(tokenUrl1, JSON.stringify(photoId), {headers: headers1});
  }

  getPhotosByUser (user: User) {
    let tokenUrl1 = "http://localhost:8888/rest/photo/user";
    let headers1 = new HttpHeaders({'Content-Type': 'application/json', 'Authorization': 'Bearer '+localStorage.getItem("token")});
    return this.http.post(tokenUrl1, JSON.stringify(user), {headers: headers1});
  }

  updatePhoto(photo: Photo) {
    let tokenUrl1 = "http://localhost:8888/rest/photo/update";
    let headers1 = new HttpHeaders({'Content-Type': 'application/json', 'Authorization': 'Bearer '+localStorage.getItem("token")});
    return this.http.post(tokenUrl1, JSON.stringify(photo), {headers: headers1});
  }

}
