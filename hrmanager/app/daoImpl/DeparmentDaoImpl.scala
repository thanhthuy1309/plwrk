package daoImpl

import java.util.{ List => JList }

import dao.DepartemntDao
import entity.Deparment
import constants._
import javax.persistence.Persistence
import javax.persistence.Query
import utils.DataBaseUtils

class DeparmentDaoImpl extends DepartemntDao {
  
  def findDeparmentAll: JList[Deparment] = {
    var result: JList[Deparment] = null
    try {
      var entityManager = DataBaseUtils.persitence.createEntityManager()
      var query: Query = entityManager.createNamedQuery(DaoConstant.DEPARMENT_DAO_FIND_DEPARMENT_ALL)
      var temp = query.getResultList
      if(temp != null) {
        if(!temp.isEmpty()) {
          result = temp.asInstanceOf[JList[Deparment]]
        }
      }
    } catch {
       case exception: Throwable =>
        throw exception
    }
    result
  }
  
  def findDeparmentById(deparmentId:Int): Deparment = {
    var department: Deparment = null;
    try {
      var entityManager = DataBaseUtils.persitence.createEntityManager()
      department = entityManager.find(classOf[Deparment], deparmentId)
    } catch {
       case exception: Throwable =>
        throw exception
    }
    department
  }
}