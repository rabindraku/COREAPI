package com.example.test.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.test.model.EmployeeDetail;

@RestController
public class EmployeeController {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/getEmpolyeeDetails")
	public ResponseEntity<EmployeeDetail> getEmpolyeeDetails(@RequestHeader(value="Autentication-Key") String authenticationKey) {
		//do validate 
		EmployeeDetail employeeDetail = new EmployeeDetail();
		List<EmployeeDetail> list = new ArrayList<EmployeeDetail>();
		employeeDetail.setId(123L);
		employeeDetail.setFirstName("ravi");
		employeeDetail.setLastName("kumar");
		list.add(employeeDetail);
		return new ResponseEntity(list, new HttpHeaders(), HttpStatus.OK);
	}
}
