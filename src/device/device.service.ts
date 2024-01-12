import { Injectable } from '@nestjs/common';
import { InjectModel } from '@nestjs/sequelize';
import { FilesService } from 'src/files/files.service';
import { Device } from './device.model';
import { DeviceInfo } from './deviceInfo.model';
import { CreateDeviceDTO } from './dto/createDevice.dto';
import { GetAllDeviceDTO } from './dto/getAllDevice.dto';

@Injectable()
export class DeviceService {
  constructor(
    @InjectModel(Device) private deviceRepository: typeof Device,
    @InjectModel(DeviceInfo) private deviceInfoRepository: typeof DeviceInfo,
    private fileService: FilesService,
  ) {}

  async createDevice(dto: CreateDeviceDTO, image: any) {
    console.log(dto);
    console.log(image);

    let fileName = '';

    if (image) {
      fileName = await this.fileService.createFile(image);
    }

    const device = await this.deviceRepository.create({
      ...dto,
      img: fileName,
    });

    if (dto.deviceInfo) {
      const info = JSON.parse(dto.deviceInfo);

      info.forEach((inf) => {
        this.deviceInfoRepository.create({
          title: inf.title,
          description: inf.description,
          deviceId: device.id,
        });
      });
    }

    return device;
  }

  async getDeviceById(id: number) {
    const device = await this.deviceRepository.findOne({
      where: { id },
      include: [{ model: DeviceInfo, as: 'deviceInfo' }],
    });

    return device;
  }

  async getAllDevices(dto: GetAllDeviceDTO) {
    const { brandId, typeId, limit = 100, page = 1 } = dto;

    const offset = page * limit - limit;

    if (!brandId && !typeId) {
      const devices = await this.deviceRepository.findAndCountAll({
        include: { all: true },
        limit,
        offset,
      });

      return devices;
    }

    if (brandId && !typeId) {
      const devices = await this.deviceRepository.findAndCountAll({
        include: { all: true },
        where: { brandId },
        limit,
        offset,
      });

      return devices;
    }

    if (!brandId && typeId) {
      const devices = await this.deviceRepository.findAndCountAll({
        include: { all: true },
        where: { typeId },
        limit,
        offset,
      });

      return devices;
    }

    if (brandId && typeId) {
      const devices = await this.deviceRepository.findAndCountAll({
        include: { all: true },
        where: { brandId, typeId },
        limit,
        offset,
      });

      return devices;
    }
  }

  dell() {
    throw new Error('Method not implemented.');
  }

  update(dto: CreateDeviceDTO) {
    throw new Error('Method not implemented.');
  }
}
