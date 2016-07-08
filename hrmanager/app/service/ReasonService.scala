package service

import java.util.{ List => JList }
import com.google.inject.ImplementedBy
import entity.Reason
import serviceImpl.ReasonServiceImpl

@ImplementedBy(classOf[ReasonServiceImpl])
trait ReasonService {
  def findReasonAll: JList[Reason]
  def findReasonById(ReasonId:Int): Reason
}