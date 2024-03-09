import {
  Body,
  Controller,
  Delete,
  Get,
  Param,
  Post,
  Put,
} from '@nestjs/common';
import {
  ApiBearerAuth,
  ApiOperation,
  ApiResponse,
  ApiTags,
} from '@nestjs/swagger';
import { CreateRoleDTO } from './dto/createRole.dto';
import { Role } from './roles.model';
import { RolesService } from './roles.service';

@ApiTags('Roles')
@ApiBearerAuth()
// @ApiBasicAuth()
@Controller('api/v1/roles')
export class RolesController {
  constructor(private roleService: RolesService) {}

  @Post()
  @ApiOperation({ summary: 'Role creation' })
  @ApiResponse({ status: 201, type: Role })
  create(@Body() dto: CreateRoleDTO) {
    return this.roleService.create(dto);
  }

  @Get('/:value')
  @ApiOperation({ summary: 'Getting role' })
  @ApiResponse({ status: 200, type: Role })
  getByValue(@Param('value') value: string) {
    return this.roleService.getRoleByValue(value);
  }

  @Post()
  @ApiOperation({ summary: 'Create roles', description: '' })
  @ApiResponse({ status: 200, type: Role })
  post(@Body() dto: CreateRoleDTO) {
    return this.roleService.create(dto);
  }

  @Put()
  @ApiOperation({ summary: 'Update role', description: '' })
  @ApiResponse({ status: 200, type: Role })
  put(@Body() dto: CreateRoleDTO) {
    return this.roleService.update(dto);
  }

  @Delete()
  @ApiOperation({ summary: 'Remove Role', description: '' })
  @ApiResponse({ status: 200, type: Role })
  delete() {
    return this.roleService.dell();
  }
}
