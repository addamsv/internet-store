import { HttpException, HttpStatus, Injectable } from '@nestjs/common';
import { InjectModel } from '@nestjs/sequelize';
import { Brand } from './brand.model';
import { CreateBrandDto } from './dto/createBrand.dto';
import { RemoveBrandDto } from './dto/removeBrand.dto';

@Injectable()
export class BrandService {
  constructor(@InjectModel(Brand) private brandRepository: typeof Brand) {}

  async create(dto: CreateBrandDto) {
    console.log(dto);

    const brand = await this.brandRepository.create(dto);

    return brand;
  }

  async getAllBrands() {
    const brands = await this.brandRepository.findAll({
      include: { all: true },
    });

    return brands;
  }

  async removeBrand(dto: RemoveBrandDto) {
    const candidat = await this.getBrandByName(dto.name);

    return candidat;
  }

  async updateBrand(dto: CreateBrandDto) {
    const candidat = await this.getBrandByName(dto.name);

    return candidat;
  }

  async getBrandByName(name: string) {
    const brand = await this.brandRepository.findOne({ where: { name } });

    if (!brand) {
      throw new HttpException(
        'The Brand ' + name + ' does not exist',
        HttpStatus.BAD_REQUEST,
      );
    }

    return brand;
  }
}
