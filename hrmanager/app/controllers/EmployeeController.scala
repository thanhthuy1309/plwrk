package controllers

import service.UserService

import play.api.mvc.{ Action, Controller }
import javax.inject.{ Inject, Named }
import play.api.data.Forms.{ mapping, longNumber, nonEmptyText, number, date, text }

import play.api.i18n.I18nSupport
import play.api.libs.ws.WSClient
import play.api.i18n.MessagesApi
import scala.concurrent.ExecutionContext
import java.util.Date
import entity.User
import play.api.data.Form
import entity.Deparment
import service.DeparmentService
import java.util.List
import service.EmployeeApplyService
import service.ReasonService
import service.StatusService
import entity.Reason
import entity.Status
import entity.EmployeeApply
import entity.Status


class EmployeeController @Inject() (val messagesApi: MessagesApi,
    val ws: WSClient)(implicit ec: ExecutionContext) extends Controller with I18nSupport {

  @Inject
  private var employeeApplyService: EmployeeApplyService = _

  @Inject
  private var userService: UserService = _

  @Inject
  private var deparmentService: DeparmentService = _
  
  @Inject
  private var reasonService:ReasonService = _
  
  @Inject
  private var statusService:StatusService = _

  private val employeeApplyForm: Form[CreateEmployeeApplyForm] = Form(
    mapping(
      "id" -> number,
      "fullName" -> text,
      "emailEmployee" -> nonEmptyText,
      "emailManager" -> nonEmptyText,
      "deparmentid" -> number,
      "fromDate" -> date,
      "toDate" -> date,
      "submitDate" -> date,
      "reasonId" -> number,
      "statusId" -> number)(CreateEmployeeApplyForm.apply)(CreateEmployeeApplyForm.unapply))

  def emloyeeApplyGet(idValue: Int) = Action { implicit request =>
    var user: User = userService.findUserByEmail(request.session.get("email").get)
    var deparments: List[Deparment] = deparmentService.findDeparmentAll
    val users: List[User] = userService.findUserSubtractEmail(request.session.get("email").get)
    var reasons: List[Reason] = reasonService.findReasonAll
    var statusList: List[entity.Status] = statusService.findStatusAll
    
    var id = 0
    var fullName = user.fullName
    var emailEmployee = user.email
    var emailManager = ""
    var deparmentid = user.deparment.deparmentId
    var fromDate = new Date()
    var toDate = new Date()
    var submitDate = new Date()
    var reasonId = 1
    var statusId = 1
    
    if (idValue != null) {
    	var emloyeeApply: EmployeeApply = employeeApplyService.findEmployeeApplyById(id)
      id = emloyeeApply.id
      emailManager = emloyeeApply.emailManager.email
      deparmentid = emloyeeApply.deparment.deparmentId
      fromDate = emloyeeApply.fromDate
      toDate = emloyeeApply.toDate
      submitDate = emloyeeApply.submitDate
      reasonId = emloyeeApply.reason.reasonId
      statusId = emloyeeApply.status.statusId
    }

    var form: CreateEmployeeApplyForm = new CreateEmployeeApplyForm(
      id,
      fullName,
      emailEmployee,
      emailManager,
      deparmentid,
      fromDate,
      toDate,
      submitDate,
      reasonId,
      statusId)
    
    Ok(views.html.employee_apply(
      employeeApplyForm.fill(form),
      deparments,
      users,
      reasons,
      request.session.get("email").get,
      request.session.get("roleId").get))
  }

  def emloyeeApplyPost() = Action { implicit request =>
    val newEmployeeApplyForm = employeeApplyForm.bindFromRequest()
    newEmployeeApplyForm.bindFromRequest.fold(
      errorForm => {
        val users: List[User] = userService.findUserSubtractEmail(request.session.get("email").get)
        var deparments: List[Deparment] = deparmentService.findDeparmentAll
        var reasons: List[Reason] = reasonService.findReasonAll
        BadRequest(views.html.employee_apply(errorForm, deparments, users, reasons, request.session.get("email").get, request.session.get("roleId").get))
      },
      employeeApply => {
        if (employeeApply.id != 0) {
          employeeApplyService.updateEmployeeApply(employeeApply)
        } else {
        	employeeApplyService.save(employeeApply)
        }
        Redirect(routes.AdminController.listUser())
      })
  }
  
  def notApproved() = Action {implicit request =>
    val employeeApplys : List[EmployeeApply] = employeeApplyService.findEmployeeApplyByStatus(request.session.get("email").get, 1)
    Ok(views.html.employee_not_approved(employeeApplys,request.session.get("email").get,request.session.get("roleId").get))
  }
  

//  def deleteApply(id: Int)= Action { implicit request =>
//    employeeApplyService.deleteEmployeeApplyById(id)
//    Ok(views.html.)
//  }
  
//  def myLeaveOfAbsence()= Action {implicit request =>
//    val employeeApplys : List[EmployeeApply] = employeeApplyService.findEmployeeApplyByStatus(request.session.get("email").get, 1)
//    Ok(views.html.employee(employeeApplys,request.session.get("email").get,request.session.get("roleId").get))
//  }
}

case class CreateEmployeeApplyForm(
  id: Int,
  fullName: String,
  emailEmployee: String,
  emailManager: String,
  deparmentid: Int,
  fromDate: Date,
  toDate: Date,
  submitDate: Date,
  reasonId: Int,
  statusId: Int)