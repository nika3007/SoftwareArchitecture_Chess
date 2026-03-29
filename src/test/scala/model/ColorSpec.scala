package model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class ColorSpec extends AnyWordSpec with Matchers:

  "A Color" should {

    "return Black as opposite of White" in {
      Color.White.opposite shouldBe Color.Black
    }

    "return White as opposite of Black" in {
      Color.Black.opposite shouldBe Color.White
    }

    "be equal to itself" in {
      Color.White shouldBe Color.White
      Color.Black shouldBe Color.Black
    }

    "not be equal to the other color" in {
      Color.White should not be Color.Black
    }
  }