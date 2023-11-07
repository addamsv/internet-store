import {ApiProperty} from "@nestjs/swagger";

export class UserDto {
    @ApiProperty({example: "a@a.a", description: "Users ID"})
    email: string;
    @ApiProperty({example: "aaa-1V", description: "Users password"})
    password: string;
}