import {
  HttpException,
  HttpStatus,
  Injectable,
  UnauthorizedException,
} from '@nestjs/common';
import { JwtService } from '@nestjs/jwt';
import { CreateUserDto } from 'src/users/dto/createUser.dto';
import { UsersService } from 'src/users/users.service';
import * as bcrypt from 'bcryptjs';
import { User } from 'src/users/users.model';

@Injectable()
export class AuthService {
  constructor(
    private userService: UsersService,
    private jwtService: JwtService,
  ) {}

  async login(userDto: CreateUserDto) {
    const user = await this.validateUser(userDto);
    return this.generateToken(user);
  }

  async registration(userDto: CreateUserDto) {
    const candidate = await this.userService.getUserByEmail(userDto.email);

    if (candidate) {
      throw new HttpException(
        'The USER is already exist',
        HttpStatus.BAD_REQUEST,
      );
    }

    const hash = await bcrypt.hash(userDto.password, 5);

    const user = await this.userService.createUser({
      ...userDto,
      password: hash,
    });

    // const basket = await this.basketService.createBasket({userId: user.id})

    return this.generateToken(user);
  }

  private async generateToken(user: User) {
    const { id, email, roles } = user;

    const payload = { id, email, roles };

    const token = this.jwtService.sign(payload);

    return { token };
  }

  private async validateUser(userDto: CreateUserDto) {
    const user = await this.userService.getUserByEmail(userDto.email);

    const isPasswordEqual = await bcrypt.compare(
      userDto.password,
      user.password,
    );

    if (user && isPasswordEqual) {
      return user;
    }

    throw new UnauthorizedException({
      message: 'Email or Pass is not correct',
    });
  }
}
