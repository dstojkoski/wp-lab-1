package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.Course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findAllByName(String name);
    List<Course> findAllByNameLike(String name);
    List<Course> findAllByOrderByNameDesc();
    List<Course> findAllByOrderByCourseId();

//    @Query(
//            value = "select c.description, c.name, c.course_id, c.type, c.teacher_id from course c join teacher s on c.teacher_id=s.id " +
//                    "where upper(c.description) like %:str% " +
//                    "or upper(c.name) like %:str% " +
//                    "or upper(c.type) like %:str% " +
//                    "or upper(c.description) like %:str% " +
//                    "or upper(s.teacher_full_name) like %:str% ",
//        nativeQuery = true)
//    List<Course> searchAll(@Param("str") String s);


    @Query("select c from Course c where upper(c.teacher.teacherFullName.name) like %?1% " +
            "or upper(c.teacher.teacherFullName.surname) like %?1% " +
            "or upper(c.name) like %?1% " +
            "or upper(c.description) like %?1% ")
    List<Course> query(String query);



    void deleteByName(String name);

}
