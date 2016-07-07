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
    
    private val userForm: Form[UpdateUserForm] = Form(
    mapping(
      "email" -> email,
      "name" -> nonEmptyText,
      "fullName" -> nonEmptyText,
      "dateBorn" -> date,
      "roleId" -> text,
      "passWord" -> nonEmptyText,
      "emailUpper" -> text,
      "deparmentId" -> number
      )(UpdateUserForm.apply)(UpdateUserForm.unapply))
      
     @Inject
     private var userService:UserService = _
  
     @Inject
     private var deparmentService:DeparmentService = _
  
    def register = Action { implicit request =>
      var deparments : List[Deparment] = deparmentService.findDeparmentAll
      var users : List[User] = userService.findUserAll
      Ok(views.html.register(userForm,deparments,users))
  }
    
   def registerUserPost() = Action {implicit request =>
    var deparments : List[Deparment] = deparmentService.findDeparmentAll
    var users : List[User] = userService.findUserAll
    val newUserForm = userForm.bindFromRequest()
    // Bind the form first, then fold the result, passing a function to handle errors, and a function to handle succes.
    newUserForm.bindFromRequest.fold(
      // The error function. We return the index page with the error form, which will render the errors.
      // We also wrap the result in a successful future, since this action is synchronous, but we're required to return
      // a future because the person creation function returns a future.
      errorForm => {
        BadRequest(views.html.register(errorForm,deparments,users))
      },
      // There were no errors in the from, so create the person.
      user => {
        var isExistUser: User = userService.findUserByEmail(user.email)
        if (isExistUser.email != null) {
          Redirect(routes.ErrorController.error_first()).flashing("errormessage" -> ("This email is exist"))
        } else {
        	// If successful, we simply redirect to the index page.
        	userService.registerUser(user)
        	var info: User = userService.findUserByEmail(user.email)
        	Redirect(routes.LoginController.index()).withSession(request.session + ("email" -> info.email) + ("roleId" -> info.role.roleId))
        }
      })
  }
}