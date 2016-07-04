package dao

import java.util.{List => JList }
import entity.People
import com.google.inject.ImplementedBy
import daoImpl.PeopleDaoImpl

@ImplementedBy(classOf[PeopleDaoImpl])
trait PeopleDao {
  def findAll: JList[People]
  def save(artist: People): Unit
}