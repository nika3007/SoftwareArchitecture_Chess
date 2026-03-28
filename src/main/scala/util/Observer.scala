package util

trait Observer:
  def update: Boolean

trait Observable:
  private var subscribers: List[Observer] = Nil
  def add(o: Observer): Unit    = subscribers = o :: subscribers
  def remove(o: Observer): Unit = subscribers = subscribers.filterNot(_ == o)
  def notifyObservers: Unit     = subscribers.foreach(_.update)