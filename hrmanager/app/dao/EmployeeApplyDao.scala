package dao

import entity.EmployeeApply
import com.google.inject.ImplementedBy
import java.util.{ List => JList }
import daoImpl.EmployeeApplyDaoImpl
import forms._

@ImplementedBy(classOf[EmployeeApplyDaoImpl])
trait EmployeeApplyDao {
  def save(employeeApply: EmployeeApply): Int
  
  def findEmployeeApplyByStatus(status: Int):JList[EmployeeApply]
  
  def findJobApplitationByEmailStatus(email:String, status: Int):JList[ListJobApplication]
  
}