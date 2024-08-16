package com.example

import org.apache.pekko.actor.ActorSystem
import org.apache.pekko.http.scaladsl.Http
import org.apache.pekko.http.scaladsl.server.Directives._
import org.apache.pekko.stream.ActorMaterializer

import scala.io.StdIn

object WebApi {
  def main(args: Array[String]): Unit = {
    // ActorSystemとMaterializerの作成
    implicit val system = ActorSystem("web-api-system")
    implicit val materializer = ActorMaterializer()
    implicit val executionContext = system.dispatcher

    // ルート定義
    val route =
      path("hello") {
        get {
          complete("Hello, Pekko!")
        }
      } ~
      path("goodbye") {
        get {
          complete("Goodbye, Pekko!")
        }
      }

    // サーバーを起動
    val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)

    println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
    StdIn.readLine() // サーバー停止を待機

    bindingFuture
      .flatMap(_.unbind())
      .onComplete(_ => system.terminate())
  }
}
