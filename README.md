# Stefan Brandmair - Keycloak Demo

It follows those two Quarkus tutorials

- [Security Keycloak Authorization](https://quarkus.io/guides/security-keycloak-authorization)
- [Security Openid Connect](https://quarkus.io/guides/security-openid-connect)

Futhermore, it uses a modified version of [this `docker-compose.yml` file](https://github.com/1920-5bhif-nvs/referate-nvs-5bhif/blob/master/AhammerBrandmair_Security/Keycloak/docker-compose.yml).

## Quarkus Keycloak

Regarding anonymous or public access, this is a relevant Quarkus issue https://github.com/quarkusio/quarkus/issues/6807

The status quo seems to be something like this
- By default, everything is protected

- Setting `quarkus.keycloak.policy-enforcer.enforcement-mode=PERMISSIVE` doesn't have an effect. That's a bug.

- @PermitAll allows all roles. It does not allow anonymous users. No idea if that's desired behavior.

- To make something public, you have to totally disable RBAC. e.g.
```
quarkus.keycloak.policy-enforcer.paths.Person.name=Person
quarkus.keycloak.policy-enforcer.paths.Person.path=/person
quarkus.keycloak.policy-enforcer.paths.Person.enforcement-mode=DISABLED
```

## Endpoints

```
http://localhost:8080/person
```

## Running the application in dev mode

If you're not on Windows, make sure to change the Keycloak server url in `application.properties`

Start the keycloak server.
```
docker-compose up -d
```

Make sure that the `quarkus-realm.json` has been imported.

You can then run your application in dev mode using
```
./mvnw quarkus:dev
```

## Packaging and running the application

The application is packageable using `./mvnw package`.
It produces the executable `keycloak-demo-1.0.0-SNAPSHOT-runner.jar` file in `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

The application is now runnable using `java -jar target/keycloak-demo-1.0.0-SNAPSHOT-runner.jar`.