package data


import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

import scalikejdbc.SQLSyntax

import scala.concurrent.Future

object Utils {

  private val formatter = DateTimeFormatter ofPattern "yyyy-MM-dd HH:mm:ss.SSSSSS"

  implicit class SQLContext(val sc: StringContext) extends AnyVal {

    def ql(args: Any*): SQLSyntax = {
      val strings = sc.parts.iterator
      val expressions = args.iterator
      val buf = new StringBuffer(strings.next)
      while (strings.hasNext) {
        val expressionString = SQLContext paramBuilder expressions.next
        buf append expressionString
        buf append strings.next
      }
      SQLSyntax createUnsafely buf.toString
    }

  }

  object SQLContext {

    protected def paramBuilder(param: Any): String = param match {
      case b: Boolean => b.toString
      case d: Double => d.toString
      case f: Float => f.toString
      case i: Int => i.toString
      case l: Long => l.toString
      case s: String =>
        val newS = s flatMap {
          case '\'' => "''"
          case others => others.toString
        }
        s"'$newS'"
      case v: Vector[_] => s"ARRAY[${v map paramBuilder mkString ", "}]"
      case zdt: ZonedDateTime => s"'${formatter format zdt}'::TIMESTAMPTZ"
      case RawString(string) => string
      case SimpleArray(vector) => vector map paramBuilder mkString ", "
      case AllExcludeInclude(all, exclude, include) =>
        if (all) {
          val extractedExcluded = exclude getOrElse Vector.empty
          //noinspection SqlDialectInspection
          if (extractedExcluded.nonEmpty) ql"entity NOT IN (${SQLContext SimpleArray extractedExcluded})" else paramBuilder(true)
        } else {
          val extractedIncluded = include getOrElse Vector.empty
          //noinspection SqlDialectInspection
          if (extractedIncluded.nonEmpty) ql"entity IN (${SQLContext SimpleArray extractedIncluded})" else paramBuilder(false)
        }
      case x => throw new ClassNotFoundException(s"Can not insert ${x.getClass} to database")
    }

    case class AllExcludeInclude(all: Boolean, exclude: Option[Vector[Any]], include: Option[Vector[Any]])

    case class RawString(string: String)

    case class SimpleArray(vector: Vector[Any])

  }
}
