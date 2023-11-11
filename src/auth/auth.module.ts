import { Module } from '@nestjs/common';
import { AuthService } from './auth.service';
import { AuthController } from './auth.controller';
import {UsersModule} from "../users/users.module";
import {JwtModule} from "@nestjs/jwt";
import * as process from "process";
import {JWT_EXPIRES_IN} from "../common/constants";

@Module({
  providers: [AuthService],
  controllers: [AuthController],
  imports: [
      UsersModule,
      JwtModule.register({
          secret: process.env.JWT_SECRET || "Woo-Hoo",
          signOptions: {
              expiresIn: JWT_EXPIRES_IN
          }
      })
  ]
})
export class AuthModule {}
