package service

import java.util.{ List => JList }

import com.google.inject.ImplementedBy

import entity.Status
import serviceImpl.StatusServiceImpl

@ImplementedBy(classOf[StatusServiceImpl])
trait StatusService {
  def findStatusAll: JList[Status]
  def findStatusById(StatusId:Int): Status
}