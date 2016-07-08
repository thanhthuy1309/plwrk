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
import service.StatusService

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
  
  @Inject
  private var statusService:StatusService = _
 
  def listJobApplication(status:Int) = Action {implicit request =>
     var list : List[ListJobApplication] = employeeService.findJobApplitationByEmailStatus(request.session.get("email").get, status)
     Ok(views.html.jobapplication_list(list,request.session.get("email").get,request.session.get("roleId").get, status))
   }
  
  def loadJobApplicationInfo(id: Int, status:Int) = Action {implicit request =>
     var info: EmployeeApply = employeeService.findEmployeeApplyById(id)
     Ok(views.html.jobapplication_info(info,request.session.get("email").get,request.session.get("roleId").get,status))
  }
  
  def approvalJobApplication(id: Int, status:Int) = Action {implicit request =>
     var info: EmployeeApply = employeeService.findEmployeeApplyById(id)
     info.status = statusService.findStatusById(CommonConstant.STATUS_APPROVAL)
     employeeService.updateEmployeeApply(info)
     Redirect(routes.AdminJobApplicationController.listJobApplication(status))
  }
  
   def approvalNotJobApplication(id: Int, status:Int) = Action {implicit request =>
     var info: EmployeeApply = employeeService.findEmployeeApplyById(id)
     info.status = statusService.findStatusById(CommonConstant.STATUS_CANCEL_APPROVAL)
     employeeService.updateEmployeeApply(info)
     Redirect(routes.AdminJobApplicationController.listJobApplication(status))
  }
   
   def managerJobApplicationList() = Action {implicit request =>
     var list : List[ListAllJobApplication] = employeeService.findJobApplitationAllList()
     Ok(views.html.admin_jobapplication_list(list,request.session.get("email").get,request.session.get("roleId").get))
  }
   
   def infoJobApplicationInfo(id: Int) = Action {implicit request =>
     var info: EmployeeApply = employeeService.findEmployeeApplyById(id)
     Ok(views.html.admin_jobapplication_info(info,request.session.get("email").get,request.session.get("roleId").get))
  }
   
  def deleteApply(id: Int)= Action { implicit request =>
    employeeService.deleteEmployeeApplyById(id)
    Redirect(routes.AdminJobApplicationController.managerJobApplicationList())
  }
}