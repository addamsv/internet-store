import { Controller } from '@nestjs/common';
import { ApiTags } from '@nestjs/swagger';

@ApiTags('Rating')
@Controller('api/v1/rating')
export class RatingController {}
