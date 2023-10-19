import {Injectable} from "@nestjs/common";

@Injectable()
export class UsersService {
    getUsers() {
        return [{id: 1, name: "Sergey", email: "a@a.a"}];
    }
}