import { ApiProperty } from '@nestjs/swagger';
import {
  BelongsTo,
  Column,
  DataType,
  ForeignKey,
  HasMany,
  Model,
  Table,
} from 'sequelize-typescript';
import { BasketDevice } from 'src/basket/basketDevice.model';
import { Brand } from 'src/brand/brand.model';
import { Rating } from 'src/rating/rating.model';
import { Type } from 'src/type/type.model';
import { DeviceInfo } from './deviceInfo.model';

interface AttrsToCreateDevice {
  name: string;
  price: number;
  rating: number;
  img: string;
}

@Table({ tableName: 'device' })
export class Device extends Model<Device, AttrsToCreateDevice> {
  @ApiProperty({ example: '1', description: 'Unique ID' })
  @Column({
    type: DataType.INTEGER,
    unique: true,
    autoIncrement: true,
    primaryKey: true,
  })
  id: number;

  @ApiProperty({
    example: 'The Awesome name',
    description: 'name of the Device',
  })
  @Column({ type: DataType.STRING, unique: true, allowNull: false })
  name: string;

  @ApiProperty({ example: '1000', description: 'Price of the Device' })
  @Column({ type: DataType.INTEGER, allowNull: false })
  price: string;

  @ApiProperty({ example: '10', description: 'Rating of the Device' })
  @Column({ type: DataType.INTEGER, defaultValue: 0 })
  rating: string;

  @ApiProperty({ example: '123.png', description: 'Image File Name' })
  @Column({ type: DataType.STRING })
  img: string;

  @ForeignKey(() => Type)
  @Column({ type: DataType.INTEGER })
  typeId: number;

  @ForeignKey(() => Brand)
  @Column({ type: DataType.INTEGER })
  brandId: number;

  @HasMany(() => DeviceInfo)
  deviceInfo: DeviceInfo[];

  @HasMany(() => Rating)
  rate: Rating[];

  @HasMany(() => BasketDevice)
  basketDevice: BasketDevice[];

  @BelongsTo(() => Type)
  type: Type;

  @BelongsTo(() => Brand)
  brand: Brand;
}
