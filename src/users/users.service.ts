import {Injectable} from "@nestjs/common";
import {Users} from "./users";
import {InjectModel} from "@nestjs/sequelize";
import {UserDto} from "./dto/userDto";
import {RolesService} from "../roles/roles.service";

@Injectable()
export class UsersService {
    constructor(@InjectModel(Users) private usersRepository: typeof Users,
                private rolesService: RolesService) {
    }

    async get() {
        return await this.usersRepository.findAll({include: {all: true}});
        // return [{id: 1, email: "a@a.a", password: 'aaa'}];
    }

    async getUserByEmail(email: string) {
        return await this.usersRepository.findOne({where: {email}, include: {all: true}});
    }

    async create(userDto: UserDto) {
        const user = await this.usersRepository.create(userDto);
        /* role "USER" should be in DB */
        const role = await this.rolesService.getByValue("USER");
        await user.$set('roles', [role.id]);
        user.roles = [role];
        return user;
    }

    async dell() {
        console.log('remove');
        return ["remove"];
    }

    async update() {
        console.log('update');
        return ["update"];
    }
}