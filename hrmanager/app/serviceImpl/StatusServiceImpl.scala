package serviceImpl

import java.util.{ List => JList }

import com.google.inject.ImplementedBy

import constants.CommonConstant
import dao.StatusDao
import entity.Status
import forms.UpdateUserForm
import forms.UserGoogleForm
import forms.UserLoginAccountForm
import javax.inject.Inject
import javax.persistence.Entity
import javax.persistence.Table
import service.StatusService
import java.util.Date

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