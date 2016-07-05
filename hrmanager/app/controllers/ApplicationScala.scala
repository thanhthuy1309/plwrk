package controllers

import java.io.File
import javax.inject.Inject

import org.apache.commons.mail.EmailAttachment
import play.api.Environment
import play.api.libs.mailer._
import play.api.mvc.{Action, Controller}

class ApplicationScala @Inject()(mailer: MailerClient, environment: Environment) extends Controller {

  def send = Action {
    val cid = "1234"
    val email = Email(
      "Text mail Again",
      "HR Manager <thanhthuy13091992@gmail.com>",
      Seq("HiuHiu <ptdung0312@gmail.com >"),
      attachments = Seq(
        AttachmentFile("aaa.jpg", new File(environment.classLoader.getResource("public/img/aaa.jpg").getPath), contentId = Some(cid))
//        AttachmentData("data.txt", "data".getBytes, "text/plain", Some("Simple data"), Some(EmailAttachment.INLINE))
      ),
      bodyText = Some("Hiu moi, hiu moi"),
      bodyHtml = Some(s"""<html><body><p>An <b>html</b> message with cid <img src="cid:$cid"></p></body></html>""")
    )
    val id = mailer.send(email)
    Ok(s"Email $id sent!")
  }

  def sendWithCustomMailer = Action {
    val mailer = new SMTPMailer(SMTPConfiguration("typesafe.org", 1234))
    val id = mailer.send(Email("Simple email", "Mister FROM <from@email.com>"))
    Ok(s"Email $id sent!")
  }
}