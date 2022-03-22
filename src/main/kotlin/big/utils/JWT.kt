package big.utils

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.typesafe.config.ConfigFactory
import io.ktor.server.config.*
import java.util.*

private val appConfig = HoconApplicationConfig(ConfigFactory.load())

private val jwtSecret = appConfig.property("jwt.secret").getString()
private val jwtIssuer = appConfig.property("jwt.issuer").getString()
private val jwtAudience = appConfig.property("jwt.audience").getString()
val jwtRealm  =  appConfig.property("jwt.realm").getString()

private const val validityInMs = 36_000_00 * 1     // 1 hour

fun createJwtToken(email: String): String? {
    return JWT.create()
    .withAudience(jwtAudience)
    .withIssuer(jwtIssuer)
    .withClaim("email", email)
    .withExpiresAt(Date(System.currentTimeMillis() + validityInMs))
    .sign(Algorithm.HMAC256(jwtSecret))
}

val jwtVerifier: JWTVerifier = JWT
    .require(Algorithm.HMAC256(jwtSecret))
    .withAudience(jwtAudience)
    .withIssuer(jwtIssuer)
    .build()