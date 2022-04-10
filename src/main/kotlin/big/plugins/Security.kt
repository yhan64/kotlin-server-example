package big.plugins

import big.utils.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.application.*


fun Application.configureSecurity() {

    authentication {
        jwt {
            realm = JWT.jwtRealm    //not sure its purpose, from generated code

            verifier(
                JWT.jwtVerifier
            )
            validate { credential ->
                UserIdPrincipal(credential.payload.getClaim("email").asString())
            }
        }
    }

}
