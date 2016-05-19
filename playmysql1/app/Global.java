import play.Application;
import play.GlobalSettings;
import play.i18n.Lang;

/**
 * Provide initialization code for the digits application.
 * @author Philip Johnson
 */
public class Global extends GlobalSettings {

  /**
   * Initialize the system with some sample contacts.
   * @param app The application.
   */
  public void onStart(Application app) {
	 new Lang(Lang.forCode("ja"));
  }
  
  
}
