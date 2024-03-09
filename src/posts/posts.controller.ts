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
import {
  ApiBearerAuth,
  ApiOperation,
  ApiResponse,
  ApiTags,
} from '@nestjs/swagger';
import { CreatePostDto } from './dto/createPost.dto';
import { PostsService } from './posts.service';

@ApiTags('Posts')
@Controller('api/v1/posts')
export class PostsController {
  constructor(private postService: PostsService) {}

  @Post()
  @ApiBearerAuth()
  @UseInterceptors(FileInterceptor('image'))
  create(@Body() dto: CreatePostDto, @UploadedFile() image) {
    return this.postService.create(dto, image);
  }

  @Put()
  @ApiBearerAuth()
  @ApiOperation({ summary: 'Update post', description: '' })
  @ApiResponse({ status: 200, type: '' })
  put(@Body() dto: CreatePostDto) {
    return this.postService.update(dto);
  }

  @Delete()
  @ApiBearerAuth()
  @ApiOperation({ summary: 'Remove post', description: '' })
  @ApiResponse({ status: 200, type: '' })
  delete() {
    return this.postService.dell();
  }
}
