package service

import com.google.inject.ImplementedBy
import java.util.{ List => JList }
import forms.CreateEmployeeApplyForm
import serviceImpl.EmployeeApplyServiceImpl
import forms._
import java.util.{ List => JList }
import entity.EmployeeApply

@ImplementedBy(classOf[EmployeeApplyServiceImpl])
trait EmployeeApplyService {
  
  def save(entity:EmployeeApply): Int
  def findJobApplitationByEmailStatus(email:String, status: Int):JList[ListJobApplication]
  def findEmployeeApplyByStatus(email:String, statusId: Int):JList[EmployeeApply]
  def findEmployeeApplyById(id: Int): EmployeeApply
  def deleteEmployeeApplyById(id: Int): Int
  def updateEmployeeApply(entity:EmployeeApply): Int
  def findEmployeeApplyByEmail(email:String):JList[EmployeeApply]
  
  def findJobApplitationAllList():JList[ListAllJobApplication]
  
  def findJobApplitationByEmailStatusAll(email:String):JList[ListJobApplication]
}