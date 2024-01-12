import { ApiProperty } from '@nestjs/swagger';
import { IsString, IsEmail, Length } from 'class-validator';

export class CreateUserDto {
  @ApiProperty({ example: 'user@gmail.com', description: 'email' })
  @IsString({ message: 'Should be a string' })
  @IsEmail({}, { message: 'Should be an email' })
  readonly email: string;

  @ApiProperty({ example: 'bestUserEver', description: 'Password' })
  @IsString({ message: 'Should be a string' })
  @Length(4, 16, { message: 'Should be more then 4 and less then 16' })
  readonly password: string;
}
