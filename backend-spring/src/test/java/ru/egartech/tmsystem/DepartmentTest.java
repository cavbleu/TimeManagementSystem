package ru.egartech.tmsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.egartech.tmsystem.model.repository.DepartmentRepository;
import ru.egartech.tmsystem.service.DepartmentService;

@SpringBootTest
class DepartmentTest implements CrudTest{

    @MockBean
    private DepartmentRepository departmentRepository;

    @Autowired
    private DepartmentService departmentService;

    @Override
    public void findAllTest() {

    }

    @Override
    public void findByIdTest() {

    }

    @Override
    public void updateTest() {

    }
}