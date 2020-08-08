package controllers

import javax.inject.Inject
import play.api.data.Form
import play.api.data.Forms.{ mapping, text }
import play.api.i18n.{ I18nSupport, Messages }
import play.api.mvc.{ AbstractController, Action, AnyContent, ControllerComponents }
import play.api.libs.json.Json
import java.time.OffsetDateTime

import models.{ Meta, Post, PostRequest, Response }
import repositories.PostRepository

class PostsController @Inject() (cc: ControllerComponents) extends AbstractController(cc) with I18nSupport {

  private[this] val form = Form(
    mapping(
      "post" -> text(minLength = 1, maxLength = 10)
    )(PostRequest.apply)(PostRequest.unapply)
  )

  def get: Action[AnyContent] = Action { implicit request =>
    Ok(Json.toJson(Response(Meta(200), Some(Json.obj("posts" -> Json.toJson(PostRepository.findAll))))))
  }

  def post: Action[AnyContent] = Action { implicit request =>
    form.bindFromRequest.fold(
      error => {
        val errorMessage = Messages(error.errors("post").head.message)
        BadRequest(Json.toJson(Response(Meta(400, Some(errorMessage)))))
      },
      postRequest => {
        val post = Post(postRequest.body, OffsetDateTime.now)
        PostRepository.add(post)
        Ok(Json.toJson(Response(Meta(200))))
      }
    )
  }
}
