package service

import com.google.inject.ImplementedBy

import controllers.CreateEmployeeApplyForm
import serviceImpl.EmployeeApplyServiceImpl
import java.util.{ List => JList }
import entity.EmployeeApply

@ImplementedBy(classOf[EmployeeApplyServiceImpl])
trait EmployeeApplyService {
  
  def save(employeeApplyForm: CreateEmployeeApplyForm): Int
  
  def findEmployeeApplyByStatus(statusId: Int):JList[EmployeeApply]
}