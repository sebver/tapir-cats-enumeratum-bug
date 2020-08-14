import cats.data.NonEmptyList
import enumeratum._
import io.circe.generic.semiauto.deriveCodec
import sttp.tapir._
import sttp.tapir.json.circe._
import sttp.tapir.docs.openapi._
import sttp.tapir.openapi.circe.yaml._
import sttp.tapir.codec.enumeratum._
import sttp.tapir.integ.cats.codec._

object Main extends App {

  final case class WithNel(testEnums: NonEmptyList[TestEnum])
  object WithNel {
    implicit val codec = deriveCodec[WithNel]
  }

  final case class WithList(testEnums: List[TestEnum])
  object WithList {
    implicit val codec = deriveCodec[WithList]
  }

  sealed abstract class TestEnum(override val entryName: String) extends EnumEntry
  object TestEnum extends Enum[TestEnum] with CirceEnum[TestEnum] {
    val values = findValues

    case object First extends TestEnum("first")
    case object Second extends TestEnum("second")
  }

  val endpoint1 = endpoint.in(jsonBody[WithNel])
  val endpoint2 = endpoint.in(jsonBody[WithList])

  println(List(endpoint1).toOpenAPI("api", "1.0").toYaml)
  println(List(endpoint2).toOpenAPI("api", "1.0").toYaml)

}
