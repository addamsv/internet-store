import {Module} from "@nestjs/common";
import { ConfigModule } from '@nestjs/config';
import {SequelizeModule} from "@nestjs/sequelize";
import { UsersModule } from './users/users.module';
import * as process from "process";
import {Users} from "./users/users";
import { RolesModule } from './roles/roles.module';
import {Roles} from "./roles/roles";
import {UsersRoles} from "./roles/usersRoles";
import { AuthModule } from './auth/auth.module';

@Module({
    controllers: [],
    providers: [],
    imports: [
        ConfigModule.forRoot({
            envFilePath: `.${process.env.NODE_ENV}.env`
        }),
        SequelizeModule.forRoot({
                dialect: 'postgres',
                host: process.env.POSTGRES_HOST,
                port: Number(process.env.POSTGRES_PORT),
                username: process.env.POSTGRES_USER,
                password: process.env.POSTGRES_PASSWORD,
                database: process.env.POSTGRES_DB,
                models: [Users, Roles, UsersRoles],
                autoLoadModels: true
            }
        ),
        UsersModule,
        RolesModule,
        AuthModule
    ]
})
export class AppModule {}