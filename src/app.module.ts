import { Module } from '@nestjs/common';
import { ConfigModule } from '@nestjs/config';
import { SequelizeModule } from '@nestjs/sequelize';
import { User } from './users/users.model';
import { UsersModule } from './users/users.module';
import { RolesModule } from './roles/roles.module';
import { Role } from './roles/roles.model';
import { UserRoles } from './roles/userRoles.model';
import { AuthModule } from './auth/auth.module';
import { PostsModule } from './posts/posts.module';
import { Post } from './posts/posts.model';
import { FilesModule } from './files/files.module';
import { ServeStaticModule } from '@nestjs/serve-static';
import { BasketModule } from './basket/basket.module';
import { DeviceModule } from './device/device.module';
import { TypeModule } from './type/type.module';
import { BrandModule } from './brand/brand.module';
import { RatingModule } from './rating/rating.module';
import * as path from 'path';
import { Rating } from './rating/rating.model';
import { Device } from './device/device.model';
import { DeviceInfo } from './device/deviceInfo.model';
import { Brand } from './brand/brand.model';
import { Type } from './type/type.model';
import { Basket } from './basket/basket.model';
import { BasketDevice } from './basket/basketDevice.model';
import { TypeBrand } from './type/type-brand.model';
import { join } from 'path';

console.log(join(__dirname, '..', 'static'));
@Module({
  imports: [
    ConfigModule.forRoot({
      envFilePath: `.${process.env.NODE_ENV}.env`,
    }),
    ServeStaticModule.forRoot({
      rootPath: join(__dirname, 'static'), // path.resolve(__dirname, 'static'),
    }),
    SequelizeModule.forRoot({
      dialect: 'postgres',
      host: process.env.POSTGRES_HOST,
      port: Number(process.env.POSTGRES_PORT),
      username: process.env.POSTGRES_USER,
      password: process.env.POSTGRES_PASSWORD,
      database: process.env.POSTGRES_DB,
      models: [
        User,
        Role,
        UserRoles,
        Post,
        Rating,
        Type,
        TypeBrand,
        Device,
        DeviceInfo,
        Brand,
        Basket,
        BasketDevice,
      ],
      autoLoadModels: true,
    }),
    UsersModule,
    RolesModule,
    AuthModule,
    PostsModule,
    FilesModule,
    BasketModule,
    DeviceModule,
    TypeModule,
    BrandModule,
    RatingModule,
  ],
  controllers: [],
  providers: [],
})
export class AppModule {}
