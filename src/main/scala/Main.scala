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

  val nelEndpoint = endpoint.in(jsonBody[WithNel])
  val listEndpoint = endpoint.in(jsonBody[WithList])

  println(List(nelEndpoint).toOpenAPI("api", "1.0").toYaml)
  /* Prints:
  schemas:
  WithNel:
    required:
    - testEnums
    type: object
    properties:
      testEnums:
        type: array
        items:
          type: string
        minItems: 1
   */

  println(List(listEndpoint).toOpenAPI("api", "1.0").toYaml)
  /* Prints:
  schemas:
    WithList:
      type: object
      properties:
        testEnums:
          type: array
          items:
            type: string
            enum:
            - first
            - second
  */

}
