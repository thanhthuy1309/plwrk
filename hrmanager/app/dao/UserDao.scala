package dao

import java.util.{ List => JList }
import entity.User
import com.google.inject.ImplementedBy
import daoImpl.UserDaoImpl

@ImplementedBy(classOf[UserDaoImpl])
trait UserDao {
  def findUserAll: JList[User]
  def findUserByEmail(email: String): User
  def save(user: User): Int
}