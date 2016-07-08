package utils

import javax.persistence.EntityManagerFactory
import javax.persistence.Persistence
import constants.DaoConstant

object DataBaseUtils {
  var persitence : EntityManagerFactory = null
  def createEntityManagerFactory(): EntityManagerFactory = {
    if(persitence == null) {
      persitence = Persistence.createEntityManagerFactory(DaoConstant.DEFAULT_PERSISTENCE_UNIT)
    }
    persitence
  }
}