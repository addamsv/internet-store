import {
  BelongsTo,
  Column,
  DataType,
  ForeignKey,
  Model,
  Table,
} from 'sequelize-typescript';
import { Device } from 'src/device/device.model';
import { Basket } from './basket.model';

@Table({ tableName: 'basket_device', createdAt: false, updatedAt: false })
export class BasketDevice extends Model<BasketDevice> {
  @Column({
    type: DataType.INTEGER,
    unique: true,
    autoIncrement: true,
    primaryKey: true,
  })
  id: number;

  @ForeignKey(() => Device)
  @Column({ type: DataType.INTEGER })
  device_id: number;

  @ForeignKey(() => Basket)
  @Column({ type: DataType.INTEGER })
  basket_id: number;

  @BelongsTo(() => Basket)
  basket: Basket;

  @BelongsTo(() => Device)
  device: Device;
}
