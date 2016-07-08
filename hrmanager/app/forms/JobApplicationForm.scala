package forms

import java.util.Date
case class ListJobApplication(id: Int, name: String, toDate: Date, fromDate : Date,submitDate : Date, reason: String, email:String)
case class ListAllJobApplication(id: Int,
    nameApprovaled: String,
    nameApproval: String,
    fromDate : Date,
    toDate: Date,
    status: String,
    emailApprovaled:String,
    emailApproval:String)