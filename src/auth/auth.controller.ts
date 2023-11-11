import {Body, Controller, Post} from '@nestjs/common';
import {ApiTags} from "@nestjs/swagger";
import {AuthService} from "./auth.service";
import {UserDto} from "../users/dto/userDto";

@ApiTags("Auth")
@Controller('/api/v1/auth')
export class AuthController {
    constructor(private authService: AuthService) {}

    @Post("/login")
    login(@Body() dto: UserDto) {
        return this.authService.login(dto);
    }

    @Post("/registration")
    registration(@Body() dto: UserDto) {
        return this.authService.registration(dto);
    }
}
