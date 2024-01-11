import { Body, Controller, Delete, Get, Post, Put } from '@nestjs/common';
import { ApiOperation, ApiResponse, ApiTags } from '@nestjs/swagger';
import { CreateTypeDTO } from './dto/createType.dto';
import { Type } from './type.model';
import { TypeService } from './type.service';

@ApiTags('Type')
@Controller('api/v1/type')
export class TypeController {
  constructor(private typeService: TypeService) {}

  @ApiOperation({ summary: 'type creation' })
  @ApiResponse({ status: 201, type: Type })
  @Post()
  create(@Body() dto: CreateTypeDTO) {
    return this.typeService.create(dto);
  }

  @ApiOperation({ summary: 'Getting type' })
  @ApiResponse({ status: 200, type: Type })
  @Get()
  getAll() {
    return this.typeService.getAll();
  }

  @ApiOperation({ summary: 'Remove Type' })
  @ApiResponse({ status: 200, type: Type })
  @Delete()
  deleteType(@Body() dto: CreateTypeDTO) {
    return this.typeService.removeType(dto);
  }

  @ApiOperation({ summary: 'Update Type' })
  @ApiResponse({ status: 200, type: Type })
  @Put()
  updateType(@Body() dto: CreateTypeDTO) {
    return this.typeService.updateType(dto);
  }
}
