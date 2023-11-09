import {BelongsToMany, Column, DataType, ForeignKey, Model, Table} from "sequelize-typescript";
import {ApiProperty} from "@nestjs/swagger";
import {Users} from "../users/users";
import {Roles} from "./roles";

@Table({tableName: "users_roles", createdAt: false, updatedAt: false})
export class UsersRoles extends Model<UsersRoles>{
    @Column({
        type: DataType.INTEGER,
        unique: true,
        autoIncrement: true,
        primaryKey: true,
    })
    id: number;

    @ForeignKey(() => Roles)
    @Column({ type: DataType.INTEGER })
    roleId: number;

    @ForeignKey(() => Users)
    @Column({ type: DataType.INTEGER })
    userId: number;
}