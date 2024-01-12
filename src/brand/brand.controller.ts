import { Body, Controller, Delete, Get, Post, Put } from '@nestjs/common';
import { ApiOperation, ApiResponse, ApiTags } from '@nestjs/swagger';
import { Brand } from './brand.model';
import { BrandService } from './brand.service';
import { CreateBrandDto } from './dto/createBrand.dto';
import { RemoveBrandDto } from './dto/removeBrand.dto';

@ApiTags('Brand')
@Controller('api/v1/brand')
export class BrandController {
  constructor(private brandService: BrandService) {}

  @ApiOperation({ summary: 'Create Brand' })
  @ApiResponse({ status: 201, type: Brand })
  @Post()
  createPost(@Body() dto: CreateBrandDto) {
    console.log('=============');
    console.log(dto);

    return this.brandService.create(dto);
  }

  @ApiOperation({ summary: 'Get all Brands' })
  @ApiResponse({ status: 200, type: Brand })
  @Get()
  getAll() {
    return this.brandService.getAllBrands();
  }

  @ApiOperation({ summary: 'Remove Brand' })
  @ApiResponse({ status: 200, type: Brand })
  @Delete()
  deleteBrand(@Body() dto: RemoveBrandDto) {
    return this.brandService.removeBrand(dto);
  }

  @ApiOperation({ summary: 'Update Brand' })
  @ApiResponse({ status: 200, type: Brand })
  @Put()
  updateBrand(@Body() dto: CreateBrandDto) {
    return this.brandService.updateBrand(dto);
  }
}
