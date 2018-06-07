package app

import org.pac4j.core.authorization.authorizer.RequireAnyRoleAuthorizer
import org.pac4j.core.config.Config
import org.pac4j.core.config.ConfigFactory
import org.pac4j.http.client.direct.ParameterClient
import org.pac4j.jwt.config.signature.SecretSignatureConfiguration
import org.pac4j.jwt.credentials.authenticator.JwtAuthenticator
import org.pac4j.core.client.direct.AnonymousClient
import org.pac4j.core.client.Clients
import org.pac4j.core.profile.CommonProfile


class TestConfigFactory: ConfigFactory {
    lateinit var Salt:String
    fun init(salt:String):Boolean{
        this.Salt = salt
        return true
    }

    override fun build(vararg parameters: Any?): Config {
        // REST authent with JWT for a token passed in the url as the token parameter
        val parameterClient = ParameterClient("token", JwtAuthenticator(SecretSignatureConfiguration(Salt)))
        parameterClient.setSupportGetRequest(true)
        parameterClient.setSupportPostRequest(false)
        val clients = Clients("http://localhost:4567/callback", parameterClient, AnonymousClient())

        val config =Config(clients)
        config.addAuthorizer("admin", RequireAnyRoleAuthorizer<CommonProfile>("ROLE_ADMIN"))

        return config

    }

}