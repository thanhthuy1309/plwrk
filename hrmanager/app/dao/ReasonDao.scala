package dao

import java.util.{ List => JList }

import com.google.inject.ImplementedBy

import javax.persistence.Entity
import javax.persistence.Table
import daoImpl.ReasonDaoImpl
import entity.Reason

@ImplementedBy(classOf[ReasonDaoImpl])
trait ReasonDao {
  def findReasonAll: JList[Reason]
  def findReasonById(ReasonId:Int): Reason
}