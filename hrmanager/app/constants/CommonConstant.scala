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
  
}