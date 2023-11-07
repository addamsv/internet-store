import { NestFactory } from '@nestjs/core';
import * as process from "process";
import { AppModule } from './app.module';
import {DocumentBuilder, SwaggerModule} from "@nestjs/swagger";

async function nestApp() {
  const PORT = process.env.PORT || 5000;

  const app = await NestFactory.create(AppModule);

  const documentation = new DocumentBuilder()
      .setTitle("The Internet Store")
      .setDescription("Enhance my spring boot && nest tech skills")
      .setVersion("1.0.0")
      .addTag("Sergy Addam")
      .setLicense("MIT", "")
      .setTermsOfService("Terms of Service")
      .addServer("", "dev server")
      // .addSecurity("bearerAuth", {
      //     type: undefined,
      //     description: "JWT",
      //     name: "bearerAuth",
      //     scheme: "bearer"
      //   }
      // )
      .build();

  const doc = SwaggerModule.createDocument(app, documentation);

  SwaggerModule.setup("/api/v1/docs", app, doc); // 28:40

  await app.listen(PORT, () => console.log("ok -> ", PORT));
}
nestApp();
