import {Body, Controller, Delete, Get, Post, Put} from "@nestjs/common";
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

    @Delete('/users')
    dell() {
        return  this.usersService.dellUser();
    }

    @Put('/users')
    update() {
        return this.usersService.updateUser();
    }
}