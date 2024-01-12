import { ApiProperty } from '@nestjs/swagger';

export class CreateBrandDto {
  @ApiProperty({ example: 'Sumsung', description: 'Name of Brand' })
  readonly name: string;
}
