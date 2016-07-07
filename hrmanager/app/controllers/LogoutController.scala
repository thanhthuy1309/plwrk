package controllers

import play.api._
import play.api.mvc._
import play.api.i18n.I18nSupport
import play.api.libs.ws.WSClient
import play.api.cache.CacheApi
import play.api.i18n.MessagesApi
import javax.inject.Inject
import play.api.mvc.Controller
import scala.concurrent.ExecutionContext
import constants.GoogleConstant
import scala.concurrent.Await
import utils.StringUtils
import service._
import entity.User
import forms.UserGoogleForm
import forms.UserLoginAccountForm
import play.api.data.Form
import play.api.data.Forms.{ mapping, longNumber, nonEmptyText, number, email }

class LogoutController @Inject() (val messagesApi: MessagesApi,
    val ws: WSClient,
    val cache: CacheApi)(implicit ec: ExecutionContext) extends Controller with I18nSupport {

  def logout = Action { implicit request =>
    request.session.-("email")
    request.session.-("roleId")
    Ok(views.html.home())
  }

}