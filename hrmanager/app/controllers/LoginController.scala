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

class LoginController @Inject() (val messagesApi: MessagesApi,
    val ws: WSClient,
    val cache: CacheApi)(implicit ec: ExecutionContext) extends Controller with I18nSupport {

  @Inject
  private var userService: UserService = _

  private val loginForm: Form[UserLoginAccountForm] = Form(
    mapping(
      "email" -> email,
      "password" -> nonEmptyText)(UserLoginAccountForm.apply)(UserLoginAccountForm.unapply))

  def home = Action { implicit request =>
    Ok(views.html.home())
  }

  def loginHome = Action { implicit request =>
    Ok(views.html.login(loginForm))
  }

  def index = Action { implicit request =>
    Ok(views.html.index(request.session.get("email").get,request.session.get("roleId").get))
  }
  
  def loginByGoogle = Action {implicit request =>
    Redirect(GoogleConstant.GOOGLE_URL_LOGIN)
  }

  def login(state: String, code: String) = Action { implicit request =>
    var email: String = null
    var name: String = null
    var postBody = StringBuilder.newBuilder
    postBody.append("code=" + code + "&client_id=")
    postBody.append(GoogleConstant.CLIENT_ID)
    postBody.append("&client_secret=" + GoogleConstant.CLIENT_SECRET)
    postBody.append("&redirect_uri=" + GoogleConstant.REDIRECT_URL)
    postBody.append("&grant_type=authorization_code")

    val body = ws.url(GoogleConstant.GOOGLE_URL_TOKEN).withHeaders("Content-Type" -> "application/x-www-form-urlencoded").post(postBody.toString())

    Await.result(body, GoogleConstant.TIMEOUT)
    if (body.value != None) {
      val accessJson = body.value.get.get.json
      var jsonToken = accessJson.\("access_token").get.toString()
      val accessToken = StringUtils.deleteStrip(jsonToken.toString)
      cache.set("accessToken", accessToken)

      val getOauth = ws.url(GoogleConstant.GOOGLE_URL_OAUTH + accessToken)
        .withHeaders("Content-Type" -> "application/x-www-form-urlencoded").get()
      Await.result(getOauth, GoogleConstant.TIMEOUT)

      if (getOauth.value != None) {
        var result = getOauth.value.get.get.json
        email = StringUtils.deleteStrip((result.\("email").get.toString()).toString())
        name = StringUtils.deleteStrip((result.\("name").get.toString()).toString())
      }
    }

    if (email != null) {
      var user: User = userService.findUserByEmail(email)
      var roleId:String = null
      if (user.email != null) {
        roleId =  user.role.roleId
      } else {
        var infoGoogle: UserGoogleForm = new UserGoogleForm(email, name)
        if (userService.saveByGoogle(infoGoogle) == 1) {
           var info: User = userService.findUserByEmail(email)
           roleId =  info.role.roleId
        } else {
          Ok("Man hinh loi")
        }
      }
      //cache.get[String](email).get)
      Redirect("/index").withSession(request.session + ("email" -> email) + ("roleId" -> roleId))
    } else {
      Ok("loi")
    }
  }

  def postLogin = Action { implicit request =>
    val login = loginForm.bindFromRequest()
    login.bindFromRequest.fold(
      errorForm => {
        BadRequest(views.html.login(errorForm))
      },
      person => {
        var result: Int = userService.serviceLoginAccount(person)
        // user not exist
        if (result == 1) {
          Redirect("/loginHome").flashing("userNotExist" -> (person.email + " not exist"))
        } else if (result == 2) {
          var user:User = userService.findUserByEmail(person.email)
          Redirect("/employee/myLeaveOfAbsence").withSession(request.session + ("email" -> user.email) + ("roleId" -> user.role.roleId))
        } else {
          Ok("error")
        }
        
      })
  }

}