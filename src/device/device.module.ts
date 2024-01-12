import { Module } from '@nestjs/common';
import { SequelizeModule } from '@nestjs/sequelize';
import { FilesModule } from 'src/files/files.module';
import { DeviceController } from './device.controller';
import { Device } from './device.model';
import { DeviceService } from './device.service';
import { DeviceInfo } from './deviceInfo.model';

@Module({
  controllers: [DeviceController],
  providers: [DeviceService],
  imports: [SequelizeModule.forFeature([Device, DeviceInfo]), FilesModule],
})
export class DeviceModule {}
