import { ApiProperty } from '@nestjs/swagger';
import { Column, DataType, Model, Table } from 'sequelize-typescript';

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
}
