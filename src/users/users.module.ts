import { Module } from '@nestjs/common';
import {UsersController} from "./users.controller";
import {UsersService} from "./users.service";
import {SequelizeModule} from "@nestjs/sequelize";
import {Users} from "./users";
import {Roles} from "../roles/roles";
import {UsersRoles} from "../roles/usersRoles";

@Module({
    controllers: [UsersController],
    providers: [UsersService],
    imports: [
        SequelizeModule.forFeature([Users, Roles, UsersRoles])
    ]
})
export class UsersModule {}
