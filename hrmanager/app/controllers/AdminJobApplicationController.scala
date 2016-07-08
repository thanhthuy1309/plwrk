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
import service.EmployeeApplyService
import entity.EmployeeApply

class AdminJobApplicationController @Inject() (val messagesApi: MessagesApi,
    val ws: WSClient)(implicit ec: ExecutionContext) extends Controller with I18nSupport {
  
  @Inject
  private var userService:UserService = _
  
  @Inject
  private var deparmentService:DeparmentService = _
  
  @Inject
  private var roleService:RoleService = _
  
  @Inject
  private var employeeService:EmployeeApplyService = _
 
  def listJobApplication(status:Int) = Action {implicit request =>
     var list : List[ListJobApplication] = employeeService.findJobApplitationByEmailStatus(request.session.get("email").get, status)
     Ok(views.html.jobapplication_list(list,request.session.get("email").get,request.session.get("roleId").get, status))
   }
  
  def loadJobApplicationInfo(id: Int, status:Int) = Action {implicit request =>
     var info: EmployeeApply = employeeService.findEmployeeApplyById(id)
     Ok(views.html.jobapplication_info(info,request.session.get("email").get,request.session.get("roleId").get))
  }
  
  def approvalJobApplication(id: Int, status:Int) = Action {implicit request =>
     var info: EmployeeApply = employeeService.findEmployeeApplyById(id)
     Redirect(routes.AdminJobApplicationController.listJobApplication(status))
  }
}