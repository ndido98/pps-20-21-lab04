package u04lab.code

import org.junit.jupiter.api.Assertions._
import org.junit.jupiter.api.Test

class ComplexTests {
    @Test
    def testComponents(): Unit = {
        val complex = Complex(1.0, 2.0)
        assertEquals(1.0, complex.re)
        assertEquals(2.0, complex.im)
    }

    @Test
    def testToString(): Unit = {
        assertEquals("ComplexImpl(1.0,2.0)", Complex(1.0, 2.0).toString)
    }

    @Test
    def testEquals(): Unit = {
        assertEquals(Complex(1.0, 2.0), Complex(1.0, 2.0))
        assertNotEquals(Complex(0.0, 0.0), Complex(1.0, 2.0))
    }

    @Test
    def testSum(): Unit = {
        assertEquals(Complex(5.0, 6.0), Complex(4.0, 2.0) + Complex(1.0, 4.0))
    }

    @Test
    def testProduct(): Unit = {
        assertEquals(Complex(1.0, 0.0), Complex(1.0, 0.0) * Complex(1.0, 0.0))
        assertEquals(Complex(0.0, 5.0), Complex(1.0, 2.0) * Complex(2.0, 1.0))
    }
}
