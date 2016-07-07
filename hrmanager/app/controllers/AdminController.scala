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
import play.api.data.Forms.{ mapping, longNumber, nonEmptyText, number, date, text}
import play.api.i18n.I18nSupport
import play.api.libs.ws.WSClient
import play.api.i18n.MessagesApi
import scala.concurrent.ExecutionContext
import entity.User
import constants._
import forms._
import entity.User

class AdminController @Inject() (val messagesApi: MessagesApi,
    val ws: WSClient)(implicit ec: ExecutionContext) extends Controller with I18nSupport {
  
  @Inject
  private var userService:UserService = _
  
  @Inject
  private var deparmentService:DeparmentService = _
  
  @Inject
  private var roleService:RoleService = _
  
  private val userForm: Form[UpdateUserForm] = Form(
    mapping(
      "email" -> nonEmptyText,
      "name" -> nonEmptyText,
      "fullName" -> nonEmptyText,
      "dateBorn" -> date,
      "roleId" -> text,
      "passWord" -> nonEmptyText,
      "emailUpper" -> text,
      "deparmentId" -> number
      )(UpdateUserForm.apply)(UpdateUserForm.unapply))
      
  def listUser() = Action {implicit request =>
    val users : List[User] = userService.findUserSubtractEmail(request.session.get("email").get)
    Ok(views.html.admin_list(users,request.session.get("email").get,request.session.get("roleId").get))
  }
  
  
  def updateUser(email : String) = Action { implicit request =>
    var deparments : List[Deparment] = deparmentService.findDeparmentAll
    var roles : List[Role] = roleService.findRoleAll
    var supList : List[User] = userService.findUserSubtractEmail(email)
    var form: UpdateUserForm = setInfoUser(email, deparments,roles)
    Ok(views.html.admin_update(email, userForm.fill(form), deparments, roles,request.session.get("email").get,request.session.get("roleId").get,CommonConstant.MODE_LIST_SCREEN,supList))
  }
  
  def adminUpdateUserPost(email : String,mode :String) = Action {implicit request =>
    var deparments : List[Deparment] = deparmentService.findDeparmentAll
    var roles : List[Role] = roleService.findRoleAll
    var supList : List[User] = userService.findUserSubtractEmail(email)
    val newUserForm = userForm.bindFromRequest()
    // Bind the form first, then fold the result, passing a function to handle errors, and a function to handle succes.
    newUserForm.bindFromRequest.fold(
      // The error function. We return the index page with the error form, which will render the errors.
      // We also wrap the result in a successful future, since this action is synchronous, but we're required to return
      // a future because the person creation function returns a future.
      errorForm => {
        BadRequest(views.html.admin_update(email, errorForm, deparments, roles,request.session.get("email").get, request.session.get("roleId").get,mode,supList))
      },
      // There were no errors in the from, so create the person.
      user => {
        // If successful, we simply redirect to the index page.
        userService.updateUser(user)
        if(mode == CommonConstant.MODE_LIST_SCREEN) {
        	Redirect(routes.AdminController.listUser())
        } else {
          Redirect(routes.LoginController.index())
        }
      })
  }
  
  def profile = Action { implicit request =>
    var deparments : List[Deparment] = deparmentService.findDeparmentAll
    var roles : List[Role] = roleService.findRoleAll
    var supList : List[User] = userService.findUserSubtractEmail(request.session.get("email").get)
    var form: UpdateUserForm = setInfoUser(request.session.get("email").get, deparments,roles)
    Ok(views.html.admin_update(request.session.get("email").get, userForm.fill(form), deparments, roles,request.session.get("email").get,request.session.get("roleId").get,CommonConstant.MODE_LIST_OTHERS,supList))
  }
  
  private def setInfoUser(email: String, deparments: List[Deparment],roles : List[Role]): UpdateUserForm = {
    var user : User = userService.findUserByEmail(email)
    var form:UpdateUserForm = new UpdateUserForm(
        user.email,
        user.name,
        user.fullName,
        user.dateBorn,
        user.role.roleId,
        user.passWord,
        user.emailUpper,
        user.deparment.deparmentId
    )
    form
  }
}