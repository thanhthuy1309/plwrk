package entity

import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

@Entity
@Table(name = "people")
class People {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  var id: Long = _

  var name: String = _

  var age: Int = _
}