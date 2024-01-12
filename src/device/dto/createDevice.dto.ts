import { ApiProperty } from '@nestjs/swagger';

export class CreateDeviceDTO {
  @ApiProperty({ example: 'Sumsung', description: 'Name of Brand' })
  readonly name: string;

  @ApiProperty({ example: '12000', description: 'Price of the Device' })
  readonly price: number;

  @ApiProperty({ example: '1', description: 'Brand ID' })
  readonly brandId: number;

  @ApiProperty({ example: '1', description: 'Type ID' })
  readonly typeId: number;

  @ApiProperty({
    example: 'c9a27af3-1aa8-4b64-8ea9-e60f0448d5a1.jpg',
    description: 'Img File Name with Extension',
  })
  readonly img: string;

  @ApiProperty({
    example: '[{"title": "Volume", "description": "10lbs"}, ...]',
    description: 'JSON for Device Info',
  })
  readonly deviceInfo: string;
}
