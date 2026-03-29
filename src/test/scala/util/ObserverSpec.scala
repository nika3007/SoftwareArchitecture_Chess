package util

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class ObserverSpec extends AnyWordSpec with Matchers:

  class TestObserver extends Observer:
    var updated = false
    override def update: Boolean =
      updated = true
      true

  class TestObservable extends Observable

  "An Observable" should {

    "notify observers when notifyObservers is called" in {
      val observable = new TestObservable
      val observer   = new TestObserver
      observable.add(observer)
      observable.notifyObservers
      observer.updated shouldBe true
    }

    "not notify removed observers" in {
      val observable = new TestObservable
      val observer   = new TestObserver
      observable.add(observer)
      observable.remove(observer)
      observable.notifyObservers
      observer.updated shouldBe false
    }

    "notify multiple observers" in {
      val observable = new TestObservable
      val observer1  = new TestObserver
      val observer2  = new TestObserver
      observable.add(observer1)
      observable.add(observer2)
      observable.notifyObservers
      observer1.updated shouldBe true
      observer2.updated shouldBe true
    }
  }