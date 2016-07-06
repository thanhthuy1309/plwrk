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

class LoginController @Inject() (val messagesApi: MessagesApi,
    val ws: WSClient,
    val cache: CacheApi)(implicit ec: ExecutionContext) extends Controller with I18nSupport {

  @Inject
  private var userService: UserService = _

  def home = Action {
    Ok(views.html.home())
  }

  def loginHome = Action {
    Ok(views.html.login())
  }

  def loginByGoogle = Action {
    Redirect(GoogleConstant.GOOGLE_URL_LOGIN)
  }

  def login(state: String, code: String) = Action {
    if (cache.get("accessToken") == None) {
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
        if(user.email != null) {
          cache.set(email, email)
        } else {
          var infoGoogle:UserGoogleForm = new UserGoogleForm(email,name)
          if (userService.saveByGoogle(infoGoogle) == 1) {
            cache.set(email, email)
          } else {
            Ok("Man hinh loi")
          }
        }
        Ok("vao man hinh quan ly"+cache.get(email).get)
      }

    }
    Ok("vao man hinh quan ly")
  }

}