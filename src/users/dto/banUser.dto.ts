import { ApiProperty } from '@nestjs/swagger';

export class BanUserDto {
  @ApiProperty({ example: '5', description: 'User ID' })
  readonly userId: number;

  @ApiProperty({ example: 'Bad Ass', description: 'Ban Reason' })
  readonly banReason: string;
}
