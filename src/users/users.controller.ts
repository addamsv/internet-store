import {Body, Controller, Delete, Get, Post, Put} from "@nestjs/common";
import {UsersService} from "./users.service";
import {UserDto} from "./dto/userDto";
import {ApiOperation, ApiResponse, ApiTags} from "@nestjs/swagger";
import {Users} from "./users";


@ApiTags("Users")
@Controller('/api/v1/users')
export class UsersController {
    constructor(private usersService: UsersService) {}

    @ApiOperation({summary: "Get all users", description: ""})
    @ApiResponse({status: 200, type: [Users]})
    @Get()
    get() {
        return this.usersService.get();
    }

    @ApiOperation({summary: "Create user"})
    @ApiResponse({status: 201, type: Users})
    @Post()
    create(@Body() userDto: UserDto) {
        return this.usersService.create(userDto);
    }

    @Delete('/:userId')
    dell() {
        return  this.usersService.dell();
    }

    @Put('/:userId')
    update() {
        return this.usersService.update();
    }
}