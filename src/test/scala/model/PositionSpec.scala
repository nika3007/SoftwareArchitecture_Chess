package model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class PositionSpec extends AnyWordSpec with Matchers:

  "A Position" should {

    "be valid when row and col are within 0-7" in {
      Position(0, 0).isValid shouldBe true
      Position(7, 7).isValid shouldBe true
      Position(4, 4).isValid shouldBe true
    }

    "be invalid when row is negative" in {
      Position(-1, 0).isValid shouldBe false
    }

    "be invalid when col is negative" in {
      Position(0, -1).isValid shouldBe false
    }

    "be invalid when row is 8 or more" in {
      Position(8, 0).isValid shouldBe false
    }

    "be invalid when col is 8 or more" in {
      Position(0, 8).isValid shouldBe false
    }

    "be equal to another position with same row and col" in {
      Position(3, 4) shouldBe Position(3, 4)
    }

    "not be equal to a position with different row" in {
      Position(3, 4) should not be Position(4, 4)
    }

    "not be equal to a position with different col" in {
      Position(3, 4) should not be Position(3, 5)
    }
  }