package entity

import java.util.Date

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence._

@Entity
@Table(name = "user")
class User {

  @Id
  @Column(name = "email", nullable = false, length = 255)
  var email: String = _

  @Column(name = "name", nullable = false, length = 255)
  var name: String = _

  @Column(name = "fullname", nullable = false,length = 500)
  var fullName: String = _

  @Column(name = "dateborn")
  @Temporal(TemporalType.DATE)
  var dateBorn: Date = _

  @ManyToOne
  @JoinColumn(name = "roleid", referencedColumnName = "roleid")
  var role: Role = _

  @Column(name = "password")
  var passWord: String = _

  @Column(name = "emailupper", length = 255)
  var emailUpper: String = _
  
  @ManyToOne
  @JoinColumn(name = "deparmentid", referencedColumnName = "deparmentid")
  var deparment: Deparment = _
  
}