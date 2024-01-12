import { ApiProperty } from '@nestjs/swagger';
import {
  BelongsToMany,
  Column,
  DataType,
  HasMany,
  HasOne,
  Model,
  Table,
} from 'sequelize-typescript';
import { Basket } from 'src/basket/basket.model';
import { Post } from 'src/posts/posts.model';
import { Rating } from 'src/rating/rating.model';
import { Role } from 'src/roles/roles.model';
import { UserRoles } from 'src/roles/userRoles.model';

interface AttrsToCreateUser {
  email: string;
  password: string;
}

@Table({ tableName: 'users' })
export class User extends Model<User, AttrsToCreateUser> {
  @ApiProperty({ example: '1', description: 'Unique ID' })
  @Column({
    type: DataType.INTEGER,
    unique: true,
    autoIncrement: true,
    primaryKey: true,
  })
  id: number;

  @ApiProperty({ example: 'user@gmail.com', description: 'email' })
  @Column({ type: DataType.STRING, unique: true, allowNull: false })
  email: string;

  @ApiProperty({ example: 'bestUserEver123', description: 'Password' })
  @Column({ type: DataType.STRING, allowNull: false })
  password: string;

  @ApiProperty({ example: 'true', description: 'is banned' })
  @Column({ type: DataType.BOOLEAN, defaultValue: false })
  banned: boolean;

  @ApiProperty({
    example: 'for bad behavior',
    description: 'Why is hi he banned',
  })
  @Column({ type: DataType.STRING, allowNull: true })
  banReason: string;

  @BelongsToMany(() => Role, () => UserRoles)
  roles: Role[];

  @HasMany(() => Post)
  posts: Post[];

  @HasOne(() => Basket)
  basket: Basket;

  @HasMany(() => Rating)
  rate: Rating[];
}
