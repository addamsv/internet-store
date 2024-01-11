import { Module } from '@nestjs/common';
import { SequelizeModule } from '@nestjs/sequelize';
import { TypeBrand } from 'src/type/type-brand.model';
import { BrandController } from './brand.controller';
import { Brand } from './brand.model';
import { BrandService } from './brand.service';

@Module({
  controllers: [BrandController],
  providers: [BrandService],
  imports: [SequelizeModule.forFeature([Brand, TypeBrand])],
})
export class BrandModule {}
