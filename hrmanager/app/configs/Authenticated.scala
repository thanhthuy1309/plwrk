package configs

import play.api.mvc._
import Results._
import scala.concurrent.Future
import play.api.libs.concurrent.Akka
import controllers.routes

object Authenticated extends ActionBuilder[Request] {
  def invokeBlock[A](request: Request[A], block: (Request[A]) => Future[Result]) = {
    if (request.session.get("email").isDefined)
      block(request)
    else
      Future.successful(Redirect(routes.LoginController.loginHome()))

  }
}