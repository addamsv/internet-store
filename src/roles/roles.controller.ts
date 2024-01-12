import {
  Body,
  Controller,
  Delete,
  Get,
  Param,
  Post,
  Put,
} from '@nestjs/common';
import { ApiOperation, ApiResponse, ApiTags } from '@nestjs/swagger';
import { CreateRoleDTO } from './dto/createRole.dto';
import { Role } from './roles.model';
import { RolesService } from './roles.service';

@ApiTags('Roles')
@Controller('api/v1/roles')
export class RolesController {
  constructor(private roleService: RolesService) {}

  @ApiOperation({ summary: 'Role creation' })
  @ApiResponse({ status: 201, type: Role })
  @Post()
  create(@Body() dto: CreateRoleDTO) {
    return this.roleService.create(dto);
  }

  @ApiOperation({ summary: 'Getting role' })
  @ApiResponse({ status: 200, type: Role })
  @Get('/:value')
  getByValue(@Param('value') value: string) {
    return this.roleService.getRoleByValue(value);
  }

  @ApiOperation({ summary: 'Create roles', description: '' })
  @ApiResponse({ status: 200, type: Role })
  @Post()
  post(@Body() dto: CreateRoleDTO) {
    return this.roleService.create(dto);
  }

  @ApiOperation({ summary: 'Update role', description: '' })
  @ApiResponse({ status: 200, type: Role })
  @Put()
  put(@Body() dto: CreateRoleDTO) {
    return this.roleService.update(dto);
  }

  @ApiOperation({ summary: 'Remove Role', description: '' })
  @ApiResponse({ status: 200, type: Role })
  @Delete()
  delete() {
    return this.roleService.dell();
  }
}
