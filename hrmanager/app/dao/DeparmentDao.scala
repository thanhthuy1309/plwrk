package dao

import java.util.{ List => JList }

import com.google.inject.ImplementedBy

import daoImpl.DeparmentDaoImpl
import entity.Deparment
import javax.persistence.Entity
import javax.persistence.Table

@ImplementedBy(classOf[DeparmentDaoImpl])
trait DepartemntDao {
  def findDeparmentAll: JList[Deparment]
  def findDeparmentById(deparmentId:Int): Deparment
}