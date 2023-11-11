import {HttpException, HttpStatus, Injectable} from '@nestjs/common';
import {UserDto} from "../users/dto/userDto";
import {UsersService} from "../users/users.service";
import {JwtService} from "@nestjs/jwt";
import {E_USER_ALREADY_EXISTS} from "../common/exceptions";
import * as bcrypt from "bcryptjs";
import {PASSWORD_HASH_SALT} from "../common/constants";
import {Users} from "../users/users";

@Injectable()
export class AuthService {
    constructor(private usersService: UsersService, private jwtService: JwtService) {}

    async login(dto: UserDto) {

    }

    async registration(dto: UserDto) {
        const candidate = await this.usersService.getUserByEmail(dto.email);

        if (candidate) {
            throw new HttpException(E_USER_ALREADY_EXISTS, HttpStatus.BAD_REQUEST)
        }

        const hashOfPassword = await bcrypt.hash(dto.password, PASSWORD_HASH_SALT);

        const user= await this.usersService.create({...dto, password: hashOfPassword});

        return this.generateToken(user);
    }

    private async generateToken(user: Users) {
        const payload = {email: user.email, id: user.id, roles: user.roles}
        return {
            token: this.jwtService.sign(payload)
        };
    }
}
