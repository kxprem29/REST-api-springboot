package com.kxprem.RESTAPI.service;

import com.kxprem.RESTAPI.dto.AddStudentRequestDTO;
import com.kxprem.RESTAPI.dto.StudentDTO;

import java.net.URI;
import java.util.List;
import java.util.Map;

public interface StudentService {

    List<StudentDTO> getAllStudents();

    StudentDTO getStudentByID(Long id);

    StudentDTO createNewStudent(AddStudentRequestDTO addStudentRequestDTO);

    void deleteStudentByID(Long id);

    StudentDTO updateStudent(Long id, AddStudentRequestDTO addStudentRequestDTO);

    StudentDTO updatePartialStudent(Long id, Map<String, Object> updates);
}
