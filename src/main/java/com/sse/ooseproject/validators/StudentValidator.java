package com.sse.ooseproject.validators;

import com.sse.ooseproject.InstituteRepository;
import com.sse.ooseproject.StudentRepository;
import com.sse.ooseproject.exceptions.StudentValidationException;
import com.sse.ooseproject.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class StudentValidator {

    private final InstituteRepository instituteRepository;
    private final StudentRepository studentRepository;


    @Autowired
    public StudentValidator(InstituteRepository pInstituteRepository, StudentRepository pStudentRepository){
        this.instituteRepository = pInstituteRepository;
        this.studentRepository = pStudentRepository;
    }

    public boolean validateStudent(Student pStudent) throws StudentValidationException {

        if(!checkFieldsFilled(pStudent)){
            throw new StudentValidationException("Ein oder mehrere Felder sind leer.");
        }

        if(!checkEmail(pStudent)){
            throw new StudentValidationException("Die Email-Adresse ist ungültig.");
        }

        if(!checkMatNrNotZero(pStudent)){
            throw new StudentValidationException("Die Matrikelnummer darf nicht leer sein.");
        }

        if(!checkNameAlpha(pStudent)){
            throw new StudentValidationException("Der Vor-und/oder Nachname enthält nicht-alpha Zeichen.");
        }

        if(!checkStudySubjectValid(pStudent)){
            throw new StudentValidationException("Das Fach existiert nicht.");
        }

        if(!checkMatNrNotOccupied(pStudent)){
            throw new StudentValidationException("Die Matrikelnummer ist bereits belegt.");
        }


        return true;
    }

    private boolean checkFieldsFilled(Student pStudent){
        return (pStudent.getFirstName() != null && pStudent.getLastName() != null && pStudent.getEmail() != null && pStudent.getStudySubject() != null) && (!pStudent.getFirstName().isEmpty() &&
                !pStudent.getLastName().isEmpty() &&
                !pStudent.getEmail().isEmpty());
    }

    private boolean checkEmail(Student pStudent){
        String regexPattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        return Pattern.compile(regexPattern)
                .matcher(pStudent.getEmail())
                .matches();
    }

    private boolean checkMatNrNotZero(Student pStudent){
        return pStudent.getMatNr() != 0;
    }

    private boolean checkNameAlpha(Student pStudent){
        String regexPattern = "^[a-zA-Z0-9_]*$";
        return Pattern.compile(regexPattern)
                .matcher(pStudent.getFirstName())
                .matches() &&
                Pattern.compile(regexPattern)
                        .matcher(pStudent.getLastName())
                        .matches();
    }

    private boolean checkStudySubjectValid(Student pStudent){
        List<String> studySubjects = instituteRepository.listStudySubjects();
        return studySubjects.contains(pStudent.getStudySubject());
    }

    private boolean checkMatNrNotOccupied(Student student){
        List<Student> studentList = studentRepository.findStudentByMatNr(student.getMatNr());
        return studentList.isEmpty();
    }
}
