import {Body, Controller, Get, Post} from "@nestjs/common";
import {UsersService} from "./users.service";
import {UserDto} from "./dto/userDto";



@Controller('/api/v1')
export class UsersController {

    constructor(private usersService: UsersService) {}

    @Get('/users')
    get() {
        return this.usersService.getUsers();
    }

    @Post('/users')
    create(@Body() userDto: UserDto) {
        return this.usersService.addUser(userDto);
    }
}