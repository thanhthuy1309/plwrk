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

class AdminController @Inject() (val messagesApi: MessagesApi,
    val ws: WSClient)(implicit ec: ExecutionContext) extends Controller with I18nSupport {
  
  @Inject
  private var userService:UserService = _
  
  @Inject
  private var deparmentService:DeparmentService = _
  
  @Inject
  private var roleService:RoleService = _
  
  private val userForm: Form[CreateUserForm] = Form(
    mapping(
      "email" -> nonEmptyText,
      "name" -> nonEmptyText,
      "fullName" -> text,
      "dateBorn" -> date,
      "roleId" -> text,
      "passWord" -> text,
      "emailUpper" -> text,
      "deparmentId" -> number
      )(CreateUserForm.apply)(CreateUserForm.unapply))
      
  val mapMenu = Map("listUser" -> "List User","createUser" -> "CreateUser")
 
  def listUser() = Action {
    val users : List[User] = userService.findUserAll
    
    Ok(views.html.admin_list(users))
  }
  
  
  def updateUser(email : String) = Action {
    var deparments : List[Deparment] = deparmentService.findDeparmentAll
    var roles : List[Role] = roleService.findRoleAll
    var user : User = userService.findUserByEmail(email)
    var form:CreateUserForm = new CreateUserForm(
        user.email,
        user.name,
        user.fullName,
        user.dateBorn,
        user.role.roleId,
        user.passWord,
        user.emailUpper,
        user.deparment.deparmentId
    )
    Ok(views.html.admin_update(email, userForm.fill(form), deparments, roles))
  }
  
  def adminUpdateUserPost(email : String) = Action {implicit request =>
    var deparments : List[Deparment] = deparmentService.findDeparmentAll
    var roles : List[Role] = roleService.findRoleAll
    val newUserForm = userForm.bindFromRequest()
    // Bind the form first, then fold the result, passing a function to handle errors, and a function to handle succes.
    newUserForm.bindFromRequest.fold(
      // The error function. We return the index page with the error form, which will render the errors.
      // We also wrap the result in a successful future, since this action is synchronous, but we're required to return
      // a future because the person creation function returns a future.
      errorForm => {
        var user : User = userService.findUserByEmail(email)
        var form:CreateUserForm = new CreateUserForm(
          user.email,
          user.name,
          user.fullName,
          user.dateBorn,
          user.role.roleId,
          user.passWord,
          user.emailUpper,
          user.deparment.deparmentId
          )
        BadRequest(views.html.admin_update(email, userForm.fill(form), deparments, roles))
      },
      // There were no errors in the from, so create the person.
      user => {
        // If successful, we simply redirect to the index page.
        var u: User = new User
        u.email = user.email
        u.name = user.name
        u.fullName = user.fullName
        u.dateBorn = user.dateBorn
        u.passWord = user.passWord
        u.emailUpper = user.emailUpper
        u.role = roleService.findRoleById(user.roleId)
        u.deparment = deparmentService.findDeparmentById(user.deparmentId)
        userService.updateUser(u)
        Redirect(routes.AdminController.listUser())
      })
  }
}

case class CreateUserForm(email: String, name: String, fullName: String, dateBorn: Date, roleId: String, passWord: String, emailUpper: String, deparmentId: Int)