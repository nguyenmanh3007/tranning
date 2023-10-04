package com.example.example1_atm.service.impl;

import com.example.example1_atm.entity.Classes;
import com.example.example1_atm.entity.Student;
import com.example.example1_atm.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final JdbcTemplate jdbcTemplate;
    private final EntityManager entityManager;
    @Override
    public List<Student> filterByNameOrAgeOrGender(String name, int age, String gender, int pageNumber, int limit) {
        String paramName="%"+name+"%";
        int offset=(pageNumber-1)*limit;
        return jdbcTemplate.query("SELECT * FROM student st JOIN classes cl ON st.class_id = cl.id" +
                        " WHERE st.name LIKE ?1 OR st.age = ?2 OR st.gender =?3" +
                        " LIMIT ?4  OFFSET ?5",
                this::mapToStudent,paramName,age,gender,limit, offset);
//        return entityManager.createNativeQuery("SELECT st.id,st.address,st.age,st.gender,st.name,st.class_id FROM student st JOIN classes as cl ON st.class_id = cl.id" +
//                " WHERE st.name LIKE ? OR st.age = ? OR st.gender =?" +
//                " LIMIT ?  OFFSET ?")
//                .setParameter(1,paramName)
//                .setParameter(2,age)
//                .setParameter(3,gender)
//                .setParameter(4,limit)
//                .setParameter(5,offset)
//                .getResultList();
    }

    @Override
    public List<Student> findAll(int pageNumber, int limit) {
        Query query=entityManager.createQuery("SELECT st FROM Student st");
        return query.getResultList();
    }

    @Override
    public List<Student> filterByNameOrAgeOrGender(String name, int age, String gender, Pageable pageable) {
                String paramName="%"+name+"%";
                return entityManager.createQuery("SELECT st FROM Student st JOIN Classes as cl ON st.classes.id = cl.id" +
                " WHERE st.name LIKE ?1 OR st.age = ?2 OR st.gender =?3")
                .setParameter(1,paramName)
                .setParameter(2,age)
                .setParameter(3,gender)
                        .setFirstResult((int) pageable.getOffset())
                        .setMaxResults(pageable.getPageSize())
                .getResultList();
    }

    private Student mapToStudent(ResultSet rs, int row) throws SQLException {
        Long classId=rs.getLong("class_id");
        String department= rs.getString("department");
        String nameClass= rs.getString("name_class");
        Classes classes= Classes.builder()
                .id(classId)
                .nameClass(nameClass)
                .department(department)
                .build();
        return Student.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .age(rs.getInt("age"))
                .gender(rs.getString("gender"))
                .address(rs.getString("address"))
                .classes(classes)
                .build();
    }
}
