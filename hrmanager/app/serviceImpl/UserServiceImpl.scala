package serviceImpl

import java.util.{ List => JList }

import com.google.inject.ImplementedBy

import constants.CommonConstant
import dao.DepartemntDao
import dao.RoleDao
import dao.UserDao
import daoImpl.DeparmentDaoImpl
import daoImpl.RoleDaoImpl
import daoImpl.UserDaoImpl
import entity.Deparment
import entity.Role
import entity.User
import forms.UpdateUserForm
import forms.UserGoogleForm
import forms.UserLoginAccountForm
import javax.inject.Inject
import javax.persistence.Entity
import javax.persistence.Table
import service.UserService
import java.util.Date

class UserServiceImpl extends UserService {

  @Inject
  private var userDAO: UserDao = _

  @Inject
  private var roleDao: RoleDao = _

  @Inject
  private var departmentDao: DepartemntDao = _

  def findUserAll: JList[User] = userDAO.findUserAll

  def findUserByEmail(email: String): User = userDAO.findUserByEmail(email)

  def saveByGoogle(user: UserGoogleForm): Int = {
    var entity: User = new User
    entity.email = user.email
    entity.name = user.name
    entity.fullName = user.name
    var listUser: JList[User] = userDAO.findUserAll

    if (listUser != null) {
      if (listUser.size() > 0) {
        var role: Role = roleDao.findRoleById(CommonConstant.ROLE_USER)
        entity.role = role
      }
    } else {
      var role: Role = roleDao.findRoleById(CommonConstant.ROLE_ADMIN)
      entity.role = role
    }
    var department: Deparment = departmentDao.findDeparmentById(1)
    if (department != null) {
      if (department.deparmentName != null) {
        entity.deparment = department
      }
    }
    userDAO.save(entity)
  }

  def updateUser(userUpdate: UpdateUserForm): Int = {
    var u: User = new User
    u.email = userUpdate.email.asInstanceOf[String]
    u.name = userUpdate.name.asInstanceOf[String]
    u.fullName = userUpdate.fullName.asInstanceOf[String]
    u.dateBorn = userUpdate.dateBorn.asInstanceOf[Date]
    u.passWord = userUpdate.passWord.asInstanceOf[String]
    u.emailUpper = userUpdate.emailUpper.asInstanceOf[String]
    u.role = (roleDao.findRoleById(userUpdate.roleId)).asInstanceOf[Role]
    u.deparment = departmentDao.findDeparmentById(userUpdate.deparmentId).asInstanceOf[Deparment]
    userDAO.update(u)
  }

    def registerUser(user: UpdateUserForm): Int = {
    var u: User = new User
    u.email = user.email.asInstanceOf[String]
    u.name = user.name.asInstanceOf[String]
    u.fullName = user.fullName.asInstanceOf[String]
    u.dateBorn = user.dateBorn.asInstanceOf[Date]
    u.passWord = user.passWord.asInstanceOf[String]
    u.emailUpper = user.emailUpper.asInstanceOf[String]
    u.role = (roleDao.findRoleById(CommonConstant.ROLE_USER)).asInstanceOf[Role]
    u.deparment = departmentDao.findDeparmentById(user.deparmentId).asInstanceOf[Deparment]
    userDAO.save(u)
  }
    
  def serviceLoginAccount(info: UserLoginAccountForm): Int = {
    var result: Int = 0
    var user: User = userDAO.findUserByEmailPassword(info.email, info.password)
    if (user.email == null && user.passWord == null) {
      result = 1
    } else if (user.email != null && user.passWord != null) {
      result = 2
    }
    result
  }

  def findUserSubtractEmail(email: String): JList[User] = {
    userDAO.findUserSubtractEmail(email)
  }
}