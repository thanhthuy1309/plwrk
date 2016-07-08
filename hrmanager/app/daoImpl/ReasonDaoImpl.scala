package daoImpl

import java.util.{ List => JList }

import constants._
import javax.persistence.Persistence
import javax.persistence.Query
import dao.ReasonDao
import entity.Reason

class ReasonDaoImpl extends ReasonDao {
  private var persitence = Persistence.createEntityManagerFactory(DaoConstant.DEFAULT_PERSISTENCE_UNIT)
  
  def findReasonAll: JList[Reason] = {
    var result: JList[Reason] = null
    var entityManager = persitence.createEntityManager()
    if (entityManager != null) {
      var query: Query = entityManager.createNamedQuery(DaoConstant.REASON_DAO_FIND_REASON_ALL)
      var temp = query.getResultList
      if(temp != null) {
        if (!temp.isEmpty()) {
          result = temp.asInstanceOf[JList[Reason]]
        }
      }
    }
    result
  }
  
  def findReasonById(ReasonId:Int): Reason = {
    var entityManager = persitence.createEntityManager()
    entityManager.find(classOf[Reason], ReasonId)
  }
}