package daoImpl

import java.util.{ List => JList }

import dao.DepartemntDao
import entity.Deparment
import constants._
import javax.persistence.Persistence
import javax.persistence.Query

class DeparmentDaoImpl extends DepartemntDao {
  private var persitence = Persistence.createEntityManagerFactory(DaoConstant.DEFAULT_PERSISTENCE_UNIT)
  
  def findDeparmentAll: JList[Deparment] = {
    var result: JList[Deparment] = null
    var entityManager = persitence.createEntityManager()
    if (entityManager != null) {
      var query: Query = entityManager.createNamedQuery(DaoConstant.DEPARMENT_DAO_FIND_DEPARMENT_ALL)
      //result = query.getResultList.asInstanceOf[JList[Deparment]]
      var temp = query.getResultList
      if(temp != null) {
        if (!temp.isEmpty()) {
          result = temp.asInstanceOf[JList[Deparment]]
        }
      }
    }
    result
  }
  
  def findDeparmentById(deparmentId:Int): Deparment = {
    var entityManager = persitence.createEntityManager()
    entityManager.find(classOf[Deparment], deparmentId)
  }
}