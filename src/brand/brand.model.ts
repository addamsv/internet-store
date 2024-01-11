import { ApiProperty } from '@nestjs/swagger';
import {
  BelongsToMany,
  Column,
  DataType,
  // HasMany,
  Model,
  Table,
} from 'sequelize-typescript';
// import { Device } from 'src/device/device.model';
// import { TypeBrand } from 'src/type/type-brand.model';
// import { Type } from 'src/type/type.model';

@Table({ tableName: 'brand', createdAt: false, updatedAt: false })
export class Brand extends Model<Brand> {
  @Column({
    type: DataType.INTEGER,
    unique: true,
    autoIncrement: true,
    primaryKey: true,
  })
  id: number;

  @ApiProperty({ example: 'Samsung', description: 'Brand of the Device' })
  @Column({ type: DataType.STRING, unique: true, allowNull: false })
  name: string;

  // @HasMany(() => Device)
  // device: Device[];

  // @BelongsToMany(() => Type, () => TypeBrand)
  // types: Type[];
}
