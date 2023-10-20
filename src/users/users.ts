import {Column, DataType, Model, Table} from "sequelize-typescript";

interface UsersAttrs {
    email: string;
}
@Table({tableName: "users"})
export class Users extends Model<Users, UsersAttrs>{
    @Column({
        type: DataType.INTEGER,
        unique: true,
        autoIncrement: true,
        primaryKey: true,
    })
    id: number;

    @Column({ type: DataType.STRING, allowNull: true })
    name: string;

    @Column({ type: DataType.STRING, unique: true, allowNull: false })
    email: string;

    @Column({ type: DataType.STRING, allowNull: false })
    password: string;
}