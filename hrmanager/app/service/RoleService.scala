package service

import java.util.{ List => JList }

import com.google.inject.ImplementedBy

import javax.persistence.Entity
import javax.persistence.Table
import serviceImpl.RoleServiceImpl
import entity.Role

@ImplementedBy(classOf[RoleServiceImpl])
trait RoleService {
  def findRoleAll: JList[Role]
  def findRoleById(roleId:String): Role
}