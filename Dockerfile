FROM node

WORKDIR /app

COPY package*.json ./

RUN npm install

COPY . .

COPY ./dist ./dist

ENV PORT 5500

EXPOSE $PORT

CMD ["npm", "run", "start:dev"]

# FROM node:12.13-alpine

# WORKDIR /app

# COPY package*.json ./

# RUN npm install

# COPY . .

# copy ./dist ./dist

# CMD ["npm", "run", "start:dev"]
