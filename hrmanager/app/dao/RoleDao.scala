package dao

import java.util.{ List => JList }

import com.google.inject.ImplementedBy

import daoImpl.RoleDaoImpl
import entity.Role
import javax.persistence.Entity
import javax.persistence.Table

@ImplementedBy(classOf[RoleDaoImpl])
trait RoleDao {
  def findRoleAll: JList[Role]
  def findRoleById(roleId:String): Role
}