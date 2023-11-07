import { NestFactory } from '@nestjs/core';
import * as process from "process";
import { AppModule } from './app.module';
import {DocumentBuilder, SwaggerModule} from "@nestjs/swagger";

async function nestApp() {
  const PORT = process.env.PORT || 5000;

  const app = await NestFactory.create(AppModule);

  const documentation = new DocumentBuilder()
      .setTitle("The Internet Store")
      .setDescription("Enhance my \"nest\" tech skills")
      .setVersion("1.0.0")
      .addTag("Sergy Addam")
      .build();

  const doc = SwaggerModule.createDocument(app, documentation);

  SwaggerModule.setup("/api/v1/docs", app, doc);

  await app.listen(PORT, () => console.log("ok -> ", PORT));
}
nestApp();
