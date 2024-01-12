import { ApiProperty } from '@nestjs/swagger';

export class CreateRoleDTO {
  @ApiProperty({ example: 'ADMIN', description: 'role for ADMIN' })
  readonly value: string;

  @ApiProperty({
    example: 'role for ADMIN',
    description: 'description of role',
  })
  readonly description: string;
}
