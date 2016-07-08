package daoImpl

import java.util.{ List => JList }

import dao.StatusDao
import entity.Status
import javax.persistence.Entity
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.Table
import javax.persistence.Persistence
import constants._
import javax.persistence.Query

class StatusDaoImpl extends StatusDao {
  private var persitence = Persistence.createEntityManagerFactory(DaoConstant.DEFAULT_PERSISTENCE_UNIT)
  
  def findStatusAll: JList[Status] = {
    var result: JList[Status] = null
    var entityManager = persitence.createEntityManager()
    if (entityManager != null) {
      var query: Query = entityManager.createNamedQuery(DaoConstant.STATUS_DAO_FIND_STATUS_ALL)
      var temp = query.getResultList
      if(temp != null) {
        if (!temp.isEmpty()) {
          result = temp.asInstanceOf[JList[Status]]
        }
      }
    }
    result
  }
  
  def findStatusById(statusId:Int): Status = {
    var entityManager = persitence.createEntityManager()
    entityManager.find(classOf[Status], statusId)
  }
}