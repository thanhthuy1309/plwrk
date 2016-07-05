package serviceImpl

import java.util.{ List => JList }

import com.google.inject.ImplementedBy

import dao.RoleDao
import entity.Role
import javax.inject.Inject
import javax.persistence.Entity
import javax.persistence.Table
import service.RoleService

class RoleServiceImpl extends RoleService{
  
  @Inject
  private var roleDao: RoleDao = _
  
  def findRoleAll: JList[Role] = {
    roleDao.findRoleAll
  }
  
  def findRoleById(roleId:String): Role =  {
    roleDao.findRoleById(roleId)
  }
}