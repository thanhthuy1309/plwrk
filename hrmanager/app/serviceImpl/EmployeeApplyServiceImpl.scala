package serviceImpl

import com.google.inject.Inject
import java.util.{ List => JList }
import forms.CreateEmployeeApplyForm
import dao.EmployeeApplyDao
import entity.EmployeeApply
import service.EmployeeApplyService
import forms._
import java.util.{ List => JList }
import constants.CommonConstant

class EmployeeApplyServiceImpl extends EmployeeApplyService {
  
  @Inject
  private var employeeApplyDao: EmployeeApplyDao = _
  
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
    employeeApplyDao.deleteEmployeeApply(id)
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
  
  def findJobApplitationAllList():JList[ListAllJobApplication] = {
    employeeApplyDao.findJobApplitationAllList()
  }
  
  def findJobApplitationByEmailStatusAll(email:String):JList[ListJobApplication] = {
    employeeApplyDao.findJobApplitationByEmailStatusAll(email)
  }
}