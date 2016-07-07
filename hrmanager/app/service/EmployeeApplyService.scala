package service

import com.google.inject.ImplementedBy

import controllers.CreateEmployeeApplyForm
import serviceImpl.EmployeeApplyServiceImpl

@ImplementedBy(classOf[EmployeeApplyServiceImpl])
trait EmployeeApplyService {
  def save(employeeApplyForm: CreateEmployeeApplyForm): Int
}