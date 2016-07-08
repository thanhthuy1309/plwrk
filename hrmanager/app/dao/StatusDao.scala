package dao

import java.util.{ List => JList }
import entity.Status
import com.google.inject.ImplementedBy
import daoImpl.StatusDaoImpl

@ImplementedBy(classOf[StatusDaoImpl])
trait StatusDao {
  def findStatusAll: JList[Status]
  def findStatusById(StatusId:Int): Status
}