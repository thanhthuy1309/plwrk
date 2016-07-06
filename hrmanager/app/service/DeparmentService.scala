package service

import java.util.{ List => JList }
import com.google.inject.ImplementedBy
import entity.Deparment
import serviceImpl.DeparmentServiceImpl

@ImplementedBy(classOf[DeparmentServiceImpl])
trait DeparmentService {
  def findDeparmentAll: JList[Deparment]
  def findDeparmentById(deparmentId:Int): Deparment
}