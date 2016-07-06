package serviceImpl

import java.util.{ List => JList }

import com.google.inject.ImplementedBy

import dao.UserDao
import dao.RoleDao
import daoImpl.PeopleDaoImpl
import entity.User
import javax.inject.Inject
import javax.persistence.Entity
import javax.persistence.Table
import service.UserService
import forms._
import java.util.List
import entity.Role
import constants.CommonConstant

class UserServiceImpl extends UserService {

  @Inject
  private var userDAO: UserDao = _

  @Inject
  private var roleDao: RoleDao = _

  def findUserAll: JList[User] = userDAO.findUserAll

  def findUserByEmail(email: String): User = userDAO.findUserByEmail(email)

  def saveByGoogle(user: UserGoogleForm): Int = {
    var entity: User = new User
    entity.email = user.email
    entity.name = user.name
    var listUser: List[User] = userDAO.findUserAll

    if (listUser != null) {
      if (listUser.size() > 0) {
        var role: Role = roleDao.findRoleById(CommonConstant.ROLE_USER)
        entity.role = role
      }
    } else {
      var role: Role = roleDao.findRoleById(CommonConstant.ROLE_ADMIN)
      entity.role = role
    }
    userDAO.save(entity)
  }

  def serviceLoginAccount(info : UserLoginAccountForm): Int = {
    var result: Int = 0
    var user: User = userDAO.findUserByEmailPassword(info.email, info.password)
    if (user.email == null && user.passWord == null) {
      result = 1
    } else if (user.email != null && user.passWord != null) {
      result = 2
    }
    result
  }
  
}