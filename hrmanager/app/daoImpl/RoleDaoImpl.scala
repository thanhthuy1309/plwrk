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
import utils.DataBaseUtils

class RoleDaoImpl extends RoleDao {

  def findRoleAll: JList[Role] = {
    var result: JList[Role] = null
    try {
      var entityManager = DataBaseUtils.persitence.createEntityManager()
      var query: Query = entityManager.createNamedQuery(DaoConstant.ROLE_DAO_FIND_ROLE_ALL)
      var temp = query.getResultList
      if(temp != null) {
        if (!temp.isEmpty()) {
          result = temp.asInstanceOf[JList[Role]]
        }
      }
     } catch {
       case exception: Throwable =>
        throw exception
    }
    result
  }
  
  def findRoleById(roleId:String): Role = {
    var role: Role = new Role;
    try {
      var entityManager = DataBaseUtils.persitence.createEntityManager()
      role = entityManager.find(classOf[Role], roleId)
    } catch {
       case exception: Throwable =>
        throw exception
    }
    role
  }
}