# Stefan Brandmair - Keycloak Demo

It follows those two Quarkus tutorials

- [Security Keycloak Authorization](https://quarkus.io/guides/security-keycloak-authorization)
- [Security Openid Connect](https://quarkus.io/guides/security-openid-connect)

Futhermore, it uses a modified version of [this `docker-compose.yml` file](https://github.com/1920-5bhif-nvs/referate-nvs-5bhif/blob/master/AhammerBrandmair_Security/Keycloak/docker-compose.yml).

## Quarkus Keycloak

Regarding anonymous or public access, this is a relevant Quarkus issue https://github.com/quarkusio/quarkus/issues/6807

And here is a relevant comment https://github.com/quarkusio/quarkus/issues/2231#issuecomment-597147382

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

- @RolesAllowed("role") does work, however, it first checks with Keycloak. So, if you want to do that RBAC stuff on the Quarkus side, you have to change the protected resources in Keycloak.
```diff
      "resources": [
        {
          "name": "User Resource",
          "ownerManagedAccess": false,
          "attributes": {},
          "_id": "df1b74a9-3f10-499d-a581-368de48e512b",
          "uris": [
-            "/api/users/*"
+            "/*"
          ]
        },
```

## Keycloak Login

You need an access token to access most endpoints. To get the access token, you have to send a `POST` call to `<keycloak server url>/auth/realms/quarkus/protocol/openid-connect/token` with the correct parameters. This includes the `client-id` and `secret`.

(The alternative is somehow using the Keycloak login page and possibly a different auth flow. I wouldn't know about that though, the tutorials are pretty bad.)

Now, since the `client-id` and `secret` aren't supposed to be in the frontend's source code, we have to [do that API call from the backend](https://github.com/1920-5bhif-nvs/05-assignment-quarkus-jwt-stefnotch/blob/master/src/main/java/at/htl/service/LoginService.java). This also avoids CORS issues. Furthermore, it means that you don't have to expose your Keycloak to the public anymore, which may or may not be an advantage.


## Endpoints
A public list of people
```
http://localhost:8080/person
```

The administrator, is a protected route
```
http://localhost:8080/admin
```

Forwards stuff to the Keycloak login
```
http://localhost:8080/login
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

After starting the Quarkus server, the frontend still needs to be started.
```
cd web
npm i
npm run dev
```

And there you can login with 
- `alice`:`alice`
- `jdoe`:`jdoe`
- `admin`:`admin`
