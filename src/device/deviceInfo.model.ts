import { ApiProperty } from '@nestjs/swagger';
import {
  BelongsTo,
  Column,
  DataType,
  ForeignKey,
  Model,
  Table,
} from 'sequelize-typescript';
import { Device } from './device.model';

interface AttrsToCreateDeviceInfo {
  title: string;
  description: number;
}

@Table({ tableName: 'device_info' })
export class DeviceInfo extends Model<DeviceInfo> {
  @ApiProperty({ example: '1', description: 'Unique ID' })
  @Column({
    type: DataType.INTEGER,
    unique: true,
    autoIncrement: true,
    primaryKey: true,
  })
  id: number;

  @ApiProperty({
    example: 'Value',
    description: 'Property of a device',
  })
  @Column({ type: DataType.STRING, allowNull: false })
  title: string;

  @ApiProperty({
    example: '10lt',
    description: 'Description of some property',
  })
  @Column({ type: DataType.STRING, allowNull: false })
  description: string;

  @ForeignKey(() => Device)
  @Column({ type: DataType.INTEGER })
  deviceId: number;

  @BelongsTo(() => Device)
  device: Device;
}
