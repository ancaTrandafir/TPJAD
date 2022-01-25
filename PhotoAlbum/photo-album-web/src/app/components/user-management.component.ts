import {Component,  OnInit} from '@angular/core';
import {CommentService} from '../services/comment.service';
import { UserService } from '../services/user.service';
import { PhotoService } from '../services/photo.service';
import {User} from '../models/user';
import {Comment} from '../models/comment';
import {Router} from '@angular/router';
import { Photo } from '../models/photo';


@Component({
  selector: 'user-management',
  templateUrl: './user-management.component.html'
})
export class UserManagement implements OnInit {
  users: any;
  user = new User();
  comments: any;

  constructor(private userService: UserService, private commentService: CommentService, private photoService: PhotoService,  private router: Router) { }

  ngOnInit(): void {
    this.getUsers();
  }

  getUsers() {
    this.userService.getUsers().subscribe(users => {
      this.users = users;
    })
  }

  deleteUser(user: User) {
    console.log(user.roles);
    if (user.roles.find(role => role.name === "ROLE_ADMIN")) {
      alert("You cannot delete User with ROLE_ADMIN.");
    }
    else {
      if (confirm("Are you sure to delete User " + user.userName + "?")) {
        this.userService.deleteUser(user).subscribe(user => {
          console.log("user deleted");
          this.getUsers();
        },
          error => console.log(error)
        );        
      }     
    }
  }

  getCommentsGiven(user: User) {
    this.user = user;
    this.commentService.getComments().subscribe(data => {
      this.comments = data;
      this.user.commentsBy = this.comments.filter(comm => comm.userName === user.userName)
      console.log(this.comments);
      console.log(user.commentsBy);
    },
      error => console.log(error)
    );   
  }

  deleteComment(comment: Comment) {    
    if (confirm("Are you sure to delete Comment: " + comment.content + "?")) {
        this.commentService.deleteComment(comment).subscribe(comm => {
          console.log("comment deleted");
          this.getCommentsGiven(this.user);
        },
          error => console.log(error)
        );
      }
    }

  deletePhoto(photo: Photo) {
    this.photoService.removePhoto(photo)
      .subscribe(data => {
        console.log(data);
      });
    this.getUsers();
  }
}
