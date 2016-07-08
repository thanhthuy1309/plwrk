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
import utils.DataBaseUtils

class StatusDaoImpl extends StatusDao {
  
  def findStatusAll: JList[Status] = {
    var result: JList[Status] = null
    try {
      var entityManager = DataBaseUtils.persitence.createEntityManager()
      var query: Query = entityManager.createNamedQuery(DaoConstant.STATUS_DAO_FIND_STATUS_ALL)
      var temp = query.getResultList
      if(temp != null) {
        if (!temp.isEmpty()) {
          result = temp.asInstanceOf[JList[Status]]
        }
      }
    } catch {
       case exception: Throwable =>
        throw exception
    }
    result
  }
  
  def findStatusById(statusId:Int): Status = {
    var status:Status = null
    try {
      var entityManager = DataBaseUtils.persitence.createEntityManager()
      status = entityManager.find(classOf[Status], statusId)
    } catch {
       case exception: Throwable =>
        throw exception
    }
    status
  }
  
}