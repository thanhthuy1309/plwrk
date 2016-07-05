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
      result = query.getResultList.asInstanceOf[JList[Role]]
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
      resultList = query.getResultList.asInstanceOf[JList[Role]]
    }
    if (resultList != null) {
      role = resultList.get(0).asInstanceOf[Role]
    }
    role
  }
}