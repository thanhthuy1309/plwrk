package controllers

import play.api.mvc.{Action, Controller}
import service.UserService

import javax.inject.{ Inject, Named }

class AdminController extends Controller {
  
  @Inject
  private var userService:UserService = _
  
  def listUser() = Action {
    Ok(views.html.listUser())
  }
}