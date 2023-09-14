FROM eclipse-temurin
WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN sed -i 's/\r$//' mvnw
RUN chmod +x mvnw
RUN /bin/sh mvnw dependency:go-offline

COPY src ./src

CMD ["./mvnw", "spring-boot:run"]