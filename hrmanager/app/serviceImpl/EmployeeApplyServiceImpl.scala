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
import constants.CommonConstant

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
  
  def save(entity:EmployeeApply): Int = {
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
  
  def updateEmployeeApply(entity:EmployeeApply): Int = {
    employeeApplyDao.updateEmployeeApply(entity)
  }
  
  def findEmployeeApplyByEmail(email:String):JList[EmployeeApply]= {
    employeeApplyDao.findEmployeeApplyByEmail(email)
  }
  def updateEmployeeApply(employeeApplyForm: EmployeeApply): Int = {
    employeeApplyDao.updateEmployeeApply(employeeApplyForm)
  }
  def findJobApplitationAllList():JList[ListAllJobApplication] = {
    employeeApplyDao.findJobApplitationAllList()
  }
}