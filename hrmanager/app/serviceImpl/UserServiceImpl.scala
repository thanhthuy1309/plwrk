package serviceImpl

import java.util.{ List => JList }

import com.google.inject.ImplementedBy

import dao.UserDao
import daoImpl.PeopleDaoImpl
import entity.User
import javax.inject.Inject
import javax.persistence.Entity
import javax.persistence.Table
import service.UserService
import forms._

class UserServiceImpl extends UserService{
  
  @Inject
  private var userDAO: UserDao = _
  
  def findUserAll: JList[User] = userDAO.findUserAll
  
  def findUserByEmail(email: String): User = userDAO.findUserByEmail(email)
  
  def saveByGoogle(user: UserGoogleForm): Int = {
    var entity: User = new User
    entity.email = user.email
    entity.name = user.name
    userDAO.save(entity)
  }
  
  def updateUser(user: User): Int = {
    userDAO.update(user)
  }

}