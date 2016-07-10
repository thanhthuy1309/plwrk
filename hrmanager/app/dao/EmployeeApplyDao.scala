package dao

import entity.EmployeeApply
import com.google.inject.ImplementedBy
import java.util.{ List => JList }
import daoImpl.EmployeeApplyDaoImpl
import forms._

@ImplementedBy(classOf[EmployeeApplyDaoImpl])
trait EmployeeApplyDao {
  def save(employeeApply: EmployeeApply): Int
  
  def findEmployeeApplyByStatus(email:String, statusId: Int):JList[EmployeeApply]
  
  def findJobApplitationByEmailStatus(email:String, status: Int):JList[ListJobApplication]
  
  def findEmployeeApplyById(id:Int): EmployeeApply
  
  def deleteEmployeeApply(id:Int): Int
  
  def updateEmployeeApply(entity:EmployeeApply): Int
  
  def findEmployeeApplyByEmail(email:String):JList[EmployeeApply]
  
  def findJobApplitationAllList():JList[ListAllJobApplication]
  
  def findJobApplitationByEmailStatusAll(email:String):JList[ListJobApplication]
}