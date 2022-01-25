import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Comment} from '../models/comment';

@Injectable()
export class CommentService {

  constructor (private http:HttpClient) {}

  addComment(comment: Comment) {
    let tokenUrl1 = "http://localhost:8888/rest/comment/add";
    let headers1 = new HttpHeaders({'Content-Type': 'application/json', 'Authorization': 'Bearer '+localStorage.getItem("token")});
    console.log(JSON.stringify(comment));
    return this.http.post(tokenUrl1, JSON.stringify(comment), {headers: headers1});
  }

  getComments() {
    let url = "http://localhost:8888/api/allComments";
    return this.http.get(url);
  }

  deleteComment(comment: Comment) {
    let url = "http://localhost:8888/rest/comment/delete/" + comment.commentId;

    const options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json', 'Authorization': 'Bearer ' + localStorage.getItem("token")
      }),
      body: {
      },
    };

    return this.http.delete(url, options);
  }
}
