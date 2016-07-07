package daoImpl

import java.util.{ List => JList }

import com.google.inject.ImplementedBy

import dao.RoleDao
import entity.Role
import javax.persistence.Entity
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.Table
import javax.persistence.Persistence
import constants._
import javax.persistence.Query

class RoleDaoImpl extends RoleDao {

  private var persitence = Persistence.createEntityManagerFactory(DaoConstant.DEFAULT_PERSISTENCE_UNIT)

  def findRoleAll: JList[Role] = {
    var result: JList[Role] = null
    var entityManager = persitence.createEntityManager()
    if (entityManager != null) {
      var query: Query = entityManager.createNamedQuery(DaoConstant.ROLE_DAO_FIND_ROLE_ALL)
      //result = query.getResultList.asInstanceOf[JList[Role]]
      var temp = query.getResultList
      if(temp != null) {
        if (!temp.isEmpty()) {
          result = temp.asInstanceOf[JList[Role]]
        }
      }
    }
    result
  }
  
  def findRoleById(roleId:String): Role = {
    var role:Role = new Role
    var entityManager = persitence.createEntityManager()
    var resultList: JList[Role] = null
    if (entityManager != null) {
      var query: Query = entityManager.createNamedQuery(DaoConstant.ROLE_DAO_FIND_ROLE_ID)
      query.setParameter("roleId", roleId)
      var temp = query.getResultList
      if(temp != null) {
        if (!temp.isEmpty()) {
          resultList = temp.asInstanceOf[JList[Role]]
        }
      }
    }
    if (resultList != null) {
      if (resultList.size() > 0) {
        role = resultList.get(0).asInstanceOf[Role]
      }
    }
    role
  }
}