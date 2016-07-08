package serviceImpl

import java.util.{ List => JList }
import entity.Status
import service.StatusService
import javax.inject.Inject
import dao.StatusDao

class StatusServiceImpl extends StatusService {
  
  @Inject
  private var StatusDao: StatusDao = _
  
  def findStatusAll: JList[Status] = {
    StatusDao.findStatusAll
  }
  
  def findStatusById(StatusId:Int): Status =  {
    StatusDao.findStatusById(StatusId)
  }
}