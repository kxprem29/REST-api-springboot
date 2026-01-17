package com.kxprem.RESTAPI.service.impl;

import com.kxprem.RESTAPI.dto.AddStudentRequestDTO;
import com.kxprem.RESTAPI.dto.StudentDTO;
import com.kxprem.RESTAPI.entity.Student;
import com.kxprem.RESTAPI.repository.StudentRepository;
import com.kxprem.RESTAPI.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<StudentDTO> getAllStudents() {
        List<Student> students=studentRepository.findAll();
        List<StudentDTO> studentDTOList =students
                .stream()
                .map(student -> new StudentDTO(student.getId(), student.getName(), student.getEmail()))
                .toList();
        return studentDTOList;
    }

    @Override
    public StudentDTO getStudentByID(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Student not found with ID: "+id));
        StudentDTO studentDTO= modelMapper.map(student, StudentDTO.class);
        return studentDTO;
    }

    @Override
    public StudentDTO createNewStudent(AddStudentRequestDTO addStudentRequestDTO) {
        Student newStudent= modelMapper.map(addStudentRequestDTO, Student.class);
        Student student =studentRepository.save(newStudent);
        return modelMapper.map(student, StudentDTO.class);
    }

    @Override
    public void deleteStudentByID(Long id) {
        if(!studentRepository.existsById(id)){
            throw new IllegalArgumentException("Student does not exist with ID: "+id);
        }
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDTO updateStudent(Long id, AddStudentRequestDTO addStudentRequestDTO) {
        Student student = studentRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Student not found with ID: "+id));
        modelMapper.map(addStudentRequestDTO,student);

        student = studentRepository.save(student);
        return modelMapper.map(student, StudentDTO.class);
    }

    @Override
    public StudentDTO updatePartialStudent(Long id, Map<String, Object> updates) {
        Student student = studentRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Student not found with ID: "+id));

        updates.forEach((field,value)->{
            switch (field){
                case "name":
                    student.setName((String) value);
                    break;
                case "email":
                    student.setEmail((String) value);
                    break;

                default:
                    throw new IllegalArgumentException("Field not supported!");

            }
        });

        Student saveStudent = studentRepository.save(student);
        return modelMapper.map(saveStudent, StudentDTO.class);
    }
}
