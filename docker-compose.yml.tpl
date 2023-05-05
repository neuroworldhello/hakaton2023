version: '3'

services:
  db:
    restart: always
    image: postgres:12-alpine
    environment:
      POSTGRES_USER: "${POSTGRES_USER}"
      POSTGRES_PASSWORD: "${POSTGRES_PASSWORD}"
      POSTGRES_DB: "${POSTGRES_DB}"
    volumes:
      - "/etc/timezone:/etc/timezone:ro"
      - "/etc/localtime:/etc/localtime:ro"
      - /var/lib/postgresql:/var/lib/postgresql
    ports:
      - "5432:5432"

  backend:
    restart: always
    image: ${DOCKERHUB_REPOSITORY}/backend:latest
    volumes:
      - "/etc/timezone:/etc/timezone:ro"
      - "/etc/localtime:/etc/localtime:ro"
    environment:
      SPRING_DATASOURCE_URL: "jdbc:postgresql://db:5432/${POSTGRES_DB}"
      SPRING_DATASOURCE_USERNAME: "${POSTGRES_USER}"
      SPRING_DATASOURCE_PASSWORD: "${POSTGRES_PASSWORD}"
      SPRING_JPA_HIBERNATE_DDL_AUTO: update
      SPRING_JPA_PROPERTIES_HIBERNATE_FORMAT_SQL: "true"
      SPRING_JPA_SHOW_SQL: "true"
    ports:
      - "8080:8080"
    depends_on:
      - db

#  frontend:
#    restart: always
#    image: ${DOCKERHUB_REPOSITORY}/frontend:latest
#    ports:
#      - "3000:3000"

  nginx:
    restart: always
    image: nginx
    ports:
      - "80:80"
      - "443:443"
    depends_on:
      - backend
    volumes:
      - "/etc/timezone:/etc/timezone:ro"
      - "/etc/localtime:/etc/localtime:ro"
      - /etc/letsencrypt:/etc/letsencrypt
      - ./nginx.conf:/etc/nginx/conf.d/default.conf