import {User} from './user';

export class Role {
  roleId: number;
  name: string;
  users: User[];
}
