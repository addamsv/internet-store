import { NestFactory } from '@nestjs/core';
import { NestExpressApplication } from '@nestjs/platform-express';
import { DocumentBuilder, SwaggerModule } from '@nestjs/swagger';
// import * as path from 'path';
import { join } from 'path';

import { AppModule } from './app.module';
import { APP_DESCRIPTION, APP_NAME, APP_VERSION } from './common/constants';
// import { ValidationPipe } from './pipes/validation.pipe';

async function bootstrap() {
  const PORT = process.env.PORT || 5500;

  const app = await NestFactory.create<NestExpressApplication>(AppModule, {
    cors: true,
  });

  // app.useStaticAssets(path.resolve(__dirname, 'static'));

  app.useStaticAssets(join(__dirname, '..', 'static'));

  // -- Cors setup
  app.enableCors({
    origin: false, // Specify the allowed origins.  I'm setting false to allow requests from any origin
    // Find more configuration options here: https://github.com/expressjs/cors#configuration-options
  });

  const config = new DocumentBuilder()
    .setTitle(APP_NAME)
    .setDescription(APP_DESCRIPTION)
    .setVersion(APP_VERSION)
    // .addTag("Sergy Addam")
    // .setLicense("MIT", "")
    // .setTermsOfService("Terms of Service")
    // .addTag('REST API for Magaz')
    .addServer('http://localhost:5500', 'dev server')
    .addServer('http://localhost:5500', 'prod server')
    .addBearerAuth({ type: 'http', name: 'JWT', in: 'header' }) // The API will use Bearer Authentication
    .addBasicAuth({ type: 'http', name: 'basic', in: 'query' })
    .build();

  const document = SwaggerModule.createDocument(app, config);
  SwaggerModule.setup('/api/docs', app, document);

  // app.useGlobalGuards(JwtAuthGuard);
  // app.useGlobalPipes(new ValidationPipe());

  await app.listen(PORT, () => {
    // console.log(`example: http://localhost:${PORT}/api/v1/users`)
    // console.log(`doc: http://localhost:${PORT}/api/v1/docs`)
    // console.log("ok -> ", PORT)
    console.log(`https://jwt.io`);
    console.log(`https://draw.io`);
    console.log(`Started at http://localhost:${PORT}/api/docs`);
  });
  console.log(`${await app.getUrl()}`);
}

bootstrap();
