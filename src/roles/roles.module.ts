import { Module } from '@nestjs/common';
import { RolesService } from './roles.service';
import { RolesController } from './roles.controller';
import {SequelizeModule} from "@nestjs/sequelize";
import {Roles} from "./roles";
import {Users} from "../users/users";
import {UsersRoles} from "./usersRoles";

@Module({
  providers: [RolesService],
  controllers: [RolesController],
  imports: [
      SequelizeModule.forFeature([Roles, Users, UsersRoles])
  ]
})
export class RolesModule {}
