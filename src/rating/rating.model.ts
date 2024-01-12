import { ApiProperty } from '@nestjs/swagger';
import {
  BelongsTo,
  Column,
  DataType,
  ForeignKey,
  Model,
  Table,
} from 'sequelize-typescript';
import { Device } from 'src/device/device.model';
import { User } from 'src/users/users.model';

@Table({ tableName: 'rating', createdAt: false, updatedAt: false })
export class Rating extends Model<Rating> {
  @Column({
    type: DataType.INTEGER,
    unique: true,
    autoIncrement: true,
    primaryKey: true,
  })
  id: number;

  @ApiProperty({ example: '10', description: 'Rating of the Device' })
  @Column({ type: DataType.INTEGER, allowNull: false })
  rate: number;

  @ForeignKey(() => User)
  @Column({ type: DataType.INTEGER })
  user_id: number;

  @ForeignKey(() => Device)
  @Column({ type: DataType.INTEGER })
  device_id: number;

  @BelongsTo(() => User)
  user: User;

  @BelongsTo(() => Device)
  device: Device;
}
