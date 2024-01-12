import { Module } from '@nestjs/common';
import { BasketService } from './basket.service';
import { BasketController } from './basket.controller';
import { SequelizeModule } from '@nestjs/sequelize';
import { Basket } from './basket.model';
import { BasketDevice } from './basketDevice.model';

@Module({
  providers: [BasketService],
  controllers: [BasketController],
  imports: [SequelizeModule.forFeature([Basket, BasketDevice])],
})
export class BasketModule {}
