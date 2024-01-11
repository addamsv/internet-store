import { ApiProperty } from '@nestjs/swagger';
import { Column, DataType, Model, Table } from 'sequelize-typescript';

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
}
