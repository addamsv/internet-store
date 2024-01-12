import { ApiProperty } from "@nestjs/swagger";
import { BelongsToMany, Column, DataType, Model, Table } from "sequelize-typescript";
import { User } from "src/users/users.model";
import { UserRoles } from "./userRoles.model";

interface AttrsToCreateRole {
  value: string;
  description: string;
}

@Table({tableName: 'roles'})
export class Role extends Model<Role, AttrsToCreateRole>{
  @ApiProperty({example: '1', description: "Unique ID"})
  @Column({type: DataType.INTEGER, unique: true, autoIncrement: true, primaryKey: true})
  id: number;

  @ApiProperty({example: 'ADMIN', description: "unique roles name"})
  @Column({type: DataType.STRING, unique: true, allowNull: false})
  value: string;

  @ApiProperty({example: 'Administrator', description: "description of the role"})
  @Column({type: DataType.STRING, allowNull: false})
  description: string;

  @BelongsToMany(() => User, () => UserRoles)
  users: User[];
}
