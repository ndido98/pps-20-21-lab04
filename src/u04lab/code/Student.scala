package u04lab.code

import Lists._
import u04lab.code.Lists.List._ // import custom List type (not the one in Scala stdlib)

trait Student {
    def name: String

    def year: Int

    def enrolling(courses: Course*): Unit // the student participates to a Course
    def courses: List[String] // names of course the student participates to
    def hasTeacher(teacher: String): Boolean // is the student participating to a course of this teacher?
}

trait Course {
    def name: String

    def teacher: String
}

object Student {
    def apply(name: String, year: Int = 2017): Student = new StudentImpl(name, year)

    private class StudentImpl(override val name: String, override val year: Int) extends Student {
        var _courses: List[Course] = Nil()

        override def enrolling(courses: Course*): Unit = _courses = append(_courses, coursesToList(courses))

        override def courses: List[String] = map(_courses)(c => c.name)

        override def hasTeacher(teacher: String): Boolean = contains(map(_courses)(c => c.teacher))(teacher)

        private def coursesToList(courses: Seq[Course]): List[Course] = {
            var res: List[Course] = Nil()
            for (c <- courses) {
                res = append(res, Cons(c, Nil()))
            }
            res
        }
    }
}

object Course {
    def apply(name: String, teacher: String): Course = CourseImpl(name, teacher)

    private case class CourseImpl(name: String, teacher: String) extends Course
}

/** Hints:
 * - simply implement Course, e.g. with a case class
 * - implement Student with a StudentImpl keeping a private Set of courses
 * - try to implement in StudentImpl method courses with map
 * - try to implement in StudentImpl method hasTeacher with map and find
 * - check that the two println above work correctly
 * - refactor the code so that method enrolling accepts a variable argument Course*
 */
