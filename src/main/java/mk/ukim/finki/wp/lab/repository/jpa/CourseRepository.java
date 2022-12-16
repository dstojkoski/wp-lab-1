package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.Course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    // @Query(value = "") ako ni treba query
    List<Course> findAllByName(String name);
    List<Course> findAllByNameLike(String name);
    List<Course> findAllByOrderByNameDesc();
    List<Course> findAllByOrderByCourseId();
    void deleteByName(String name);

}
