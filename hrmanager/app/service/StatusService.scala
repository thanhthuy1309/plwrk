package service

import java.util.{ List => JList }

import com.google.inject.ImplementedBy

import entity.Status
import javax.persistence.Entity
import javax.persistence.Table
import serviceImpl.StatusServiceImpl
import forms._

@ImplementedBy(classOf[StatusServiceImpl])
trait StatusService {
  def findStatusAll: JList[Status]
  def findStatusById(StatusId:Int): Status
}