FROM node

USER node

COPY app-backbone/app /home/node/app

EXPOSE 3000

USER root
RUN chown -R node:node /usr/local/lib/node_modules /home/node/

#USER node
WORKDIR /home/node/app/

RUN npm install -g bower

#USER root
RUN chown node:node /usr/local/bin/bower

USER node
WORKDIR /home/node/app/

RUN npm install && bower install

