package service

import java.util.{ List => JList }

import com.google.inject.ImplementedBy

import entity.User
import javax.persistence.Entity
import javax.persistence.Table
import serviceImpl.UserServiceImpl
import forms._

@ImplementedBy(classOf[UserServiceImpl])
trait UserService {
  def findUserAll: JList[User]
  def findUserByEmail(email: String): User
  def saveByGoogle(user: UserGoogleForm): Int
  def updateUser(user: User): Int
}