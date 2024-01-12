import {
  Body,
  Controller,
  Delete,
  Post,
  Put,
  UploadedFile,
  UseInterceptors,
} from '@nestjs/common';
import { FileInterceptor } from '@nestjs/platform-express';
import { ApiOperation, ApiResponse, ApiTags } from '@nestjs/swagger';
import { CreatePostDto } from './dto/createPost.dto';
import { PostsService } from './posts.service';

@ApiTags('Posts')
@Controller('api/v1/posts')
export class PostsController {
  constructor(private postService: PostsService) {}

  @Post()
  @UseInterceptors(FileInterceptor('image'))
  create(@Body() dto: CreatePostDto, @UploadedFile() image) {
    return this.postService.create(dto, image);
  }

  @ApiOperation({ summary: 'Update post', description: '' })
  @ApiResponse({ status: 200, type: '' })
  @Put()
  put(@Body() dto: CreatePostDto) {
    return this.postService.update(dto);
  }

  @ApiOperation({ summary: 'Remove post', description: '' })
  @ApiResponse({ status: 200, type: '' })
  @Delete()
  delete() {
    return this.postService.dell();
  }
}
