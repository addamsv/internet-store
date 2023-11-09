import {Body, Controller, Delete, Get, Param, Post, Put} from '@nestjs/common';
import {RolesService} from "./roles.service";
import {RoleDTO} from "./dto/roles.dto";

@Controller('roles')
export class RolesController {
    constructor(private rolesService: RolesService) {}
    @Get()
    get() {
        return this.rolesService.get();
    }

    @Get("/:value")
    getByValue(@Param("value") value: string) {
        return this.rolesService.getByValue(value);
    }

    @Put()
    put(@Body() dto: RoleDTO) {
        return this.rolesService.update(dto);
    }

    @Post()
    post(@Body() dto: RoleDTO) {
        return this.rolesService.create(dto);
    }

    @Delete()
    delete() {
        return this.rolesService.dell();
    }
}
