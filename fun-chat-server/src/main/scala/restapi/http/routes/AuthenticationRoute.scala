package restapi.http.routes

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.model.headers.BasicHttpCredentials
import akka.http.scaladsl.server.{Directives, Route}
import core.authentication.AuthenticationService
import core.entities.UserSecret
import restapi.http.JsonSupport
import restapi.http.routes.support._

import scala.concurrent.ExecutionContext

class AuthenticationRoute(authService: AuthenticationService)(implicit ec: ExecutionContext, ac: ApiContext)
    extends Directives with SecuredAccessSupport with ContentExtractionSupport with JsonSupport {

  val route: Route =
    pathPrefix("auth") {
      path("signIn") {
        post {
          extractClientInfo { clientInfo =>
            extractCredentials {
              case Some(BasicHttpCredentials(username, password)) =>
                complete(authService.signIn(username, UserSecret(password), clientInfo))
              case _ => complete(StatusCodes.Unauthorized)
            }
          }
        }
      } ~
        path("signUp") {
          post {
            extractClientInfo { clientInfo =>
              extractCredentials {
                case Some(BasicHttpCredentials(username, password)) =>
                  complete(authService.signUp(username, UserSecret(password), clientInfo))
                case _ => complete(StatusCodes.Unauthorized)
              }
            }
          }
        } ~
        path("signOut") {
          securedAccess { ctx =>
            post {
              complete(authService.signOut(ctx.userId))
            }
          }
        }
    } ~
      path("credentials") {
        securedAccess { ctx =>
          patch {
            entity(as[String]) { password =>
              complete(authService.updateCredentials(ctx.userId, UserSecret(password)))
            }
          }
        }
      }

}
