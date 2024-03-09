import {
  Body,
  Controller,
  Delete,
  Get,
  Param,
  Post,
  Put,
  Query,
  UploadedFile,
  UseInterceptors,
} from '@nestjs/common';
import { FileInterceptor } from '@nestjs/platform-express';
import {
  ApiBearerAuth,
  ApiOperation,
  ApiResponse,
  ApiTags,
} from '@nestjs/swagger';
import { Device } from './device.model';
import { DeviceService } from './device.service';
import { CreateDeviceDTO } from './dto/createDevice.dto';
import { GetAllDeviceDTO } from './dto/getAllDevice.dto';

@ApiTags('Device')
@Controller('api/v1/device')
export class DeviceController {
  constructor(private deviceService: DeviceService) {}

  @Get()
  @ApiOperation({ summary: 'Get all devices' })
  @ApiResponse({ status: 200, type: [Device] })
  getAll(@Query() dto: GetAllDeviceDTO) {
    return this.deviceService.getAllDevices(dto);
  }

  @Get('/:id')
  @ApiOperation({ summary: 'Get device by ID' })
  @ApiResponse({ status: 200, type: Device })
  getByValue(@Param('id') id: number) {
    return this.deviceService.getDeviceById(id);
  }

  @Post()
  @ApiBearerAuth()
  @ApiOperation({ summary: 'Create Device' })
  @ApiResponse({ status: 201, type: Device })
  @UseInterceptors(FileInterceptor('img'))
  create(@Body() dto: CreateDeviceDTO, @UploadedFile() img) {
    return this.deviceService.createDevice(dto, img);
  }

  @Put()
  @ApiBearerAuth()
  @ApiOperation({ summary: 'Update device', description: '' })
  @ApiResponse({ status: 200, type: '' })
  put(@Body() dto: CreateDeviceDTO) {
    return this.deviceService.update(dto);
  }

  @Delete()
  @ApiBearerAuth()
  @ApiOperation({ summary: 'Remove device', description: '' })
  @ApiResponse({ status: 200, type: '' })
  delete() {
    return this.deviceService.dell();
  }
}
