import {Component, Input} from '@angular/core';
import {PhotoService} from '../services/photo.service';
import {UserService} from '../services/user.service';
import {Photo} from '../models/photo';
import {Router} from '@angular/router';


@Component({
  selector: 'my-album',
  templateUrl: './my-album.component.html'
})
export class MyAlbum {

  public photos: Photo[];
  public user: any;
  public selectedPhoto: Photo;

  constructor (private photoService: PhotoService, private router: Router, private userService: UserService) {
    this.userService.getUserByName(localStorage.getItem("currentUserName") || '{}').subscribe(
      user => {
        this.user = JSON.parse(JSON.stringify(user));
        console.log(this.user);
        this.photoService.getPhotosByUser(this.user).subscribe(
          photos => {console.log(this.photos = JSON.parse(JSON.stringify(user)).photoList);
          },
          error => console.log(error)
        );
      },
      error => console.log(error)
    );
  }

  onSelect(photo:Photo) {
    this.selectedPhoto = photo;
    this.router.navigate(['/image-detail', this.selectedPhoto.photoId]);

  }
}
