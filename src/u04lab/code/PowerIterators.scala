package u04lab.code

import Optionals._
import Lists._
import Streams._

import scala.util.Random

trait PowerIterator[A] {
    def next(): Option[A]

    def allSoFar(): List[A]

    def reversed(): PowerIterator[A]
}

trait PowerIteratorsFactory {

    def incremental(start: Int, successive: Int => Int): PowerIterator[Int]

    def fromList[A](list: List[A]): PowerIterator[A]

    def randomBooleans(size: Int): PowerIterator[Boolean]
}

object PowerIteratorsFactory {
    def apply(): PowerIteratorsFactory = new PowerIteratorsFactoryImpl()

    private class PowerIteratorsFactoryImpl extends PowerIteratorsFactory {

        override def incremental(start: Int, successive: Int => Int): PowerIterator[Int] =
            fromStream(Stream.iterate(start)(successive))

        override def fromList[A](list: List[A]): PowerIterator[A] = ???

        override def randomBooleans(size: Int): PowerIterator[Boolean] =
            fromStream(Stream.take(Stream.generate(Random.nextBoolean()))(size))

        private def fromStream[A](stream: Stream[A]): PowerIterator[A] = new PowerIteratorImpl(stream)
    }

    private class PowerIteratorImpl[A](private var stream: Stream[A]) extends PowerIterator[A] {
        var past: List[A] = List.Nil()

        override def next(): Option[A] = {
            val first = Stream.first(stream)
            first match {
                case Option.Some(x) => {
                    past = List.append(past, List.Cons(x, List.Nil()))
                    stream = Stream.skip(stream)(1)
                    first
                }
                case Option.None() => first
            }
        }

        override def allSoFar(): List[A] = past

        override def reversed(): PowerIterator[A] = new PowerIteratorImpl(Stream.fromList(List.reverse(past)))
    }
}