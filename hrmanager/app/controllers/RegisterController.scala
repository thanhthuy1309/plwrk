package controllers
import play.api.mvc.{Action, Controller}
import service.UserService

import javax.inject.{ Inject, Named }
import entity.User
import java.util.List
import java.util.Date
import entity.Role
import entity.Deparment
import play.api.data.Form
import service.DeparmentService
import service.RoleService
import play.api.data.Forms.{ mapping, longNumber, nonEmptyText, number, date, text,email}
import play.api.i18n.I18nSupport
import play.api.libs.ws.WSClient
import play.api.i18n.MessagesApi
import scala.concurrent.ExecutionContext
import entity.User
import constants._
import forms._
import entity.User
import play.api.cache.CacheApi

class RegisterController @Inject() (val messagesApi: MessagesApi,
    val ws: WSClient,
    val cache: CacheApi)(implicit ec: ExecutionContext) extends Controller with I18nSupport {
    
     @Inject
     private var userService:UserService = _
  
     @Inject
     private var deparmentService:DeparmentService = _
     
    private val userForm: Form[UpdateUserForm] = Form(
    mapping(
      "email" -> email.verifying("register.error.email",checkMailExist(_)),
      "name" -> nonEmptyText,
      "fullName" -> nonEmptyText,
      "dateBorn" -> date("yyyy-MM-dd").verifying("date.error.beforenow",_.before(new Date())),
      "roleId" -> text,
      "passWord" -> nonEmptyText,
      "emailUpper" -> text,
      "deparmentId" -> number
      )(UpdateUserForm.apply)(UpdateUserForm.unapply))
      

  
    def register = Action { implicit request =>
      var deparments : List[Deparment] = deparmentService.findDeparmentAll
      var users : List[User] = userService.findUserAll
      Ok(views.html.register(userForm,deparments,users))
    }
    
   def registerUserPost() = Action { implicit request =>
    var deparments : List[Deparment] = deparmentService.findDeparmentAll
    var users : List[User] = userService.findUserAll
    val newUserForm = userForm.bindFromRequest()
    newUserForm.bindFromRequest.fold(
    errorForm => {
        BadRequest(views.html.register(errorForm,deparments,users))
      },
    user => {
       // If successful, we simply redirect to the index page.
        userService.registerUser(user)
        var info: User = userService.findUserByEmail(user.email)
        info.role.roleId match {
            case CommonConstant.ROLE_USER => Redirect("/employee/list/0").withSession(request.session + ("email" -> user.email) + ("roleId" -> info.role.roleId))
            case _ => Redirect("/admin/list").withSession(request.session + ("email" -> user.email) + ("roleId" -> info.role.roleId))
          }
      })
  }
   private def checkMailExist(email: String) : Boolean = {
     var isExistUser: User = userService.findUserByEmail(email)
     if (isExistUser.email != null) {
       false
     } else {
       true
     }
   }
}