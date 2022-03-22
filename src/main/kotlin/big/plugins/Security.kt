package big.plugins

import big.utils.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.application.*


fun Application.configureSecurity() {

    authentication {
        jwt {
            realm = jwtRealm    //not sure its purpose, from generated code

            verifier(
                jwtVerifier
            )
            validate { credential ->
                UserIdPrincipal(credential.payload.getClaim("name").asString())
            }
        }
    }

}
