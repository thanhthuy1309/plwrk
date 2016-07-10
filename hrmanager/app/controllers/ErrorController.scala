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
import configs._

class ErrorController @Inject() (val messagesApi: MessagesApi,
    val ws: WSClient,
    val cache: CacheApi)(implicit ec: ExecutionContext) extends Controller with I18nSupport {

  def error_first = Action { implicit request =>
    Ok(views.html.error_first())
  }
  
  def error_after() = Authenticated { implicit request =>
    Ok(views.html.error_after(request.session.get("email").get,request.session.get("roleId").get))
  }

}