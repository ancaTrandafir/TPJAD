import {Component, Input} from '@angular/core';
import {NavBar} from './nav-bar.component';
import {Photo} from '../models/photo';
import {PhotoService} from '../services/photo.service';
import {ImageComments} from './image-comments.component';
import {UserService} from '../services/user.service';
import {User} from '../models/user';
import { ActivatedRoute, Params, Router } from '@angular/router';

@Component({
  selector: 'image-detail',
  templateUrl: './image-detail.component.html'
})
export class ImageDetail {
  @Input('photo')photo: Photo;
  @Input('user')  user: User;
  like: string;
  //user: User;
  photoId: number;
  sameUser: boolean;

  constructor ( private photoService: PhotoService, private userService: UserService, private route: ActivatedRoute, private router:Router){
  }

  goBack() {
    window.history.back();
  }

  ngOnInit() {

       this.route.params.forEach((params: Params) => {
           this.photoId = Number.parseInt(params['id']);
          });

      this.photoService.getPhotoById(this.photoId).subscribe(
        photo => {
          this.photo = JSON.parse(JSON.stringify(photo));
          this.userService.getUserByName(localStorage.getItem("currentUserName") || '{}').subscribe(
            user => {
              this.user = JSON.parse(JSON.stringify(user));
              if (this.photo.user.userName === localStorage.getItem("currentUserName"))
                 this.sameUser = true;
              else this.sameUser = false;

              if (this.user.likedPhotoList.filter(photo => photo.photoId == this.photo.photoId)[0]) {
                this.like="Unlike";
              } else {
                this.like="Like";
              }
            },
            error => console.log(error)
          )
        },
        error => console.log(error)
      );
  }

  likeDisplay() {
    if (this.like =="Like") {
      this.like="Unlike";
      this.user.likedPhotoList.push(this.photo);
      this.photo.likes+=1;
      this.userService.updateUser(this.user).subscribe();
      this.photoService.updatePhoto(this.photo).subscribe();
    } else {
      this.like="Like";
      for (let i=0; i<this.user.likedPhotoList.length; i++) {
        if (this.user.likedPhotoList[i].photoId == this.photo.photoId) {
            this.user.likedPhotoList.splice(i, 1);
        }
      }
      this.photo.likes-=1;
      this.userService.updateUser(this.user).subscribe();
      this.photoService.updatePhoto(this.photo).subscribe();
    }
  }

  removePhoto() {
      this.photoService.removePhoto(this.photo)
      .subscribe(data => {
         console.log(data);
      });
      this.reload();
    }

    reload(){
        this.router.navigate(["/my-album"]);
    }

}
