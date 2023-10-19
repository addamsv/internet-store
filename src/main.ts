import { NestFactory } from '@nestjs/core';
import * as process from "process";
import { AppModule } from './app.module';

async function nestApp() {
  const PORT = process.env.PORT || 5000;

  const app = await NestFactory.create(AppModule);

  await app.listen(PORT, () => console.log("ok -> ", PORT));
}
nestApp();
