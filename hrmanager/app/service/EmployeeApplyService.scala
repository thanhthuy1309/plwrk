package service

import com.google.inject.ImplementedBy
import java.util.{ List => JList }
import controllers.CreateEmployeeApplyForm
import serviceImpl.EmployeeApplyServiceImpl
import forms._
import java.util.{ List => JList }
import entity.EmployeeApply

@ImplementedBy(classOf[EmployeeApplyServiceImpl])
trait EmployeeApplyService {
  
  def save(employeeApplyForm: CreateEmployeeApplyForm): Int
  def findJobApplitationByEmailStatus(email:String, status: Int):JList[ListJobApplication]
  def findEmployeeApplyByStatus(email:String, statusId: Int):JList[EmployeeApply]
  def findEmployeeApplyById(id: Int): EmployeeApply
  def deleteEmployeeApplyById(id: Int): Int
  def updateEmployeeApply(employeeApplyForm: CreateEmployeeApplyForm): Int
}