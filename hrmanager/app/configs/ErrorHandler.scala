package configs

import play.api.http.HttpErrorHandler
import play.api.mvc._
import play.api.mvc.Results._
import scala.concurrent._
import javax.inject.Singleton;
import controllers.routes
import javax.inject.Inject
import play.api.i18n.MessagesApi
import service.UserService

@Singleton
class ErrorHandler @Inject() (val messagesApi: MessagesApi, userService: UserService) extends HttpErrorHandler {

  def onClientError(request: RequestHeader, statusCode: Int, message: String) = {
    if (request.session.get("email").isDefined) {
      Future.successful(
      Redirect(routes.ErrorController.error_after()).flashing("errormessage"->messagesApi.apply("url.error.notexist"))
      )
    } else {
      Future.successful(
      Redirect(routes.ErrorController.error_first()).flashing("errormessage"->messagesApi.apply("url.error.notexist"))
      )
    }
  }
  
  def onServerError(request: RequestHeader, exception: Throwable) = {
    if (request.session.get("email").isDefined) {
      if (userService.findUserAll.size() < 2) {
        Future.successful(
        Redirect(routes.ErrorController.error_after()).flashing("errormessage"-> (messagesApi.apply("error.user.database")))
        )
      } else {
        Future.successful(
        Redirect(routes.ErrorController.error_after()).flashing("errormessage"-> (messagesApi.apply("error.access.database") + exception.getMessage))
        )
      }
    } else {
      Future.successful(
      Redirect(routes.ErrorController.error_first()).flashing("errormessage"-> (messagesApi.apply("error.access.database") + exception.getMessage))
      )
    }
  }
}