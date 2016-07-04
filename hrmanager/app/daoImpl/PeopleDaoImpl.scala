package daoImpl

import java.util.{ List => JList }

import com.google.inject.ImplementedBy

import dao.PeopleDao
import entity.People
import javax.persistence.Entity
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.Table
import javax.persistence.Persistence

class PeopleDaoImpl  extends PeopleDao {
  
  private var entityManager = Persistence.createEntityManagerFactory("defaultPersistenceUnit")
    
  def save(artist: People):Unit = {
   val entityManager1 = entityManager.createEntityManager()
   def transaction = entityManager1.getTransaction
   transaction.begin()
   try {
     entityManager1.persist(artist)
     transaction.commit()
   }
    catch {
      case exception: Throwable => 
        if (transaction != null && transaction.isActive)
            transaction.rollback()
        throw exception
    }
    finally 
      entityManager1.close()
  }
        
  def findAll: JList[People] = entityManager.createEntityManager().createNamedQuery("AccountAuthorityService.findAccountAuthorities").getResultList.asInstanceOf[JList[People]]
  
}