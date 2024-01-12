import { Injectable } from '@nestjs/common';
import { InjectModel } from '@nestjs/sequelize';
import { CreateTypeDTO } from './dto/createType.dto';
import { Type } from './type.model';

@Injectable()
export class TypeService {
  constructor(@InjectModel(Type) private typeRepository: typeof Type) {}

  async create(dto: CreateTypeDTO) {
    const type = await this.typeRepository.create(dto);
    return type;
  }

  async removeType(dto: CreateTypeDTO) {
    const type = await this.typeRepository.create(dto);
    return type;
  }

  async updateType(dto: CreateTypeDTO) {
    const type = await this.typeRepository.create(dto);
    return type;
  }

  async getAll() {
    const type = await this.typeRepository.findAll({ include: { all: true } });
    return type;
  }
}
