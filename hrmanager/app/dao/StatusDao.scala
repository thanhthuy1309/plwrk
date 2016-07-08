package dao

import java.util.{ List => JList }

import com.google.inject.ImplementedBy

import javax.persistence.Entity
import javax.persistence.Table
import daoImpl.StatusDaoImpl
import entity.Status

@ImplementedBy(classOf[StatusDaoImpl])
trait StatusDao {
  def findStatusAll: JList[Status]
  def findStatusById(StatusId:Int): Status
}