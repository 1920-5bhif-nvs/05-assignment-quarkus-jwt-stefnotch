# Stefan Brandmair - Keycloak Demo

It follows those two Quarkus tutorials

- [Security Keycloak Authorization](https://quarkus.io/guides/security-keycloak-authorization)
- [Security Openid Connect](https://quarkus.io/guides/security-openid-connect)



Futhermore, it uses a modified version of [this `docker-compose.yml` file](https://github.com/1920-5bhif-nvs/referate-nvs-5bhif/blob/master/AhammerBrandmair_Security/Keycloak/docker-compose.yml).





This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```
./mvnw quarkus:dev
```

## Packaging and running the application

The application is packageable using `./mvnw package`.
It produces the executable `keycloak-demo-1.0.0-SNAPSHOT-runner.jar` file in `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

The application is now runnable using `java -jar target/keycloak-demo-1.0.0-SNAPSHOT-runner.jar`.

## Creating a native executable

You can create a native executable using: `./mvnw package -Pnative`.

Or you can use Docker to build the native executable using: `./mvnw package -Pnative -Dquarkus.native.container-build=true`.

You can then execute your binary: `./target/keycloak-demo-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image-guide .