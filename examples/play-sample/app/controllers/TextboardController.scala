package controllers

import java.time.OffsetDateTime

import javax.inject.Inject
import models.Post
import models.PostRequest
import play.api.mvc.{ AbstractController, Action, AnyContent, ControllerComponents }
import play.api.data.Form
import play.api.data.Forms._
import play.api.i18n.I18nSupport
import repositories.PostRepository

class TextboardController @Inject() (cc: ControllerComponents) extends AbstractController(cc) with I18nSupport {

  private[this] val form = Form(
    mapping(
      "post" -> text(minLength = 1, maxLength = 10).withPrefix("hogeika")
    )(PostRequest.apply)(PostRequest.unapply)
  )

  def get: Action[AnyContent] = Action(implicit request => Ok(views.html.index(PostRepository.findAll, form)))

  def post: Action[AnyContent] = Action { implicit request =>
    form.bindFromRequest.fold(
      error => BadRequest(views.html.index(PostRepository.findAll, error)),
      postRequest => {
        val post = Post(postRequest.body, OffsetDateTime.now)
        PostRepository.add(post)
        Redirect("/")
      }
    )
  }
}
