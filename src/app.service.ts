import {Injectable} from "@nestjs/common";

@Injectable()
export class AppService {
    getUsers() {
        return [{id: 1, name: "Sergey", email: "a@a.a"}];
    }
}