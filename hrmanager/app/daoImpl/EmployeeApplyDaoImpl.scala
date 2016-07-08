package daoImpl

import java.util.{ List => JList }

import com.google.inject.ImplementedBy

import constants.DaoConstant
import dao.EmployeeApplyDao
import entity.EmployeeApply
import forms.ListJobApplication
import javax.persistence.Entity
import javax.persistence.Persistence
import javax.persistence.Query
import javax.persistence.Table

class EmployeeApplyDaoImpl extends EmployeeApplyDao {

  private var persitence = Persistence.createEntityManagerFactory(DaoConstant.DEFAULT_PERSISTENCE_UNIT)

  def save(employeeApply: EmployeeApply): Int = {
    val entityManager = persitence.createEntityManager()
    def transaction = entityManager.getTransaction
    transaction.begin()
    var result: Int = 0
    try {
      entityManager.persist(employeeApply)
      transaction.commit()
      result = 1
    } catch {
      case exception: Throwable =>
        if (transaction != null && transaction.isActive)
          transaction.rollback()
        throw exception
    } finally
      entityManager.close()
    result
  }
  
  def findEmployeeApplyByStatus(statusId: Int):JList[EmployeeApply]= {
    var result: JList[EmployeeApply] = null
    var entityManager = persitence.createEntityManager()
    if (entityManager != null) {
      var query: Query = entityManager.createNamedQuery(DaoConstant.EMPLOYEE_DAO_FIND_STATUS)
      query.setParameter("statusId", statusId)
      result = query.getResultList.asInstanceOf[JList[EmployeeApply]]
    }
    result
  }
  
  def findJobApplitationByEmailStatus(email:String, status: Int):JList[ListJobApplication] = {
    var result: JList[ListJobApplication] = null
    var entityManager = persitence.createEntityManager()
    if (entityManager != null) {
      var query: Query = entityManager.createNamedQuery(DaoConstant.EMPLOYEE_DAO_FIND_JOBAPPLITATIONBYEMAILSTATUS)
      query.setParameter("email", email)
      query.setParameter("statusId", status)
      var temp = query.getResultList
      if(temp != null) {
        if (!temp.isEmpty()) {
          result = temp.asInstanceOf[JList[ListJobApplication]]
        }
      }
    }
    result
  }
  
  def loadJobApplitationById(id: Int):EmployeeApply = {
    var entityManager = persitence.createEntityManager()
    entityManager.find(classOf[EmployeeApply], id)
  }
}