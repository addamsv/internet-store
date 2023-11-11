import { NestFactory } from '@nestjs/core';
import * as process from "process";
import { AppModule } from './app.module';
import {DocumentBuilder, SwaggerModule} from "@nestjs/swagger";
import {APP_DESCRIPTION, APP_NAME, APP_VERSION} from "./common/constants";

async function nestApp() {
  const PORT = process.env.PORT || 5000;

  const app = await NestFactory.create(AppModule);

  // -- Cors setup
  app.enableCors({
    origin: false, // Specify the allowed origins.  I'm setting false to allow requests from any origin
    // Find more configuration options here: https://github.com/expressjs/cors#configuration-options
  });

  const documentation = new DocumentBuilder()
      .setTitle(APP_NAME)
      .setDescription(APP_DESCRIPTION)
      .setVersion(APP_VERSION)
      // .addTag("Sergy Addam")
      // .setLicense("MIT", "")
      // .setTermsOfService("Terms of Service")
      .addServer("", "dev server")
      .addBearerAuth() // The API will use Bearer Authentication
      .addBasicAuth({ type: 'apiKey', name: 'accessToken', in: 'query' })
      .build();

  const doc = SwaggerModule.createDocument(app, documentation);

  SwaggerModule.setup("/api/v1/docs", app, doc); // 28:40

  await app.listen(PORT, () => {
    console.log(`example: http://localhost:${PORT}/api/v1/users`)
    console.log(`doc: http://localhost:${PORT}/api/v1/docs`)
    console.log("ok -> ", PORT)
  });
}
nestApp();
