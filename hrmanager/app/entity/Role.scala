package entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "role")
class Role {

  @Id
  @Column(name = "roleid", nullable = false, length = 1)
  var roleId: String = _

  @Column(name = "rolename", nullable = false, length = 255)
  var roleName: String = _
}