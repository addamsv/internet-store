import { ApiProperty } from '@nestjs/swagger';

export class CreateTypeDTO {
  @ApiProperty({ example: 'Fridge', description: 'Type for a device' })
  readonly name: string;
}
