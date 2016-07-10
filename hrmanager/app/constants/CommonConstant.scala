package constants

import java.util.Date

object CommonConstant {
  val ROLE_ADMIN: String = "1"
  val ROLE_USER: String = "2"
  val MODE_LIST_SCREEN: String = "0"
  val MODE_LIST_OTHERS: String = "1"
  val STATUS_ALL: Int = 0
  val STATUS_YET_APPROVAL: Int = 1
  val STATUS_APPROVAL: Int = 2
  val STATUS_CANCEL_APPROVAL: Int = 3
  val DEFAUL_ID: Int = 0
  val DEFAUL_DATE: Date = new Date()
  val DEFAUL_EMAIL_MANAGER: String = ""
  val DEFAUL_REASON_ID: Int = 1
  val DEFAUL_STATUS_ID: Int = 1
  
  val MODE_INSERT: Int = 0
  val MODE_UPDATE: Int = 1
  val MODE_DETAIL: Int = 2
  
  val TITLE_MAIL_REQUEST_APPROVAL: String = "Duyệt đơn xin nghỉ của nhân viên "
  
  val URL_MAIL_REQUEST_APPROVAL: String = "admin/application/list/1"
  
  val TITLE_MAIL_APPROVALED: String = " Đã duyệt đơn xin nghỉ của nhân viên "
  
  val TITLE_MAIL_YET_APPROVALED: String = "Từ chối đơn xin nghỉ của nhân viên "
  
  val URL_MAIL_APPROVALED: String = "employee/list/2"
  
  val URL_MAIL_YET_APPROVALED: String = "/employee/list/3"
  
  val CONTENT_APPROVALED: String = "duyệt"
  
  val CONTENT_YET_APPROVALED: String = "từ chối"
  
}