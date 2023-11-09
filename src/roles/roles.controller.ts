import {Body, Controller, Delete, Get, Param, Post, Put} from '@nestjs/common';
import {RolesService} from "./roles.service";
import {RoleDTO} from "./dto/roles.dto";
import {ApiOperation, ApiResponse, ApiTags} from "@nestjs/swagger";
import {Roles} from "./roles";

@ApiTags("Roles")
@Controller('/api/v1/roles')
export class RolesController {
    constructor(private rolesService: RolesService) {}

    @ApiOperation({summary: "Get all roles", description: ""})
    @ApiResponse({status: 200, type: [Roles]})
    @Get()
    get() {
        return this.rolesService.get();
    }

    @ApiOperation({summary: "Get role by value", description: ""})
    @ApiResponse({status: 200, type: Roles})
    @Get("/:value")
    getByValue(@Param("value") value: string) {
        return this.rolesService.getByValue(value);
    }

    @ApiOperation({summary: "Update role", description: ""})
    @ApiResponse({status: 200, type: Roles})
    @Put()
    put(@Body() dto: RoleDTO) {
        return this.rolesService.update(dto);
    }

    @ApiOperation({summary: "Create roles", description: ""})
    @ApiResponse({status: 200, type: Roles})
    @Post()
    post(@Body() dto: RoleDTO) {
        return this.rolesService.create(dto);
    }

    @ApiOperation({summary: "Remove Role", description: ""})
    @ApiResponse({status: 200, type: Roles})
    @Delete()
    delete() {
        return this.rolesService.dell();
    }
}
