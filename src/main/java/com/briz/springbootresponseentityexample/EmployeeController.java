package com.briz.springbootresponseentityexample;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController
{
@Autowired
EmployeeRepository erepo;
@RequestMapping("/save")
public ResponseEntity<Employee> save(@RequestBody Employee employee)
{	
	return new ResponseEntity<>(erepo.save(employee),HttpStatus.OK);
}
@RequestMapping("/all")
public ResponseEntity<List<Employee>> alldata()
{
	return new ResponseEntity<>(erepo.findAll(),HttpStatus.OK);
}
@RequestMapping("/{id}")
public ResponseEntity<Employee> byid(@PathVariable int id)
{
	Optional<Employee> employee=erepo.findById(id);
	return new ResponseEntity<>(employee.get(),HttpStatus.OK);
}
@RequestMapping("/update/{id}")
public ResponseEntity<Employee> updbyid(@RequestBody Employee employee,int id)
{
Employee e=erepo.findById(id).get();
e.setName(employee.getName());
e.setAge(employee.getAge());
e.setCity(employee.getCity());
return  new ResponseEntity<>(erepo.save(e),HttpStatus.OK);
}
@RequestMapping("/del/{id}")
public ResponseEntity<Employee> delbyid(@PathVariable int id)
{
	Employee employee=erepo.findById(id).get();
	erepo.delete(employee);
	return new ResponseEntity<>(employee,HttpStatus.OK);
}
}
