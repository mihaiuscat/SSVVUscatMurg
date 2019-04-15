import domain.Student;
import org.junit.Test;
import org.junit.Before;
import repository.StudentRepository;
import validation.StudentValidator;

import java.util.Iterator;



public class TestLab2Assignment {
    StudentRepository studentRepository;

    @Before
    public void setup() {
        studentRepository = new StudentRepository(new StudentValidator()); }

    @Test
    public void addValidStudent() {
        Student student = new Student("10","Mihai", 935);
        studentRepository.save(student);
        assert studentRepository.findOne("10").equals(student);
    }

    @Test
    public void shouldNotAddStudentIfAlreadyPresent() {
        Student student1 = new Student("10","Mihai", 935);
        studentRepository.save(student1);
        Student student2 = new Student("10","Murg", 935);
        studentRepository.save(student2);

        Iterable values = studentRepository.findAll();
        Iterator it = values.iterator();

        int count = 0;
        while (it.hasNext()) {
            it.next();
            count++;
        }
        assert count == 1;
    }

    @Test
    public void shouldAddStudentWithValidGroupNr(){
        Student studentWithValidGroup = new Student("11", "Mihai", 935);
        studentRepository.save(studentWithValidGroup);

        assert studentRepository.findOne("11").equals(studentWithValidGroup);
    }

    @Test
    public void shouldNotAddStudentWithInvalidGroupNr() {
        Student invalidStudent = new Student("11", "Mihai", 5);
        Student st1 = studentRepository.save(invalidStudent);
        Student invalidStudent2 = new Student("12", "Murg", 2000);
        Student st2 = studentRepository.save(invalidStudent2);

        assert st1 == null;
        assert st2 == null;
    }

    @Test
    public void shouldNotAddStudentWithNullID() {
        Student studentWithNullID = new Student(null, "Mihai", 935);
        Student st1 = studentRepository.save(studentWithNullID);

        assert st1 == null;
    }

    @Test
    public void shouldNotAddStudentWithEmptyStringID() {
        Student studentWithEmptyID = new Student("", "Mihai", 935);
        Student st1 = studentRepository.save(studentWithEmptyID);

        assert st1 == null;
    }

    @Test
    public void shouldNotAddStudentWithNullName() {
        Student studentWithNullName = new Student("15", null, 935);
        Student st1 = studentRepository.save(studentWithNullName);

        assert st1 == null;
    }

    @Test
    public void shouldNotAddStudentWithEmptyStringName() {
        Student studentWithEmptyName = new Student("15", "", 935);
        Student st1 = studentRepository.save(studentWithEmptyName);

        assert st1 == null;
    }

    @Test
    public void shouldAddStudentWithValidName() {
        Student studentWithValidName = new Student("15", "MihaiSabou", 935);
        studentRepository.save(studentWithValidName);

        assert studentRepository.findOne("15").equals(studentWithValidName);
    }
}

