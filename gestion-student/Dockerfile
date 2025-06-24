### STAGE 1: Build Angular ###
FROM node:18-alpine AS build
WORKDIR /usr/src/app
COPY package.json package-lock.json ./
RUN npm install
COPY . .
RUN npm run build

### STAGE 2: Run with Nginx ###
FROM nginx:1.17.1-alpine
COPY nginx.conf /etc/nginx/nginx.conf
COPY --from=build /usr/src/app/dist/gestion-etudiant/browser /usr/share/nginx/html


#WORKDIR /usr/src/app
#COPY package*.json ./
#RUN npm install
#COPY . .
#RUN npm run build
#
#### STAGE 2: Run Nginx ###
#FROM nginx:1.17.1-alpine
#COPY nginx.conf /etc/nginx/nginx.conf
#COPY --from=build /usr/src/app/dist/gestion-etudiant /usr/share/nginx/html
#EXPOSE 42000
#CMD ["nginx", "-g", "daemon off;"]
