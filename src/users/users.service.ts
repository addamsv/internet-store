import {
  HttpException,
  HttpStatus,
  Injectable,
  UnauthorizedException,
} from '@nestjs/common';
import { JwtService } from '@nestjs/jwt';
import { InjectModel } from '@nestjs/sequelize';
import { RolesService } from 'src/roles/roles.service';
import { AddRoleDto } from './dto/addRole.dto';
import { BanUserDto } from './dto/banUser.dto';
import { CreateUserDto } from './dto/createUser.dto';
import { User } from './users.model';

@Injectable()
export class UsersService {
  constructor(
    @InjectModel(User)
    private userRepository: typeof User,
    private roleService: RolesService,
    private jwtService: JwtService,
  ) {}

  async createUser(dto: CreateUserDto) {
    try {
      const user = await this.userRepository.create(dto);

      // const role = await this.roleService.getRoleByValue('ADMIN');
      const role = await this.roleService.getRoleByValue('USER');

      await user.$set('roles', [role.id]);
      user.roles = [role];
      return user;
    } catch (e) {
      throw new HttpException('The User already exist', HttpStatus.BAD_REQUEST);
    }
  }

  async getAllUsers() {
    const users = await this.userRepository.findAll({ include: { all: true } });
    return users;
  }

  async checkUser(dto: any) {
    try {
      const [bearer, token] = dto.authorization.split(' ');

      if (bearer !== 'Bearer' || !token) {
        // return { message: 'Unathorized User' };
        return false;
      }

      this.jwtService.verify(token);

      // return user;
      return true;
    } catch (e) {
      return false;
      // return { message: 'Unathorized User' };
    }
  }

  async getUserByEmail(email: string) {
    const user = await this.userRepository.findOne({
      where: { email },
      include: { all: true },
    });
    return user;
  }

  async addRole(dto: AddRoleDto) {
    const user = await this.userRepository.findByPk(dto.userId);

    const role = await this.roleService.getRoleByValue(dto.value);

    if (role && user) {
      await user.$add('role', role.id);
      return dto;
    }

    throw new HttpException(
      'The User or Role is not found',
      HttpStatus.NOT_FOUND,
    );
  }

  async ban(dto: BanUserDto) {
    const user = await this.userRepository.findByPk(dto.userId);

    if (!user) {
      throw new HttpException('The User is not found', HttpStatus.NOT_FOUND);
    }

    user.banned = true;
    user.banReason = dto.banReason;

    await user.save();

    return user;
  }

  update() {
    throw new Error('Method not implemented.');
  }

  dell() {
    throw new Error('Method not implemented.');
  }
}
