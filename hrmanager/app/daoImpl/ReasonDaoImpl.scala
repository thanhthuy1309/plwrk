package daoImpl

import java.util.{ List => JList }

import constants._
import javax.persistence.Persistence
import javax.persistence.Query
import dao.ReasonDao
import entity.Reason
import utils.DataBaseUtils

class ReasonDaoImpl extends ReasonDao {
  
  def findReasonAll: JList[Reason] = {
    var result: JList[Reason] = null
    try {
      var entityManager = DataBaseUtils.persitence.createEntityManager()
      var query: Query = entityManager.createNamedQuery(DaoConstant.REASON_DAO_FIND_REASON_ALL)
      var temp = query.getResultList
      if(temp != null) {
        if (!temp.isEmpty()) {
          result = temp.asInstanceOf[JList[Reason]]
        }
      }
    } catch {
       case exception: Throwable =>
        throw exception
    }
    result
  }
  
  def findReasonById(ReasonId:Int): Reason = {
    var entity: Reason = null
    try {
      var entityManager = DataBaseUtils.persitence.createEntityManager()
      entity = entityManager.find(classOf[Reason], ReasonId)
    } catch {
       case exception: Throwable =>
        throw exception
    }
    entity
  }
}