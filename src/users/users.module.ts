import { Module } from '@nestjs/common';
import {UsersController} from "./users.controller";
import {UsersService} from "./users.service";
import {SequelizeModule} from "@nestjs/sequelize";
import {Users} from "./users";
import {Roles} from "../roles/roles";
import {UsersRoles} from "../roles/usersRoles";
import {RolesModule} from "../roles/roles.module";

@Module({
    controllers: [UsersController],
    providers: [UsersService],
    imports: [
        SequelizeModule.forFeature([Users, Roles, UsersRoles]),
        RolesModule
    ]
})
export class UsersModule {}
