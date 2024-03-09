import { Body, Controller, Delete, Get, Post, Put } from '@nestjs/common';
import {
  ApiBearerAuth,
  ApiOperation,
  ApiResponse,
  ApiTags,
} from '@nestjs/swagger';
import { CreateTypeDTO } from './dto/createType.dto';
import { Type } from './type.model';
import { TypeService } from './type.service';

@ApiTags('Type')
@Controller('api/v1/type')
export class TypeController {
  constructor(private typeService: TypeService) {}

  @Get()
  @ApiOperation({ summary: 'Getting type' })
  @ApiResponse({ status: 200, type: Type })
  getAll() {
    return this.typeService.getAll();
  }

  @Post()
  @ApiBearerAuth()
  @ApiOperation({ summary: 'type creation' })
  @ApiResponse({ status: 201, type: Type })
  create(@Body() dto: CreateTypeDTO) {
    return this.typeService.create(dto);
  }

  @Delete()
  @ApiBearerAuth()
  @ApiOperation({ summary: 'Remove Type' })
  @ApiResponse({ status: 200, type: Type })
  deleteType(@Body() dto: CreateTypeDTO) {
    return this.typeService.removeType(dto);
  }

  @Put()
  @ApiBearerAuth()
  @ApiOperation({ summary: 'Update Type' })
  @ApiResponse({ status: 200, type: Type })
  updateType(@Body() dto: CreateTypeDTO) {
    return this.typeService.updateType(dto);
  }
}
