package daoImpl

import java.util.{ List => JList }

import com.google.inject.ImplementedBy

import dao.UserDao
import entity.User
import javax.persistence.Entity
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.Table
import javax.persistence.Persistence
import constants._
import javax.persistence.Query
import utils.DataBaseUtils

class UserDaoImpl extends UserDao {

  def findUserAll: JList[User] = {
    var result: JList[User] = null
    var entityManager = DataBaseUtils.persitence.createEntityManager()
    try {
      var query: Query = entityManager.createNamedQuery(DaoConstant.USER_DAO_FIND_USER_ALL)
      var temp = query.getResultList
      if(temp != null) {
        if (!temp.isEmpty()) {
          result = temp.asInstanceOf[JList[User]]
        }
      }
    } catch {
       case exception: Throwable =>
        throw exception
    }
    result
  }

  def findUserByEmail(email: String): User = {
    var role: User = new User
    try {
      var entityManager = DataBaseUtils.persitence.createEntityManager()
      var resultList: JList[User] = null
        var query: Query = entityManager.createNamedQuery(DaoConstant.USER_DAO_FIND_EMAIL)
        query.setParameter("email", email)
        var temp = query.getResultList
        if(temp != null) {
          if (!temp.isEmpty()) {
            resultList = temp.asInstanceOf[JList[User]]
          }
        }
      if (resultList != null) {
        if (resultList.size() > 0) {
          role = resultList.get(0).asInstanceOf[User]
        }
      }
    } catch {
       case exception: Throwable =>
        throw exception
    }
    role
  }

  def save(user: User): Int = {
    val entityManager = DataBaseUtils.persitence.createEntityManager()
    def transaction = entityManager.getTransaction
    transaction.begin()
    var result: Int = 0
    try {
      entityManager.persist(user)
      transaction.commit()
      result = 1
    } catch {
      case exception: Throwable =>
        if (transaction != null && transaction.isActive)
          transaction.rollback()
        throw exception
    } finally {
      entityManager.close()
    }
    result
  }
  
  def update(user: User): Int = {
    val entityManager = DataBaseUtils.persitence.createEntityManager()
    def transaction = entityManager.getTransaction
    var result: Int = 0
    try {
      transaction.begin()
      entityManager.merge(user)
      transaction.commit()
      result = 1
    } catch {
      case exception: Throwable =>
        if (transaction != null && transaction.isActive)
          transaction.rollback()
        throw exception
    } finally {
      entityManager.close()
    }
    result
  }
  
  def findUserByEmailPassword(email: String, password:String): User = {
    var role: User = new User
    try {
      var entityManager = DataBaseUtils.persitence.createEntityManager()
      var resultList: JList[User] = null
      var query: Query = entityManager.createNamedQuery(DaoConstant.USER_DAO_FIND_EMAIL_PASSWORD)
      query.setParameter("email", email)
      query.setParameter("password", password)
      var temp = query.getResultList
      if(temp != null) {
        if (!temp.isEmpty()) {
          resultList = temp.asInstanceOf[JList[User]]
        }
      }
      if (resultList != null) {
        if (resultList.size() > 0) {
          role = resultList.get(0).asInstanceOf[User]
        }
      }
    } catch {
       case exception: Throwable =>
        throw exception
    }
    role
  }
  
  def findUserSubtractEmail(email: String): JList[User]= {
    var result: JList[User] = null
    try {
      var entityManager = DataBaseUtils.persitence.createEntityManager()
      var query: Query = entityManager.createNamedQuery(DaoConstant.USER_DAO_FIND_SUBTRACT_EMAIL)
      query.setParameter("email", email)
      var temp = query.getResultList
      if(temp != null) {
        if (!temp.isEmpty()) {
          result = temp.asInstanceOf[JList[User]]
        }
      }
    } catch {
       case exception: Throwable =>
        throw exception
    }
    result
  }
  
}