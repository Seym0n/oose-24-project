package com.sse.ooseproject.validators;

import com.sse.ooseproject.InstituteRepository;
import com.sse.ooseproject.StudentRepository;
import com.sse.ooseproject.exceptions.StudentValidationException;
import com.sse.ooseproject.models.Student;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentValidatorTest {

    Student student;

    @Autowired
    StudentValidator validator;

    @MockBean
    InstituteRepository instituteRepository;

    @MockBean
    StudentRepository studentRepository;


    @BeforeEach
    void generalSetupEach(){
        student = new Student();
    }

    @AfterEach
    void cleanAfterTest(){
        student = null;
    }

    /**
     * Test Case: All fields are empty
     */

    @Test
    void validateStudent1() {
        assertThrows(StudentValidationException.class, () -> {
            boolean isValid = validator.validateStudent(student);
        });
    }

    /**
     * Test Case: Email Address is not correct
     */

    @Test
    void validateStudent2(){
        assertThrows(StudentValidationException.class, () -> {
            long matNr = 156151;

            student.setFirstName("Erika");
            student.setLastName("Musterfrau");
            student.setEmail("@notrealmaildddsd.de");
            student.setMatNr(matNr);
            student.setStudySubject("Law");

            List<String> studySubjects = new ArrayList<String>();
            studySubjects.add("Law");

            Mockito.when(instituteRepository.listStudySubjects()).thenReturn(studySubjects);

            List<Student> studentList = new ArrayList<Student>();

            Mockito.when(studentRepository.findStudentByMatNr(matNr)).thenReturn(studentList);

            boolean isValid = validator.validateStudent(student);
        });
    }

    /**
     * Test Case: Study Subject does not exist
     */

    @Test
    void validateStudent3(){
        assertThrows(StudentValidationException.class, () -> {
            long matNr = 156151;

            student.setFirstName("Erika");
            student.setLastName("Musterfrau");
            student.setEmail("example@mail.com");
            student.setMatNr(matNr);
            student.setStudySubject("Greek Mythology");

            List<String> studySubjects = new ArrayList<String>();
            studySubjects.add("Law");

            Mockito.when(instituteRepository.listStudySubjects()).thenReturn(studySubjects);

            List<Student> studentList = new ArrayList<Student>();

            Mockito.when(studentRepository.findStudentByMatNr(matNr)).thenReturn(studentList);

            boolean isValid = validator.validateStudent(student);
        });
    }


    /**
     * Test Case: Two students cannot have the same matriculation number
     */

    @Test
    void validateStudent4(){
        assertThrows(StudentValidationException.class, () -> {
            long matNr = 156151;

            student.setFirstName("Erika");
            student.setLastName("Musterfrau");
            student.setEmail("example@mail.com");
            student.setMatNr(matNr);
            student.setStudySubject("Law");

            List<String> studySubjects = new ArrayList<String>();
            studySubjects.add("Law");

            Mockito.when(instituteRepository.listStudySubjects()).thenReturn(studySubjects);

            List<Student> studentList = new ArrayList<Student>();
            Student existingStudent = new Student();
            existingStudent.setMatNr(matNr);
            studentList.add(existingStudent);

            Mockito.when(studentRepository.findStudentByMatNr(matNr)).thenReturn(studentList);

            boolean isValid = validator.validateStudent(student);
        });
    }

    /**
     * Test Case: Empty first name
     */

    @Test
    void validateStudent5(){
        assertThrows(StudentValidationException.class, () -> {
            long matNr = 156151;

            student.setFirstName("");
            student.setLastName("Musterfrau");
            student.setEmail("example@mail.com");
            student.setMatNr(matNr);
            student.setStudySubject("Law");

            List<String> studySubjects = new ArrayList<String>();
            studySubjects.add("Law");

            Mockito.when(instituteRepository.listStudySubjects()).thenReturn(studySubjects);

            List<Student> studentList = new ArrayList<Student>();

            Mockito.when(studentRepository.findStudentByMatNr(matNr)).thenReturn(studentList);

            boolean isValid = validator.validateStudent(student);
        });
    }

    /**
     * Test Case: Empty last name
     */

    @Test
    void validateStudent6(){
        assertThrows(StudentValidationException.class, () -> {
            long matNr = 156151;

            student.setFirstName("Erika");
            student.setLastName("");
            student.setEmail("example@mail.com");
            student.setMatNr(matNr);
            student.setStudySubject("Law");

            List<String> studySubjects = new ArrayList<String>();
            studySubjects.add("Law");

            Mockito.when(instituteRepository.listStudySubjects()).thenReturn(studySubjects);

            List<Student> studentList = new ArrayList<Student>();

            Mockito.when(studentRepository.findStudentByMatNr(matNr)).thenReturn(studentList);

            boolean isValid = validator.validateStudent(student);
        });
    }

    /**
     * Test Case: Last name is null
     */

    @Test
    void validateStudent7(){
        assertThrows(StudentValidationException.class, () -> {
            long matNr = 156151;

            student.setFirstName("Erika");
            student.setLastName(null);
            student.setEmail("example@mail.com");
            student.setMatNr(matNr);
            student.setStudySubject("Law");

            List<String> studySubjects = new ArrayList<String>();
            studySubjects.add("Law");

            Mockito.when(instituteRepository.listStudySubjects()).thenReturn(studySubjects);

            List<Student> studentList = new ArrayList<Student>();

            Mockito.when(studentRepository.findStudentByMatNr(matNr)).thenReturn(studentList);

            boolean isValid = validator.validateStudent(student);
        });
    }

    /**
     * Test Case: First name contains non-alpha characters
     */

    @Test
    void validateStudent8(){
        assertThrows(StudentValidationException.class, () -> {
            long matNr = 156151;

            student.setFirstName("Erika$");
            student.setLastName("Musterfrau");
            student.setEmail("example@mail.com");
            student.setMatNr(matNr);
            student.setStudySubject("Law");

            List<String> studySubjects = new ArrayList<String>();
            studySubjects.add("Law");

            Mockito.when(instituteRepository.listStudySubjects()).thenReturn(studySubjects);

            List<Student> studentList = new ArrayList<Student>();

            Mockito.when(studentRepository.findStudentByMatNr(matNr)).thenReturn(studentList);

            boolean isValid = validator.validateStudent(student);
        });
    }

    /**
     * Test Case: Matriculation number cannot be zero
     */

    @Test
    void validateStudent9(){
        assertThrows(StudentValidationException.class, () -> {
            long matNr = 156151;

            student.setFirstName("Erika");
            student.setLastName("Musterfrau");
            student.setEmail("example@mail.com");
            student.setMatNr(0);
            student.setStudySubject("Law");

            List<String> studySubjects = new ArrayList<String>();
            studySubjects.add("Law");

            Mockito.when(instituteRepository.listStudySubjects()).thenReturn(studySubjects);

            List<Student> studentList = new ArrayList<Student>();

            Mockito.when(studentRepository.findStudentByMatNr(matNr)).thenReturn(studentList);

            boolean isValid = validator.validateStudent(student);
        });
    }

    /**
     * Test Case: All data are valid, thus non-failing
     * @throws StudentValidationException
     */

    @Test
    void validateStudent10() throws StudentValidationException {
        long matNr = 156151;

        student.setFirstName("Erika");
        student.setLastName("Musterfrau");
        student.setEmail("example@mail.com");
        student.setMatNr(matNr);
        student.setStudySubject("Law");

        List<String> studySubjects = new ArrayList<String>();
        studySubjects.add("Law");

        Mockito.when(instituteRepository.listStudySubjects()).thenReturn(studySubjects);

        List<Student> studentList = new ArrayList<Student>();

        Mockito.when(studentRepository.findStudentByMatNr(matNr)).thenReturn(studentList);

        boolean isValid = validator.validateStudent(student);
        assertTrue(isValid);
    }
}