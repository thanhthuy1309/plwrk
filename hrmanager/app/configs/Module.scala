

package configs

import com.google.inject.AbstractModule
import utils.DataBaseUtils

class Module extends AbstractModule {
  def configure() = {
    DataBaseUtils.persitence = DataBaseUtils.createEntityManagerFactory()
  }
}