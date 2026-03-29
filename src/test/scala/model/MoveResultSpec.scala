package model

package model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class MoveResultSpec extends AnyWordSpec with Matchers:

  "A MoveResult" should {

    "be Selected" in {
      MoveResult.Selected shouldBe MoveResult.Selected
    }

    "be Deselected" in {
      MoveResult.Deselected shouldBe MoveResult.Deselected
    }

    "be Moved" in {
      MoveResult.Moved shouldBe MoveResult.Moved
    }

    "be Capture" in {
      MoveResult.Capture shouldBe MoveResult.Capture
    }

    "be Invalid" in {
      MoveResult.Invalid shouldBe MoveResult.Invalid
    }

    "be GameOver with a winner" in {
      MoveResult.GameOver(Color.White) shouldBe MoveResult.GameOver(Color.White)
    }

    "not be equal to a different GameOver winner" in {
      MoveResult.GameOver(Color.White) should not be MoveResult.GameOver(Color.Black)
    }
  }