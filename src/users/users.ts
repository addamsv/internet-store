import {Column, DataType, Model, Table} from "sequelize-typescript";
import {ApiProperty} from "@nestjs/swagger";

interface UsersAttrs {
    email: string;
    password: string;
}

@Table({tableName: "users"})
export class Users extends Model<Users, UsersAttrs>{
    @Column({
        type: DataType.INTEGER,
        unique: true,
        autoIncrement: true,
        primaryKey: true,
    })
    @ApiProperty({example: "1", description: "Users ID"})
    id: number;

    @Column({ type: DataType.STRING, unique: true, allowNull: false })
    @ApiProperty({example: "a@a.a", description: "Users ID"})
    email: string;

    @Column({ type: DataType.STRING, allowNull: false })
    @ApiProperty({example: "pass-a-123", description: "Users password"})
    password: string;
}