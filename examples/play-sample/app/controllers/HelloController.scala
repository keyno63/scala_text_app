package controllers

import javax.inject.{ Inject, Singleton }
import play.api.i18n.{ I18nSupport, Messages }
import play.api.mvc.{ AbstractController, Action, AnyContent, ControllerComponents, Request }

@Singleton
class HelloController @Inject() (cc: ControllerComponents) extends AbstractController(cc) with I18nSupport {

  val logger = play.api.Logger("hello")

  def get(name: Option[String]): Action[AnyContent] =
    Action { implicit request: Request[AnyContent] =>
      logger.info(s"name parameter: $name")
      Ok {
        name
          .map(s => Messages("hello", s))
          .getOrElse(Messages("noQuery"))
      }
    }
}
