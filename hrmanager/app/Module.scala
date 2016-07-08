

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import utils.DataBaseUtils

class Module extends AbstractModule {
  def configure() = {
    DataBaseUtils.persitence = DataBaseUtils.createEntityManagerFactory()
  }
}