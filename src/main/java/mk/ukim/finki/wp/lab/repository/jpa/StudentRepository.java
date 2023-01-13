package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    public List<Student> findAllByNameContainsOrSurnameContains(String name, String surname);
    public List<Student> findAllByNameIgnoreCaseContainsOrSurnameIgnoreCaseContainsOrUsernameIgnoreCaseContains(String name, String surname, String username);
    public Optional<Student> findStudentByUsername(String username);
}
