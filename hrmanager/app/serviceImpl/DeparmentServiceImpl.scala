package serviceImpl

import java.util.{ List => JList }
import entity.Deparment
import service.DeparmentService
import javax.inject.Inject
import dao.DepartemntDao

class DeparmentServiceImpl extends DeparmentService {
  
  @Inject
  private var deparmentDao: DepartemntDao = _
  
  def findDeparmentAll: JList[Deparment] = {
    deparmentDao.findDeparmentAll
  }
  
  def findDeparmentById(deparmentId:Int): Deparment =  {
    deparmentDao.findDeparmentById(deparmentId)
  }
}