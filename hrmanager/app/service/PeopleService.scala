package service

import java.util.{ List => JList }

import com.google.inject.ImplementedBy

import entity.People
import javax.persistence.Entity
import javax.persistence.Table
import serviceImpl.PeopleServiceImpl

@ImplementedBy(classOf[PeopleServiceImpl])
trait PeopleService {
  def findAll: JList[People]
  def save(artist: People): Unit
}