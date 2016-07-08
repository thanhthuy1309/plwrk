package serviceImpl

import com.google.inject.Inject
import java.util.{ List => JList }
import controllers.CreateEmployeeApplyForm
import dao.EmployeeApplyDao
import entity.EmployeeApply
import service.DeparmentService
import service.EmployeeApplyService
import service.UserService
import forms._
import service.ReasonService
import service.DeparmentService
import service.StatusService
import java.util.{ List => JList }

class EmployeeApplyServiceImpl extends EmployeeApplyService {
  
  @Inject
  private var employeeApplyDao: EmployeeApplyDao = _
  
  @Inject
  private var deparmentService:DeparmentService = _
  
  @Inject
  private var userService:UserService = _
  
  @Inject
  private var reasonService:ReasonService = _
  
  @Inject
  private var statusService:StatusService = _
  
  def save(employeeApplyForm: CreateEmployeeApplyForm): Int = {
    var entity:EmployeeApply = new EmployeeApply
    entity.deparment = deparmentService.findDeparmentById(employeeApplyForm.deparmentid)
    entity.emailEmployee = userService.findUserByEmail(employeeApplyForm.emailEmployee)
    entity.emailManager = userService.findUserByEmail(employeeApplyForm.emailManager)
    entity.fromDate = employeeApplyForm.fromDate
    entity.toDate = employeeApplyForm.toDate
    entity.reason = reasonService.findReasonById(employeeApplyForm.reasonId)
    entity.status = statusService.findStatusById(employeeApplyForm.statusId)
    entity.submitDate = employeeApplyForm.submitDate
    
    employeeApplyDao.save(entity)
  }
  
  def findEmployeeApplyByStatus(email:String, statusId: Int):JList[EmployeeApply]= {
    employeeApplyDao.findEmployeeApplyByStatus(email, statusId)
  }
  
  def findJobApplitationByEmailStatus(email:String, status: Int):JList[ListJobApplication] = {
    employeeApplyDao.findJobApplitationByEmailStatus(email, status)
  }
  
  def deleteEmployeeApplyById(id: Int): Int = {
    var entity:EmployeeApply = employeeApplyDao.findEmployeeApplyById(id)
    employeeApplyDao.deleteEmployeeApply(entity)
  }
  
  def findEmployeeApplyById(id: Int):EmployeeApply = {
    employeeApplyDao.findEmployeeApplyById(id)
  }
  
  def updateEmployeeApply(employeeApplyForm: CreateEmployeeApplyForm): Int = {
    var entity:EmployeeApply = new EmployeeApply
    entity.id = employeeApplyForm.id
    entity.deparment = deparmentService.findDeparmentById(employeeApplyForm.deparmentid)
    entity.emailEmployee = userService.findUserByEmail(employeeApplyForm.emailEmployee)
    entity.emailManager = userService.findUserByEmail(employeeApplyForm.emailManager)
    entity.fromDate = employeeApplyForm.fromDate
    entity.toDate = employeeApplyForm.toDate
    entity.reason = reasonService.findReasonById(employeeApplyForm.reasonId)
    entity.status = statusService.findStatusById(employeeApplyForm.statusId)
    entity.submitDate = employeeApplyForm.submitDate
    
    employeeApplyDao.updateEmployeeApply(entity)
  }
  
//  def findEmployeeApplyByEmail(email:String):JList[EmployeeApply]= {
//    employeeApplyDao.findEmployeeApplyByEmail(email)
//  }
}