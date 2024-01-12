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
import { ApiOperation, ApiResponse, ApiTags } from '@nestjs/swagger';
import { Device } from './device.model';
import { DeviceService } from './device.service';
import { CreateDeviceDTO } from './dto/createDevice.dto';
import { GetAllDeviceDTO } from './dto/getAllDevice.dto';

@ApiTags('Device')
@Controller('api/v1/device')
export class DeviceController {
  constructor(private deviceService: DeviceService) {}

  @ApiOperation({ summary: 'Create Device' })
  @ApiResponse({ status: 201, type: Device })
  @Post()
  @UseInterceptors(FileInterceptor('img'))
  create(@Body() dto: CreateDeviceDTO, @UploadedFile() img) {
    return this.deviceService.createDevice(dto, img);
  }

  @ApiOperation({ summary: 'Get all devices' })
  @ApiResponse({ status: 200, type: [Device] })
  @Get()
  getAll(@Query() dto: GetAllDeviceDTO) {
    return this.deviceService.getAllDevices(dto);
  }

  @ApiOperation({ summary: 'Get device by ID' })
  @ApiResponse({ status: 200, type: Device })
  @Get('/:id')
  getByValue(@Param('id') id: number) {
    return this.deviceService.getDeviceById(id);
  }

  @ApiOperation({ summary: 'Update device', description: '' })
  @ApiResponse({ status: 200, type: '' })
  @Put()
  put(@Body() dto: CreateDeviceDTO) {
    return this.deviceService.update(dto);
  }

  @ApiOperation({ summary: 'Remove device', description: '' })
  @ApiResponse({ status: 200, type: '' })
  @Delete()
  delete() {
    return this.deviceService.dell();
  }
}
