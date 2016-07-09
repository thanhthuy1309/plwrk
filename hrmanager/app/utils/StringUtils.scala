package utils

import constants.CommonConstant

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
    var result: String = ""
    if (status == CommonConstant.STATUS_YET_APPROVAL) {
      result = "Danh Sách Đơn Chờ Duyệt"
    } else if(status == CommonConstant.STATUS_APPROVAL) {
      result = "Danh Sách Đơn Đã Duyệt"
    } else if (status == CommonConstant.STATUS_CANCEL_APPROVAL) {
      result = "Danh Sách Đơn Từ Chối"
    }
    result
  }
  
  def printTitleInfo(status: Int): String = {
    var result: String = ""
    if (status == CommonConstant.STATUS_YET_APPROVAL) {
      result = "Đơn Chờ Duyệt"
    } else if(status == CommonConstant.STATUS_APPROVAL) {
      result = "Đơn Đã Duyệt"
    } else if (status == CommonConstant.STATUS_CANCEL_APPROVAL) {
      result = "Đơn Từ Chối"
    }
    result
  }
  
}