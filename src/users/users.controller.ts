import {
  Body,
  Controller,
  Delete,
  Get,
  Headers,
  Post,
  Put,
  UseGuards,
  UsePipes,
  // ValidationPipe,
} from '@nestjs/common';
import {
  ApiBasicAuth,
  ApiBearerAuth,
  ApiOperation,
  ApiResponse,
  ApiTags,
} from '@nestjs/swagger';
import { ValidationPipe } from 'src/pipes/validation.pipe';
import { Roles } from 'src/roles/roles-auth.decorator';
import { RolesGuard } from 'src/roles/roles.guard';
import { AddRoleDto } from './dto/addRole.dto';
import { BanUserDto } from './dto/banUser.dto';
import { CreateUserDto } from './dto/createUser.dto';
import { User } from './users.model';
import { UsersService } from './users.service';

@ApiTags('Users')
@ApiBearerAuth()
// @ApiBasicAuth()
@Controller('api/v1/users')
export class UsersController {
  constructor(private userService: UsersService) {}

  @ApiOperation({ summary: 'Getting All Users' })
  @ApiResponse({ status: 200, type: [User] })
  // @Roles('ADMIN')
  // @UseGuards(RolesGuard)
  @Get('/check')
  check(@Headers() dto: any) {
    return this.userService.checkUser(dto);
  }

  @ApiOperation({ summary: 'User creation' })
  @ApiResponse({ status: 201, type: User })
  // @UsePipes(ValidationPipe)
  @Post()
  create(@Body() userDto: CreateUserDto) {
    return this.userService.createUser(userDto);
  }

  @ApiOperation({ summary: 'Getting All Users' })
  @ApiResponse({ status: 200, type: [User] })
  @Roles('ADMIN')
  @UseGuards(RolesGuard)
  @Get()
  getAll() {
    return this.userService.getAllUsers();
  }

  @ApiOperation({ summary: 'Выдать роль' })
  @ApiResponse({ status: 200 })
  @Roles('ADMIN')
  @UseGuards(RolesGuard)
  @Post('/role')
  addRole(@Body() dto: AddRoleDto) {
    return this.userService.addRole(dto);
  }

  @ApiOperation({ summary: 'Забанить пользователя' })
  @ApiResponse({ status: 200 })
  @Roles('ADMIN')
  @UseGuards(RolesGuard)
  @Post('/ban')
  ban(@Body() dto: BanUserDto) {
    return this.userService.ban(dto);
  }

  @Delete('/:userId')
  dell() {
    return this.userService.dell();
  }

  @Put('/:userId')
  update() {
    return this.userService.update();
  }
}
