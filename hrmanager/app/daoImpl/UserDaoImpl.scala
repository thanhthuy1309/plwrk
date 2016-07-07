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

class UserDaoImpl extends UserDao {

  private var persitence = Persistence.createEntityManagerFactory(DaoConstant.DEFAULT_PERSISTENCE_UNIT)

  def findUserAll: JList[User] = {
    var result: JList[User] = null
    var entityManager = persitence.createEntityManager()
    if (entityManager != null) {
      var query: Query = entityManager.createNamedQuery(DaoConstant.USER_DAO_FIND_USER_ALL)
      result = query.getResultList.asInstanceOf[JList[User]]
    }
    result
  }

  def findUserByEmail(email: String): User = {
    var role: User = new User
    var entityManager = persitence.createEntityManager()
    var resultList: JList[User] = null
    if (entityManager != null) {
      var query: Query = entityManager.createNamedQuery(DaoConstant.USER_DAO_FIND_EMAIL)
      query.setParameter("email", email)
      resultList = query.getResultList.asInstanceOf[JList[User]]
    }
    if (resultList != null) {
      if (resultList.size() > 0) {
        role = resultList.get(0).asInstanceOf[User]
      }
    }
    role
  }

  def save(user: User): Int = {
    val entityManager = persitence.createEntityManager()
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
    } finally
      entityManager.close()
    result
  }
  
  def update(user: User): Int = {
    val entityManager = persitence.createEntityManager()
    def transaction = entityManager.getTransaction
    transaction.begin()
    var result: Int = 0
    try {
      entityManager.merge(user)
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
  
  def findUserByEmailPassword(email: String, password:String): User = {
    var role: User = new User
    var entityManager = persitence.createEntityManager()
    var resultList: JList[User] = null
    if (entityManager != null) {
      var query: Query = entityManager.createNamedQuery(DaoConstant.USER_DAO_FIND_EMAIL_PASSWORD)
      query.setParameter("email", email)
      query.setParameter("password", password)
      resultList = query.getResultList.asInstanceOf[JList[User]]
    }
    if (resultList != null) {
      if (resultList.size() > 0) {
        role = resultList.get(0).asInstanceOf[User]
      }
    }
    role
  }
  
  def findUserSubtractEmail(email: String): JList[User]= {
    var result: JList[User] = null
    var entityManager = persitence.createEntityManager()
    if (entityManager != null) {
      var query: Query = entityManager.createNamedQuery(DaoConstant.USER_DAO_FIND_SUBTRACT_EMAIL)
      query.setParameter("email", email)
      result = query.getResultList.asInstanceOf[JList[User]]
    }
    result
  }
}