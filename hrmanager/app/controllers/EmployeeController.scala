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
      "fullName" -> text,
      "emailEmployee" -> nonEmptyText,
      "emailManager" -> nonEmptyText,
      "deparmentid" -> number,
      "fromDate" -> date,
      "toDate" -> date,
      "submitDate" -> date,
      "reasonId" -> number,
      "statusId" -> number)(CreateEmployeeApplyForm.apply)(CreateEmployeeApplyForm.unapply))

  def emloyeeApplyGet() = Action { implicit request =>
    var user: User = userService.findUserByEmail(request.session.get("email").get)
    var deparments: List[Deparment] = deparmentService.findDeparmentAll
    val users: List[User] = userService.findUserSubtractEmail(request.session.get("email").get)
    var reasons: List[Reason] = reasonService.findReasonAll
    var statusList: List[entity.Status] = statusService.findStatusAll
    var form: CreateEmployeeApplyForm = new CreateEmployeeApplyForm(
      user.fullName,
      user.email,
      "",
      user.deparment.deparmentId,
      new Date(),
      new Date(),
      new Date(),
      1,
      1)
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
        employeeApplyService.save(employeeApply)
        Redirect(routes.AdminController.listUser())
      })
  }
  
  def notApproved() = Action {implicit request =>
    val employeeApplys : List[EmployeeApply] = employeeApplyService.findEmployeeApplyByStatus(1)
    Ok(views.html.employee_not_approved(employeeApplys,request.session.get("email").get,request.session.get("roleId").get))
  }
}

case class CreateEmployeeApplyForm(
  fullName: String,
  emailEmployee: String,
  emailManager: String,
  deparmentid: Int,
  fromDate: Date,
  toDate: Date,
  submitDate: Date,
  reasonId: Int,
  statusId: Int)