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

  def save(employeeApply: EmployeeApply): Int = {
    val entityManager = DataBaseUtils.persitence.createEntityManager()
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
    var entityManager = DataBaseUtils.persitence.createEntityManager()
    try {
      var query: Query = entityManager.createNamedQuery(DaoConstant.EMPLOYEE_DAO_FIND_STATUS)
      query.setParameter("email", email)
      query.setParameter("statusId", statusId)
      result = query.getResultList.asInstanceOf[JList[EmployeeApply]]
    } catch {
       case exception: Throwable =>
        throw exception
    }
    result
  }
  
  def findJobApplitationByEmailStatus(email:String, status: Int):JList[ListJobApplication] = {
    var result: JList[ListJobApplication] = null
    var entityManager = DataBaseUtils.persitence.createEntityManager()
    try {
      var query: Query = entityManager.createNamedQuery(DaoConstant.EMPLOYEE_DAO_FIND_JOBAPPLITATIONBYEMAILSTATUS)
      query.setParameter("email", email)
      query.setParameter("statusId", status)
      var temp = query.getResultList
      if(temp != null) {
        if (!temp.isEmpty()) {
          result = temp.asInstanceOf[JList[ListJobApplication]]
        }
      }
    } catch {
       case exception: Throwable =>
        throw exception
    }
    result
  }
  
  def findEmployeeApplyById(id:Int): EmployeeApply = {
    var entity:EmployeeApply = null
    try {
      var employeeApply = DataBaseUtils.persitence.createEntityManager()
      entity = employeeApply.find(classOf[EmployeeApply], id)
    } catch {
       case exception: Throwable =>
        throw exception
    }
    entity
  }
  
  def deleteEmployeeApply(id:Int): Int = {
    var result: Int = 0
    var entityManager = DataBaseUtils.persitence.createEntityManager()
    def transaction = entityManager.getTransaction
    transaction.begin()
    try {
      var entity:EmployeeApply = entityManager.find(classOf[EmployeeApply], id)
      entityManager.remove(entity)
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
    val entityManager = DataBaseUtils.persitence.createEntityManager()
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
    var entityManager = DataBaseUtils.persitence.createEntityManager()
    try {
      var query: Query = entityManager.createNamedQuery(DaoConstant.EMPLOYEE_DAO_FIND_ALL)
      var temp = query.getResultList
      if(temp != null) {
        if (!temp.isEmpty()) {
          result = temp.asInstanceOf[JList[ListAllJobApplication]]
        }
      }
    } catch {
       case exception: Throwable =>
        throw exception
    }
    result
  }
  
  def findEmployeeApplyByEmail(email:String):JList[EmployeeApply]= {
    var result: JList[EmployeeApply] = null
    var entityManager = DataBaseUtils.persitence.createEntityManager()
    try {
      var query: Query = entityManager.createNamedQuery(DaoConstant.EMPLOYEE_DAO_FIND_EMAIL)
      query.setParameter("email", email)
      result = query.getResultList.asInstanceOf[JList[EmployeeApply]]
    } catch {
       case exception: Throwable =>
        throw exception
    }
    result
  }
  
  def findJobApplitationByEmailStatusAll(email:String):JList[ListJobApplication] = {
    var result: JList[ListJobApplication] = null
    var entityManager = DataBaseUtils.persitence.createEntityManager()
    try {
      var query: Query = entityManager.createNamedQuery(DaoConstant.EMPLOYEE_DAO_FIND_BYEMAILSTATUS_ALL)
      query.setParameter("email", email)
      var temp = query.getResultList
      if(temp != null) {
        if (!temp.isEmpty()) {
          result = temp.asInstanceOf[JList[ListJobApplication]]
        }
      }
    } catch {
       case exception: Throwable =>
        throw exception
    }
    result
  }
}