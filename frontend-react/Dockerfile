FROM node:16.3.0-alpine

WORKDIR /user/src/app

COPY ./package.json ./

COPY ./package-lock.json ./

RUN npm install --legacy-peer-deps

COPY . .

EXPOSE 3000

ENTRYPOINT [ "npm" ]

CMD ["start"]