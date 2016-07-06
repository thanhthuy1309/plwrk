package constants
import scala.concurrent.duration._
object GoogleConstant {
  val CLIENT_ID: String = "328338891021-ee9ueunvbo7l0d94elh3bps43adlh2p6.apps.googleusercontent.com"
  val CLIENT_SECRET: String = "-1aVh3RWhGw0GTXYnUeM0VJ6"
  val REDIRECT_URL: String = "http://localhost:9000/login"
  val SCOPE = """https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.profile&state=%2Fprofile&"""
  val GOOGLE_URL_LOGIN = "https://accounts.google.com/o/oauth2/auth?client_id=" + CLIENT_ID + "&redirect_uri=" + REDIRECT_URL + "&response_type=code&scope=" + SCOPE
  val TIMEOUT = 5.seconds
  val GOOGLE_URL_TOKEN = "https://accounts.google.com/o/oauth2/token"
  val GOOGLE_URL_OAUTH = "https://www.googleapis.com/oauth2/v1/userinfo?access_token="
}