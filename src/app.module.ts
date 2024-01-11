import { Module } from '@nestjs/common';
import { ConfigModule } from '@nestjs/config';
import { SequelizeModule } from '@nestjs/sequelize';

import { ServeStaticModule } from '@nestjs/serve-static';

import { join } from 'path';

import { Type } from './type/type.model';
import { TypeModule } from './type/type.module';
import { Brand } from './brand/brand.model';
import { BrandModule } from './brand/brand.module';

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
      models: [Type, Brand],
      autoLoadModels: true,
    }),
    TypeModule,
    BrandModule,
  ],
  controllers: [],
  providers: [],
})
export class AppModule {}
