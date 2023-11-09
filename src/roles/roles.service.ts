import { Injectable } from '@nestjs/common';
import {RoleDTO} from "./dto/roles.dto";
import {InjectModel} from "@nestjs/sequelize";
import {Roles} from "./roles";

@Injectable()
export class RolesService {
    constructor(@InjectModel(Roles) private roleRepository: typeof Roles) {}

    async get() {
        return await this.roleRepository.findAll();
    }

    async getByValue(value: string) {
        return await this.roleRepository.findOne({where: {value}});
    }

    async update(dto: RoleDTO) {

    }

    async create(dto: RoleDTO) {
        return await this.roleRepository.create(dto);
    }

    async dell() {

    }
}
