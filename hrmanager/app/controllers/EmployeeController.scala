package controllers

import java.util.Date
import java.util.List

import scala.concurrent.ExecutionContext

import com.google.inject.ImplementedBy

import constants.CommonConstant
import entity.Deparment
import entity.EmployeeApply
import entity.Reason
import entity.User
import javax.inject.Inject
import javax.persistence.Entity
import javax.persistence.Table
import play.api.data.Form
import play.api.data.Forms.date
import play.api.data.Forms.mapping
import play.api.data.Forms.nonEmptyText
import play.api.data.Forms.number
import play.api.data.Forms.text
import play.api.i18n.I18nSupport
import play.api.i18n.MessagesApi
import play.api.libs.ws.WSClient
import play.api.mvc.Action
import play.api.mvc.Controller
import service.DeparmentService
import service.EmployeeApplyService
import service.ReasonService
import service.StatusService
import service.UserService
import serviceImpl.EmployeeApplyServiceImpl
import serviceImpl.UserServiceImpl
import constants.CommonConstant
import play.api.Environment
import play.api.libs.mailer._
import forms._
import utils.StringUtils
import configs._

class EmployeeController @Inject() (val messagesApi: MessagesApi,
    val ws: WSClient, mailer: MailerClient, environment: Environment)(implicit ec: ExecutionContext) extends Controller with I18nSupport {

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
      "fromDate" -> date("yyyy-MM-dd").verifying("date.error.afternow",_.after(new Date())),
      "toDate" -> date("yyyy-MM-dd").verifying("date.error.afternow",_.after(new Date())),
      "submitDate" -> date("yyyy-MM-dd"),
      "reasonId" -> number,
      "statusId" -> number,
      "currentPage" -> number)(CreateEmployeeApplyForm.apply)(CreateEmployeeApplyForm.unapply).verifying("employee.error.datefromto", form => form.fromDate
          .before(form.toDate))
      )

  def emloyeeApplyGet(idValue: Int, currentDate: Int) = Authenticated { implicit request =>
    var user: User = userService.findUserByEmail(request.session.get("email").get)
    var deparments: List[Deparment] = deparmentService.findDeparmentAll
    val users: List[User] = userService.findUserSubtractEmail(request.session.get("email").get)
    var reasons: List[Reason] = reasonService.findReasonAll
    var statusList: List[entity.Status] = statusService.findStatusAll
    
    var id = CommonConstant.DEFAUL_ID
    var fullName = user.fullName
    var emailEmployee = user.email
    var emailManager = CommonConstant.DEFAUL_EMAIL_MANAGER
    var deparmentid = user.deparment.deparmentId
    var fromDate = CommonConstant.DEFAUL_DATE
    var toDate = CommonConstant.DEFAUL_DATE
    var submitDate = CommonConstant.DEFAUL_DATE
    var reasonId = CommonConstant.DEFAUL_REASON_ID
    var statusId = CommonConstant.DEFAUL_STATUS_ID
    
    if (idValue != 0) {
    	var emloyeeApply: EmployeeApply = employeeApplyService.findEmployeeApplyById(idValue)
    	if (emloyeeApply != null) {
    		id = emloyeeApply.id
				emailManager = emloyeeApply.emailManager.email
				deparmentid = emloyeeApply.deparment.deparmentId
				fromDate = emloyeeApply.fromDate
				toDate = emloyeeApply.toDate
				submitDate = emloyeeApply.submitDate
				reasonId = emloyeeApply.reason.reasonId
				statusId = emloyeeApply.status.statusId
    	}
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
      statusId,
      currentDate)
    
    Ok(views.html.employee_apply(
      employeeApplyForm.fill(form),
      deparments,
      users,
      reasons,
      request.session.get("email").get,
      request.session.get("roleId").get))
  }

  def emloyeeApplyPost() = Authenticated { implicit request =>
    val newEmployeeApplyForm = employeeApplyForm.bindFromRequest()
    var entity:EmployeeApply = new EmployeeApply
    newEmployeeApplyForm.bindFromRequest.fold(
      errorForm => {
        val users: List[User] = userService.findUserSubtractEmail(request.session.get("email").get)
        var deparments: List[Deparment] = deparmentService.findDeparmentAll
        var reasons: List[Reason] = reasonService.findReasonAll
        BadRequest(views.html.employee_apply(errorForm, deparments, users, reasons, request.session.get("email").get, request.session.get("roleId").get))
      },
      employeeApply => {
        entity.deparment = deparmentService.findDeparmentById(employeeApply.deparmentid)
        entity.emailEmployee = userService.findUserByEmail(employeeApply.emailEmployee)
        entity.emailManager = userService.findUserByEmail(employeeApply.emailManager)
        entity.fromDate = employeeApply.fromDate
        entity.toDate = employeeApply.toDate
        entity.reason = reasonService.findReasonById(employeeApply.reasonId)
        entity.status = statusService.findStatusById(employeeApply.statusId)
        entity.submitDate = employeeApply.submitDate
        // save to DB
        if (employeeApply.id != 0) {
          entity.id = employeeApply.id
          employeeApplyService.updateEmployeeApply(entity)
        } else {
        	employeeApplyService.save(entity)
        }
        
        val managerName:String = entity.emailManager.fullName
        val managerEmail:String = entity.emailManager.email
        val dateFrom:Date = entity.fromDate
        val toDate:Date = entity.toDate
        val employeeName:String = entity.emailEmployee.fullName
        val depatermentName:String = entity.deparment.deparmentName
        val reasonName:String = entity.reason.reasonName
        val employeeEmail: String = entity.emailEmployee.email
        // send mail
        mailer.send(sendMail(managerName, managerEmail, dateFrom, toDate, employeeName, depatermentName,
            reasonName, CommonConstant.TITLE_MAIL_REQUEST_APPROVAL, CommonConstant.URL_MAIL_REQUEST_APPROVAL))
        Redirect(routes.EmployeeController.approveEmployeeList(employeeApply.currentPage))
      })
  }
 
  def deleteApply(id: Int, currentPage: Int)= Authenticated { implicit request =>
    employeeApplyService.deleteEmployeeApplyById(id)
    Redirect(routes.EmployeeController.approveEmployeeList(currentPage))
  }
  
  def approveEmployeeList(currentPage:Int)= Authenticated {implicit request =>
    var employeeApplys : List[EmployeeApply] = null
    if (currentPage == CommonConstant.STATUS_ALL) {
      employeeApplys = employeeApplyService.findEmployeeApplyByEmail(request.session.get("email").get)
    } else {
      employeeApplys = employeeApplyService.findEmployeeApplyByStatus(request.session.get("email").get, currentPage)
    }
    Ok(views.html.employee(employeeApplys,currentPage,request.session.get("email").get,request.session.get("roleId").get))
  }
  
   private def sendMail(managerName:String,managerEmail:String,
      dateFrom:Date,toDate:Date,employeeName:String,depatermentName:String,reasonName:String,title:String, url:String) = 
    Email(
        title + employeeName,
        "HR Manager <hrmanagersystem@yandex.com>",
        Seq(s"$managerName <$managerEmail>"),
        bodyHtml = Some(s"""<html><body>
                        <p>Gửi Anh/Chị: <b>$managerName</b></p>
                        <p>Từ ngày $dateFrom đến ngày $toDate, có nhân viên <b>$employeeName</b></p>
                        <p>Thuộc phòng ban/bộ phận $depatermentName xin nghỉ vì lý do $reasonName</p>
                        <p></p>
                        <p>Anh/chị vui lòng truy cập vào <a href="http://localhost:9000/$url">HR Manager</a> để <b>duyệt đơn xin nghỉ</b> của nhân viên.</p>
                        <p>Trân trọng cảm ơn!</p>
                  </body></html>""")
       )
}
