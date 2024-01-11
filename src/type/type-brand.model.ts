import {
  Column,
  DataType,
  Model,
  Table,
  ForeignKey,
} from 'sequelize-typescript';
import { Brand } from 'src/brand/brand.model';
import { Type } from './type.model';

@Table({ tableName: 'type_brand', createdAt: false, updatedAt: false })
export class TypeBrand extends Model<TypeBrand> {
  @Column({
    type: DataType.INTEGER,
    unique: true,
    autoIncrement: true,
    primaryKey: true,
  })
  id: number;

  @ForeignKey(() => Type)
  @Column({ type: DataType.INTEGER })
  typeId: number;

  @ForeignKey(() => Brand)
  @Column({ type: DataType.INTEGER })
  brandId: number;
}
