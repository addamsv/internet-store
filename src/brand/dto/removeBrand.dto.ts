import { ApiProperty } from '@nestjs/swagger';

export class RemoveBrandDto {
  @ApiProperty({ example: 'Sumsung', description: 'Name of Brand' })
  readonly name: string;
}
