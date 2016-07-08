package daoImpl

import java.util.{ List => JList }

import constants._
import dao.EmployeeApplyDao
import entity.EmployeeApply
import forms._
import javax.persistence.EntityManager
import javax.persistence.Persistence
import javax.persistence.Query
import entity.EmployeeApply
import utils.DataBaseUtils

class EmployeeApplyDaoImpl extends EmployeeApplyDao {

  //private var persitence = Persistence.createEntityManagerFactory(DaoConstant.DEFAULT_PERSISTENCE_UNIT)
private var persitence = DataBaseUtils.persitence
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
  
  def findEmployeeApplyByStatus(email:String, statusId: Int):JList[EmployeeApply]= {
    var result: JList[EmployeeApply] = null
    var entityManager = persitence.createEntityManager()
    if (entityManager != null) {
      var query: Query = entityManager.createNamedQuery(DaoConstant.EMPLOYEE_DAO_FIND_STATUS)
      query.setParameter("email", email)
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
  
  def findEmployeeApplyById(id:Int): EmployeeApply = {
    var employeeApply = persitence.createEntityManager()
    employeeApply.find(classOf[EmployeeApply], id)
  }
  
  def deleteEmployeeApply(employeeApply: EmployeeApply): Int = {
    var result: Int = 0
    var entityManager = persitence.createEntityManager()
    def transaction = entityManager.getTransaction
    transaction.begin()
    try {
      entityManager.remove(employeeApply)
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
  
  def updateEmployeeApply(employeeApply: EmployeeApply): Int = {
    val entityManager = persitence.createEntityManager()
    def transaction = entityManager.getTransaction
    transaction.begin()
    var result: Int = 0
    try {
      entityManager.merge(employeeApply)
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
  
  def findJobApplitationAllList():JList[ListAllJobApplication] = {
    var result: JList[ListAllJobApplication] = null
    var entityManager = persitence.createEntityManager()
    if (entityManager != null) {
      var query: Query = entityManager.createNamedQuery(DaoConstant.EMPLOYEE_DAO_FIND_ALL)
      var temp = query.getResultList
      if(temp != null) {
        if (!temp.isEmpty()) {
          result = temp.asInstanceOf[JList[ListAllJobApplication]]
        }
      }
    }
    result
  }
}