import {BelongsToMany, Column, DataType, Model, Table} from "sequelize-typescript";
import {ApiProperty} from "@nestjs/swagger";
import {Users} from "../users/users";
import {UsersRoles} from "./usersRoles";

interface RolesAttrs {
    value: string;
    description: string;
}

@Table({tableName: "roles"})
export class Roles extends Model<Roles, RolesAttrs>{
    @Column({
        type: DataType.INTEGER,
        unique: true,
        autoIncrement: true,
        primaryKey: true,
    })
    @ApiProperty({example: "1", description: "Roles ID"})
    id: number;

    @Column({ type: DataType.STRING, unique: true, allowNull: false })
    @ApiProperty({example: "ADMIN", description: "The Users role"})
    value: string;

    @Column({ type: DataType.STRING, allowNull: false })
    @ApiProperty({example: "Administration", description: "Roles Description"})
    description: string;

    // many-to-many
    @BelongsToMany(() => Users, () => UsersRoles)
    users: Users[];
}