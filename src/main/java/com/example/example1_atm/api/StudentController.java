package com.example.example1_atm.api;

import com.example.example1_atm.request.StudentRequest;
import com.example.example1_atm.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/student")
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/filter")
    public ResponseEntity<?>filterStudentByNameAndAgeAndGender(@RequestBody StudentRequest studentRequest
                                                                ,@RequestParam("pageNumber") int pageNumber
                                                                ,@RequestParam("limit") int limit){
        Pageable pageable= PageRequest.of(pageNumber,limit);
        return ResponseEntity.ok(studentService.filterByNameOrAgeOrGender(studentRequest.getName(),studentRequest.getAge()
                                                                        ,studentRequest.getGender(),
                                                                        pageable));
    }

}
