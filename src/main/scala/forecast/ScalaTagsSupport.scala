package forecast

import akka.http.scaladsl.marshalling._
import akka.http.scaladsl.model.{ContentType, HttpEntity, MediaTypes}

/** Utilities to support marshalling ScalaTags */
object ScalaTagsSupport {
  implicit val `ScalaTags marshaller`: ToEntityMarshaller[scalatags.Text.TypedTag[String]] =
    Marshaller.withOpenCharset(MediaTypes.`text/html`) { (value, charset) ⇒
      HttpEntity(ContentType(MediaTypes.`text/html`, charset), "<!DOCTYPE html>\n" + value.toString())
    }
}
