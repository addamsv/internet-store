import { ApiProperty } from '@nestjs/swagger';

export class GetAllDeviceDTO {
  @ApiProperty({ example: '1', description: 'Brand ID' })
  readonly brandId: number;

  @ApiProperty({ example: '1', description: 'Type ID' })
  readonly typeId: number;

  @ApiProperty({ example: '5', description: 'Limit' })
  readonly limit: number;

  @ApiProperty({ example: '1', description: 'Page' })
  readonly page: number;
}
