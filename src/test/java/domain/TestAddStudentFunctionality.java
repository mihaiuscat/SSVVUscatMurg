package domain;

import domain.Student;
import org.junit.Test;
import org.junit.Before;
import repository.StudentRepository;
import validation.StudentValidator;

import java.util.Iterator;



public class TestAddStudentFunctionality {
    StudentRepository studentRepository;

    @Before
    public void setup() {
        studentRepository = new StudentRepository(new StudentValidator()); }

    @Test
    public void shouldAddValidStudent() {
        Student student = new Student("10","Mihai", 935);
        studentRepository.save(student);
        assert studentRepository.findOne("10").equals(student);
    }

    @Test
    public void shouldNotSaveDuplicatedData() {
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
    public void shouldSaveValidGroup(){
        Student studentWithValidGroup = new Student("11", "Mihai", 935);
        studentRepository.save(studentWithValidGroup);

        assert studentRepository.findOne("11").equals(studentWithValidGroup);
    }

    @Test
    public void shouldNotSaveInvalidGroup() {
        Student invalidStudent = new Student("11", "Mihai", 5);
        Student student1 = studentRepository.save(invalidStudent);
        Student invalidStudent2 = new Student("12", "Murg", 2000);
        Student student2 = studentRepository.save(invalidStudent2);

        assert student1 == null;
        assert student2 == null;
    }

    @Test
    public void shouldNotAddStudentWithoutID() {
        Student studentWithNullID = new Student(null, "Mihai", 935);
        Student student = studentRepository.save(studentWithNullID);

        assert student == null;
    }


    @Test
    public void shouldNotAddStudentWithoutName() {
        Student studentWithNullName = new Student("15", null, 935);
        Student student = studentRepository.save(studentWithNullName);

        assert student == null;
    }

}

