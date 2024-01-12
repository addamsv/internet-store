import { Module } from '@nestjs/common';
import { SequelizeModule } from '@nestjs/sequelize';
import { RatingController } from './rating.controller';
import { Rating } from './rating.model';
import { RatingService } from './rating.service';

@Module({
  controllers: [RatingController],
  providers: [RatingService],
  imports: [SequelizeModule.forFeature([Rating])],
})
export class RatingModule {}
