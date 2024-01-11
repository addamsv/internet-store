import { ApiProperty } from '@nestjs/swagger';
import {
  Column,
  DataType,
  Model,
  Table,
  // HasMany,
  // BelongsToMany,
} from 'sequelize-typescript';
// import { Brand } from 'src/brand/brand.model';
// import { Device } from 'src/device/device.model';
// import { TypeBrand } from './type-brand.model';

@Table({ tableName: 'type', createdAt: false, updatedAt: false })
export class Type extends Model<Type> {
  @Column({
    type: DataType.INTEGER,
    unique: true,
    autoIncrement: true,
    primaryKey: true,
  })
  id: number;

  @ApiProperty({ example: 'Refrigirators', description: 'Type of the Device' })
  @Column({ type: DataType.STRING, unique: true, allowNull: false })
  name: string;

  // @HasMany(() => Device)
  // device: Device[];

  // @BelongsToMany(() => Brand, () => TypeBrand)
  // brands: Brand[];
}
