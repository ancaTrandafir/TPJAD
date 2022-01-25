import { Photo } from './photo';
import { Role } from './role';
import { Comment } from './comment';

export class User {
  public userId: number;
  public firstName: string;
  public lastName: string;
  public userName: string;
  public password: string;
  public roles: Role[];
  public created: Date;
  public photoList: Photo[];
  public likedPhotoList: Photo[] = [];
  public commentsBy: Comment[];
}
