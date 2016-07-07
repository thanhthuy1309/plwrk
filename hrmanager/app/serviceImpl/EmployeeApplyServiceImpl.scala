package serviceImpl

import com.google.inject.Inject

import controllers.CreateEmployeeApplyForm
import dao.EmployeeApplyDao
import entity.EmployeeApply
import service.DeparmentService
import service.EmployeeApplyService
import service.UserService

class EmployeeApplyServiceImpl extends EmployeeApplyService {
  
  @Inject
  private var employeeApplyDao: EmployeeApplyDao = _
  
  @Inject
  private var deparmentService:DeparmentService = _
  
  @Inject
  private var userService:UserService = _
  
  def save(employeeApplyForm: CreateEmployeeApplyForm): Int = {
    var entity:EmployeeApply = new EmployeeApply
    entity.deparment = deparmentService.findDeparmentById(employeeApplyForm.deparmentid)
    entity.emailEmployee = userService.findUserByEmail(employeeApplyForm.emailEmployee)
    entity.emailManager = userService.findUserByEmail(employeeApplyForm.emailManager)
    entity.fromDate = employeeApplyForm.fromDate
    entity.toDate = employeeApplyForm.toDate
    entity.reason = employeeApplyForm.reason
    entity.status = 1
    
    employeeApplyDao.save(entity)
  }
}