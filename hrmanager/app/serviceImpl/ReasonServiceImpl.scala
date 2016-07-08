package serviceImpl

import java.util.{ List => JList }
import entity.Reason
import service.ReasonService
import javax.inject.Inject
import dao.ReasonDao

class ReasonServiceImpl extends ReasonService {
  
  @Inject
  private var ReasonDao: ReasonDao = _
  
  def findReasonAll: JList[Reason] = {
    ReasonDao.findReasonAll
  }
  
  def findReasonById(ReasonId:Int): Reason =  {
    ReasonDao.findReasonById(ReasonId)
  }
}