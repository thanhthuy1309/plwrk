package forms

import java.util.Date

case class UserGoogleForm(email: String, name: String)
case class UserLoginAccountForm(email: String, password: String)
case class UpdateUserForm(email:String,name: String,
    fullName: String,
    dateBorn: Date,
    roleId: String,
    passWord: String,
    emailUpper: String,
    deparmentId: Int)
