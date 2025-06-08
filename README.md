# ИНТЕРНЕТ МАГАЗИН

<br>

## КРАТКО О ПРИЛОЖЕНИИ:

- возможность покупки через yookassa картой (тестируется)

## Страница доступного списка товаров

- возможность сортировки по фильтру: типу, бренду
- возможность сортировки по убыванию|возрастанию: дате публикации, заголовку...
- возможность поиска товара
- два вида отображения товара
- уведомления (для зарег-х пользователей)

<img src="./.github/projectDescription/images/app.png">

## О товаре подробнее

- универсальное описание, состоящее из блоков
- возможность отправки комментария (только для авторизированных пользователей)
- список комментариев
- рекомендованные товары

<br>

## OAUTH2 JWT и Basic аутентификация

<img src="./.github/projectDescription/images/basicAuth.png" width="300px">

- Authorization: роли пользователя (admin, user, manager) и разграничение доступа
- возможность использования как Basic так и JWT аутентификацию пользователя

<br>

## Страница профиля пользователя

(только для авторизированных пользователей)

- возможность изменять и сохранять поля профиля

<img src="./.github/projectDescription/images/userProfile.png" width="300px">

<br>

## Различные темы

- возможность легко добавить новые
- 5 тем

<img src="./.github/projectDescription/images/darkBlue.png" width="100px"><img src="./.github/projectDescription/images/dark.png" width="100px"><img src="./.github/projectDescription/images/lightBlue.png" width="100px">

<br>

## Интернационализация i18n

- RU
- EN
- возможность легко добавить другие локали

<br>

## Возможность установить Progressive Web App (WPA)

<img src="./.github/projectDescription/images/wpa.png" width="394px" height="250px">

<br><br>

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
