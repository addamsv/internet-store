# ИНТЕРНЕТ МАГАЗИН

<br>

# КРАТКО О ПРИЛОЖЕНИИ:

## Страница доступного списка товаров

<img src="./.github/projectDescription/images/app.png">

## О товаре подробнее

<img src="./.github/projectDescription/images/bookDescription.png" width="300px">

- возможность отправки комментария (только для авторизированных пользователей)
- список комментариев
- рекомендованные товары

## Страница профиля пользователя

(только для авторизированных пользователей)

<img src="./.github/projectDescription/images/userProfile.png" width="300px">

- возможность изменять и сохранять поля профиля

## Различные темы и интернационализация (RU, EN)

<img src="./.github/projectDescription/images/darkBlue.png" width="100px"><img src="./.github/projectDescription/images/dark.png" width="100px"><img src="./.github/projectDescription/images/lightBlue.png" width="100px">

## Возможность установить Progressive Web App (WPA)

<img src="./.github/projectDescription/images/wpa.jpg" width="394px" height="250px">

<br><br>

## OAUTH2 JWT и Basic аутентификация

<img src="./.github/projectDescription/images/basicAuth.png" width="300px">

# Технологический стек

## FRONT:

Typescript, React, Thunk (async), RTK query, Webpack, FSD, Redux Toolkit;

## Tests

jest, integration, ui, screenshot tests, ts lint, style lint;

## Storybook:

<img src="./.github/projectDescription/images/storybook.png">

## CI/CD

## BACK:

Dev: Nodejs json-server (смотри папку mock-server)
<br>
Prod: Java Spring Boot + (H2 | postgresql | mysql...) (смотри sb-lab-api repo)

<br>

# Установка приложения

### разрабатывалось с node version: v16.14.0

clone the repo then (sudo)

```bash
npm install
```

build storybook: (sudo)

```bash
npm run sb:build
```

<!-- в node_modules/entities удалил поля "module" и "exports" и всё заработало -->

# Запуск приложения:

```bash
npm start
```

# Установка и Запуск JSON сервера:

```bash
# clone the repo
git clone --single-branch -b mock-server https://github.com/addamsv/internet-store.git

# install all deps
npm install

# launch the server:
npm start
```

# Запуск Storybook:

```bash
npm run sb
```

# Запуск ТEST:

```bash
npm run lint
npm run stylelint
npm run test
```

# Loki (screenshot test):

### убедиться, что Docker и Storybook запущен и подключен инет

```bash
npm run loki
```

### если всё устраивает:

```bash
npm run loki:ok
```
