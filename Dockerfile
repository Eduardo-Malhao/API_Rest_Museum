# 1º estágio
# chamado `build-image`, imagem JDK temporária para compilar o código
FROM maven:3-openjdk-17 AS build-image
# define o diretório de trabalho
WORKDIR /to-build-app
# copia o código-fonte para o diretório de trabalho
COPY pom.xml .
# executa o comando de empacotamento do maven
RUN dependency:go-offline

COPY src ./src

RUN mvn package -DskipTests

# 2º estágio
# com imagem JRE, limpa e mais leve
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app
# copia o jar gerado no primeiro estágio para o diretório de trabalho
COPY --from=build-image /to-build-image-app/target/your-application.jar .
# expõe a porta 8080
EXPOSE 8080
# define o comando de execução da aplicação
ENTRYPOINT ["java", "-jar", "/app.jar"]