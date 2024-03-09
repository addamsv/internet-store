import { Body, Controller, Delete, Get, Post, Put } from '@nestjs/common';
import {
  ApiBearerAuth,
  ApiOperation,
  ApiResponse,
  ApiTags,
} from '@nestjs/swagger';
import { Brand } from './brand.model';
import { BrandService } from './brand.service';
import { CreateBrandDto } from './dto/createBrand.dto';
import { RemoveBrandDto } from './dto/removeBrand.dto';

@ApiTags('Brand')
@Controller('api/v1/brand')
export class BrandController {
  constructor(private brandService: BrandService) {}

  @Get()
  @ApiOperation({ summary: 'Get all Brands' })
  @ApiResponse({ status: 200, type: Brand })
  getAll() {
    return this.brandService.getAllBrands();
  }

  @Post()
  @ApiBearerAuth()
  @ApiOperation({ summary: 'Create Brand' })
  @ApiResponse({ status: 201, type: Brand })
  createPost(@Body() dto: CreateBrandDto) {
    return this.brandService.create(dto);
  }

  @Put()
  @ApiBearerAuth()
  @ApiOperation({ summary: 'Update Brand' })
  @ApiResponse({ status: 200, type: Brand })
  updateBrand(@Body() dto: CreateBrandDto) {
    return this.brandService.updateBrand(dto);
  }

  @Delete()
  @ApiBearerAuth()
  @ApiOperation({ summary: 'Remove Brand' })
  @ApiResponse({ status: 200, type: Brand })
  deleteBrand(@Body() dto: RemoveBrandDto) {
    return this.brandService.removeBrand(dto);
  }
}
