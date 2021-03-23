package u04lab.code

import u04lab.code.Lists._
import org.junit.jupiter.api.Assertions._
import org.junit.jupiter.api.Test

class StudentTests {

    val cPPS = Course("PPS", "Viroli")
    val cPCD = Course("PCD", "Ricci")
    val cSDR = Course("SDR", "D'Angelo")
    val s1 = Student("Mario", 2015)
    val s2 = Student("Gino", 2016)
    val s3 = Student("Rino") //defaults to 2017
    s1.enrolling(cPPS, cPCD)
    s2.enrolling(cPPS)
    s3.enrolling(cPPS, cPCD, cSDR)

    @Test
    def testStudent(): Unit = {
        assertEquals("Mario", s1.name)
        assertEquals(2015, s1.year)
        assertEquals("Gino", s2.name)
        assertEquals(2016, s2.year)
        assertEquals("Rino", s3.name)
        assertEquals(2017, s3.year)
    }

    @Test
    def testCourse(): Unit = {
        assertEquals("PPS", cPPS.name)
        assertEquals("Viroli", cPPS.teacher)
        assertEquals("PCD", cPCD.name)
        assertEquals("Ricci", cPCD.teacher)
        assertEquals("SDR", cSDR.name)
        assertEquals("D'Angelo", cSDR.teacher)
    }

    @Test
    def testStudentCourses(): Unit = {
        assertEquals(List("PPS", "PCD"), s1.courses)
        assertEquals(List("PPS"), s2.courses)
        assertEquals(List("PPS", "PCD", "SDR"), s3.courses)
    }

    @Test
    def testStudentHasTeacher(): Unit = {
        assertTrue(s1 hasTeacher "Ricci")
        assertTrue(s2 hasTeacher "Viroli")
        assertTrue(s3 hasTeacher "D'Angelo")
        assertFalse(s1 hasTeacher "D'Angelo")
    }

    @Test
    def testSameTeacher(): Unit = {
        val differentTeachers = List(cPPS, cPCD, cSDR)
        val sameTeachers = List(cPPS, cPPS, cPPS)
        val emptyList: List[Course] = List()
        differentTeachers match {
            case sameTeacher(t) => fail(s"$t was wrongly identified as a common teacher")
            case _ =>
        }
        sameTeachers match {
            case sameTeacher(t) => assertEquals("Viroli", t)
            case _ => fail("No common teacher was found")
        }
        emptyList match {
            case sameTeacher(t) => fail(s"$t was wrongly identified as a common teacher on an empty list")
            case _ =>
        }
    }
}
