import {Injectable} from "@nestjs/common";
import {Users} from "./users";
import {InjectModel} from "@nestjs/sequelize";
import {UserDto} from "./dto/userDto";

@Injectable()
export class UsersService {
    constructor(@InjectModel(Users) private usersRepository: typeof Users) {
    }

    async getUsers() {
        return [{id: 1, name: "Sergey", email: "a@a.a", password: 'aaa'}];
    }

    async addUser(userDto: UserDto) {
        console.log(userDto)
        return ["addUser"];
    }

    dellUser() {
        console.log('remove');
        return ["remove"];
    }

    updateUser() {
        console.log('update');
        return ["update"];
    }
}