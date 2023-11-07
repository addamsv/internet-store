import {Injectable} from "@nestjs/common";
import {Users} from "./users";
import {InjectModel} from "@nestjs/sequelize";
import {UserDto} from "./dto/userDto";

@Injectable()
export class UsersService {
    constructor(@InjectModel(Users) private usersRepository: typeof Users) {
    }

    async getUsers() {
        return await this.usersRepository.findAll();
        // return [{id: 1, email: "a@a.a", password: 'aaa'}];
    }

    async addUser(userDto: UserDto) {
        console.log(userDto)
        return await this.usersRepository.create(userDto);
    }

    async dellUser() {
        console.log('remove');
        return ["remove"];
    }

    async updateUser() {
        console.log('update');
        return ["update"];
    }
}