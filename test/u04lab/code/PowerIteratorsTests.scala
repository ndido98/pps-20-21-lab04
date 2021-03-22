package u04lab.code

import Optionals._
import Lists._
import Lists.List._
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions._

class PowerIteratorsTests {

    val factory = PowerIteratorsFactory()

    @Test
    def testIncremental() {
        val pi = factory.incremental(5, _ + 2) // pi produce 5,7,9,11,13,...
        assertEquals(Option.of(5), pi.next)
        assertEquals(Option.of(7), pi.next)
        assertEquals(Option.of(9), pi.next)
        assertEquals(Option.of(11), pi.next)
        assertEquals(List.Cons(5, List.Cons(7, List.Cons(9, List.Cons(11, List.Nil())))), pi.allSoFar()) // elementi già prodotti
        for (_ <- 0 until 10) {
            pi.next() // procedo in avanti per un po'...
        }
        assertEquals(Option.of(33), pi.next()) // sono arrivato a 33
    }

   @Test
   def testRandom(): Unit = { // semi-automatico, si controlleranno anche le stampe a video
        val pi = factory.randomBooleans(4) // pi produce 4 booleani random
        val b1 = pi.next
        val b2 = pi.next
        val b3 = pi.next
        val b4 = pi.next
        println(b1 + " " + b2 + " " + b3 + " " + b4) // stampo a video... giusto per vedere se sono proprio random...

        assertFalse(!Option.isEmpty(pi.next)) // ne ho già prodotti 4, quindi il prossimo è un opzionale vuoto

        assertEquals(pi.allSoFar, List.Cons(b1, List.Cons(b2, List.Cons(b3, List.Cons(b4, Nil()))))) // ho prodotto proprio b1,b2,b3,b4
    }
}