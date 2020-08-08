package controllers

import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneServerPerSuite
import play.api.Application
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.libs.json.Json
import play.api.libs.json.JsValue
import play.api.test.WsTestClient

import scala.concurrent.Await
import scala.concurrent.duration.Duration

class PostsSpec extends PlaySpec with GuiceOneServerPerSuite {

  override def fakeApplication(): Application =
    new GuiceApplicationBuilder()
      .configure("db.default.driver" -> "org.h2.Driver", "db.default.url" -> "jdbc:h2:mem:test;MODE=MYSQL")
      .build()

  "GET /posts" should {
    "何も投稿しない場合は空の配列を返す" in {
      WsTestClient.withClient { ws =>
        val response = Await.result(ws.url(s"http://localhost:$port/posts").get(), Duration.Inf)
        assert(response.status === 200)
        assert(response.json === Json.parse("""{"meta":{"status":200},"data":{"posts":[]}}"""))
      }
    }
  }

  "POST /posts" should {
    "投稿したものが返される" in {
      WsTestClient.withClient { ws =>
        val body = "test post"
        val postResponse =
          Await.result(ws.url(s"http://localhost:$port/posts").post(Map("post" -> Seq(body))), Duration.Inf)
        assert(postResponse.status === 200)
        val getResponse = Await.result(ws.url(s"http://localhost:$port/posts").get(), Duration.Inf)
        assert(getResponse.status === 200)
        assert((getResponse.json \ "meta" \ "status").as[Int] === 200)
        val posts = (getResponse.json \ "data" \ "posts").as[Array[JsValue]]
        assert(posts.length === 1)
        assert((posts(0) \ "body").as[String] === body)
      }
    }

    "空のメッセージは投稿できない" in {
      WsTestClient.withClient { ws =>
        val body = ""
        val response =
          Await.result(ws.url(s"http://localhost:$port/posts").post(Map("post" -> Seq(body))), Duration.Inf)
        assert(response.status === 400)
        assert((response.json \ "meta" \ "status").as[Int] === 400)
        assert((response.json \ "meta" \ "errorMessage").as[String] === "Please enter a message.")
      }
    }

    "長すぎるメッセージは投稿できない" in {
      WsTestClient.withClient { ws =>
        val body = "too long messages"
        val response =
          Await.result(ws.url(s"http://localhost:$port/posts").post(Map("post" -> Seq(body))), Duration.Inf)
        assert(response.status === 400)
        assert((response.json \ "meta" \ "status").as[Int] === 400)
        assert((response.json \ "meta" \ "errorMessage").as[String] === "The message is too long.")
      }
    }
  }
}
