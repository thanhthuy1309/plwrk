package controllers

import play.api._
import play.api.mvc._
import entity.People
import play.api.libs.json.JsObject
import play.api.libs.json.JsNumber
import play.api.libs.json.JsString
import play.api.libs.json.Json.toJson
import play.api.data.validation.Constraints._
import service.PeopleService
import javax.inject.{ Inject, Named }
import play.api.data.Form
import play.api.data.Forms.{ mapping, longNumber, nonEmptyText, number }
import scala.concurrent.{ ExecutionContext, Future }
import play.api.i18n.I18nSupport
import play.api.i18n.MessagesApi
import play.api.libs
import play.api.libs.ws._
import constants._
import utils._


class Application @Inject() (val messagesApi: MessagesApi, ws: WSClient)(implicit ec: ExecutionContext) extends Controller with I18nSupport {

  @Inject
  private var artistService: PeopleService = _

  val clientId = "328338891021-ee9ueunvbo7l0d94elh3bps43adlh2p6.apps.googleusercontent.com"
  val clientSecret = "-1aVh3RWhGw0GTXYnUeM0VJ6"
  val redirectUrl = "http://localhost:9000/login"

  def loginGoogle = Action {
    val scope = """https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.profile&state=%2Fprofile&"""
    Redirect("https://accounts.google.com/o/oauth2/auth?client_id=" + GoogleConstant.CLIENT_ID + "&redirect_uri=" + redirectUrl + "&response_type=code&scope=" + scope)
  }

  private val personForm: Form[CreatePersonForm] = Form(
    mapping(
      "name" -> nonEmptyText,
      "age" -> number(min = 0, max = 100))(CreatePersonForm.apply)(CreatePersonForm.unapply))

  def index = Action {
    print("artistService:  " + artistService)
    val mylist = artistService.findAll
    import collection.JavaConversions._
    val typList = mylist.toList.asInstanceOf[List[People]]
    Ok(toJson(renderArticleJson(typList)))
  }

  def add = Action {
    Ok(views.html.index(personForm))
  }
  def strip(quoted: String): String = {
    quoted.filter(char => char != '\"')
  }
  def login(state: String, code: String) = Action {
    val postBody = "code=" + code + "&client_id=" + clientId + "&client_secret=" + clientSecret + "&redirect_uri=" + redirectUrl + "&grant_type=authorization_code"
    val body = ws.url("https://accounts.google.com/o/oauth2/token").withHeaders("Content-Type" -> "application/x-www-form-urlencoded").post(postBody)
    val accessJson = body.value.get.get.json

    var aa = accessJson.\("access_token").get.toString()
    val accessToken = strip(aa.toString)
    println("email: " + accessToken)
    val user1 = ws.url("https://www.googleapis.com/oauth2/v1/userinfo?access_token=" + accessToken).withHeaders("Content-Type" -> "application/x-www-form-urlencoded").get()
    var result = user1.value.get.get.json
    var email = strip((result.\("email").get.toString()).toString())
    println(email)
    var name = strip((result.\("name").get.toString()).toString())
    println(name)

    Ok(views.html.index(personForm))
  }
  private def renderArticleJson(artists: Iterable[People]): Iterable[JsObject] = {
    val listToConvert = for (artist <- artists) yield {
      JsObject(Seq(
        "id" -> JsNumber(artist.id),
        "firstName" -> JsString(artist.name)))
    }
    //	 logger.info("The converted *********** " + listToConvert)
    listToConvert
  }

  /**
   * The add person action.
   *
   * This is asynchronous, since we're invoking the asynchronous methods on PersonRepository.
   */
  def addPerson = Action { implicit request =>
    val newProductForm = personForm.bindFromRequest()

    // Bind the form first, then fold the result, passing a function to handle errors, and a function to handle succes.
    personForm.bindFromRequest.fold(
      // The error function. We return the index page with the error form, which will render the errors.
      // We also wrap the result in a successful future, since this action is synchronous, but we're required to return
      // a future because the person creation function returns a future.
      errorForm => {
        BadRequest(views.html.index(errorForm))
      },
      // There were no errors in the from, so create the person.
      person => {
        // If successful, we simply redirect to the index page.
        var p: People = new People
        p.name = person.name
        p.age = person.age
        artistService.save(p)
        Redirect(routes.Application.index())
      })
  }

}

case class CreatePersonForm(name: String, age: Int)