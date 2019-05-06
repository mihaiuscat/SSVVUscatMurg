package domain;

import org.junit.Before;
import org.junit.Test;
import repository.NotaRepository;
import repository.StudentRepository;
import repository.TemaRepository;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;

public class Lab4TakeHome {
    Student sampleStudent;
    StudentRepository studentRepository;

    Tema sampleAssignment;
    TemaRepository assignmentRepository;

    Nota sampleGrade;
    NotaRepository gradeRepository;

    @Before
    public void setup() {
        sampleStudent = new Student("10", "Mihai", 935);
        studentRepository = new StudentRepository(new StudentValidator());

        sampleAssignment = new Tema("10", "Description", 12, 2);
        assignmentRepository = new TemaRepository(new TemaValidator());

        sampleGrade = new Nota(new Pair<>("10", "10"), 9, 10, "Very good");
        gradeRepository = new NotaRepository(new NotaValidator());
    }

    private void addValidStudent() {
        studentRepository.save(sampleStudent);
    }

    private void addValidAssignment() {
        assignmentRepository.save(sampleAssignment);
    }

    private void addValidGrade(){
        gradeRepository.save(sampleGrade);
    }

    @Test
    public void testIncremental() {
        System.out.println("Running tests");
        this.addValidStudent();
        assert studentRepository.findOne("10").equals(sampleStudent);

        this.addValidAssignment();
        assert assignmentRepository.findOne("10").equals(sampleAssignment);

        this.addValidGrade();
        assert gradeRepository.findOne(new Pair<>("10", "10")).equals(sampleGrade);
    }

}