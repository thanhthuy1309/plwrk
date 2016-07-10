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
import play.api.libs.mailer._
import play.api.Environment
import utils.StringUtils
import configs._


class AdminJobApplicationController @Inject() (val messagesApi: MessagesApi,
    val ws: WSClient, mailer: MailerClient, environment: Environment)(implicit ec: ExecutionContext) extends Controller with I18nSupport {
  
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
 
  def listJobApplication(status:Int) = Authenticated {implicit request =>
    var list : List[ListJobApplication]= null
    if (status == CommonConstant.STATUS_ALL) {
     list = employeeService.findJobApplitationByEmailStatusAll(request.session.get("email").get)
    } else {
      list = employeeService.findJobApplitationByEmailStatus(request.session.get("email").get, status)
    }
    Ok(views.html.jobapplication_list(list,request.session.get("email").get,request.session.get("roleId").get, status))
   }
  
  def loadJobApplicationInfo(id: Int, status:Int,flag:Int) = Authenticated {implicit request =>
     var info: EmployeeApply = employeeService.findEmployeeApplyById(id)
     Ok(views.html.jobapplication_info(info,request.session.get("email").get,request.session.get("roleId").get,status,flag))
  }
  
  def approvalJobApplication(id: Int, status:Int, flag:Int) = Authenticated {implicit request =>
     var info: EmployeeApply = employeeService.findEmployeeApplyById(id)
     var title:String = null
     var url:String = null
     var content:String = null
     if (flag == CommonConstant.STATUS_APPROVAL) {
       info.status = statusService.findStatusById(CommonConstant.STATUS_APPROVAL)
       title = CommonConstant.TITLE_MAIL_APPROVALED
       url = CommonConstant.URL_MAIL_APPROVALED
       content = CommonConstant.CONTENT_APPROVALED
     } else if(flag == CommonConstant.STATUS_CANCEL_APPROVAL) {
       info.status = statusService.findStatusById(CommonConstant.STATUS_CANCEL_APPROVAL)
       title = CommonConstant.TITLE_MAIL_YET_APPROVALED
       url = CommonConstant.URL_MAIL_YET_APPROVALED
       content = CommonConstant.CONTENT_YET_APPROVALED
     }
     mailer.send(sendMail(info.emailEmployee.fullName,
         info.emailEmployee.email, info.fromDate, info.toDate, info.emailManager.fullName,
         info.deparment.deparmentName, info.reason.reasonName, title, url,content))
     employeeService.updateEmployeeApply(info)
     
     Redirect(routes.AdminJobApplicationController.listJobApplication(status))
  }
   
  def managerJobApplicationList() = Authenticated {implicit request =>
     var list : List[ListAllJobApplication] = employeeService.findJobApplitationAllList()
     Ok(views.html.admin_jobapplication_list(list,request.session.get("email").get,request.session.get("roleId").get))
  }
   
  def infoJobApplicationInfo(id: Int) = Authenticated {implicit request =>
     var info: EmployeeApply = employeeService.findEmployeeApplyById(id)
     Ok(views.html.admin_jobapplication_info(info,request.session.get("email").get,request.session.get("roleId").get))
  }
   
  def deleteApply(id: Int)= Authenticated { implicit request =>
    employeeService.deleteEmployeeApplyById(id)
    Redirect(routes.AdminJobApplicationController.managerJobApplicationList())
  }
  
  private def sendMail(managerName:String,managerEmail:String,
      dateFrom:Date,toDate:Date,employeeName:String,depatermentName:String,reasonName:String,title:String, url:String, content:String) = 
    Email(
        title + employeeName,
        "HR Manager <hrmanagersystem@yandex.com>",
        Seq(s"$managerName <$managerEmail>"),
        bodyHtml = Some(s"""<html><body>
                        <p>Gửi Anh/Chị: <b>$employeeName</b></p>
                        <p>Từ ngày $dateFrom đến ngày $toDate, xin nghỉ vì lý do $reasonName</p>
                        <p>Cấp trên <b>$managerName</b> của phòng ban/bộ phận $depatermentName <b>đã $content đơn xin nghỉ</b> của bạn </p>
                        <p></p>
                        <p>Anh/chị vui lòng truy cập vào <a href="http://localhost:9000/$url">HR Manager</a> để xem thông tin.</p>
                        <p>Trân trọng cảm ơn!</p>
                  </body></html>""")
       )
}