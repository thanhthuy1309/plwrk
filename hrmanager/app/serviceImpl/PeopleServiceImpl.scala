package serviceImpl

import java.util.{ List => JList }

import com.google.inject.ImplementedBy

import dao.PeopleDao
import daoImpl.PeopleDaoImpl
import entity.People
import javax.inject.Inject
import javax.persistence.Entity
import javax.persistence.Table
import service.PeopleService

class PeopleServiceImpl extends PeopleService{
  
  @Inject
  private var artistDAO: PeopleDao = _
  
  def findAll: JList[People] = artistDAO.findAll
  
  def save(artist: People): Unit = artistDAO.save(artist)

}