package domain;

import org.junit.Before;
import org.junit.Test;
import repository.NotaRepository;
import repository.StudentRepository;
import repository.TemaRepository;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;

public class Lab4InClass {
    StudentRepository studentRepository;
    TemaRepository assignmentRepository;
    NotaRepository gradeRepository;

    @Before
    public void setup() {
        studentRepository = new StudentRepository(new StudentValidator());
        assignmentRepository = new TemaRepository(new TemaValidator());
        gradeRepository = new NotaRepository(new NotaValidator());
    }

    private void addValidStudent() {
        Student student = new Student("10", "Mihai", 935);
        studentRepository.save(student);
    }

    private void addValidAssignment() {
        Tema assignment = new Tema("10", "Description", 12, 2);
        assignmentRepository.save(assignment);
    }

    private void addValidGrade(){
        Nota grade = new Nota(new Pair<>("10", "10"), 9, 10, "Very good");
        gradeRepository.save(grade);
    }

    @Test
    public void testBigBang() {
        this.addValidStudent();
        this.addValidAssignment();
        this.addValidGrade();

        assert gradeRepository.findOne(new Pair<>("10", "10")).equals(new Nota(new Pair<>("10", "10"), 9, 10, "Very good"));
    }

}
