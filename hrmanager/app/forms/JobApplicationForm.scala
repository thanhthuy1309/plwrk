package forms

import java.util.Date
case class ListJobApplication(id: Int, name: String, toDate: Date, fromDate : Date,submitDate : Date, status: String,statusId: Int ,email:String)
case class ListAllJobApplication(id: Int,
    nameApprovaled: String,
    nameApproval: String,
    fromDate : Date,
    toDate: Date,
    status: String,
    emailApprovaled:String,
    emailApproval:String)
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
  statusId: Int,
  currentPage: Int)