package utils

import constants.CommonConstant
import java.util.Date
import play.api.libs.mailer._
import play.api.data.Form
import forms.CreateEmployeeApplyForm
import scala.util.control._

object StringUtils {
  
  def deleteStrip(quoted: String): String = {
    var result: String = ""
    if (quoted != null) {
      if (quoted.trim().nonEmpty) {
        result = quoted.filter(char => char != '\"')
      }
    }
    result
  }
  
  def printTitleJobList(status: Int): String = {
    status match {
      case CommonConstant.STATUS_YET_APPROVAL => "Danh Sách Đơn Chờ Duyệt"
      case CommonConstant.STATUS_APPROVAL => "Danh Sách Đơn Đã Duyệt"
      case CommonConstant.STATUS_CANCEL_APPROVAL => "Danh Sách Đơn Từ Chối"
      case CommonConstant.STATUS_ALL => "Danh Sách Tất Cả Đơn"
    }
    
  }
  
  def printTitleInfo(status: Int): String = {
    status match {
      case CommonConstant.STATUS_YET_APPROVAL => "Đơn Chờ Duyệt"
      case CommonConstant.STATUS_APPROVAL => "Đơn Đã Duyệt"
      case CommonConstant.STATUS_CANCEL_APPROVAL => "Đơn Từ Chối"
    }
  }
  
  def printTitleApplication(status: Int): String = {
    status match {
      case CommonConstant.MODE_INSERT => "Nộp Đơn"
      case CommonConstant.MODE_UPDATE => "Cập Nhật Đơn"
      case _ => "Cập Nhật Đơn"
    }
  }
  
  def redirectPageRole(roleId: String): String = {
    roleId match {
      case CommonConstant.ROLE_ADMIN => "/admin/list"
      case CommonConstant.ROLE_USER => "/employee/list/0"
    }
  }
  def getMessageDate(employeeApplyForm : Form[CreateEmployeeApplyForm]): String = {
    var key:String = null
    if(employeeApplyForm.hasGlobalErrors) {
       val loop = new Breaks
       loop.breakable {
  				for(error <- employeeApplyForm.globalErrors) {
	    				if(error.messages.contains("employee.error.datefromto")) {
	    						key = "employee.error.datefromto"
	    				}
	    				loop.break
  				}
       }
			}
    key
  }
}