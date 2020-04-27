package com.example.demo.controller;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entity.EmployeeDetail;
import com.example.demo.entity.EmployeeEntity;
import com.example.demo.jpa.EmployeeRepository;
import com.example.demo.service.UserService;

//@Controller
//@RequestMapping("/users")
@RestController
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private EmployeeRepository repository;

	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/userDetailslist", method = RequestMethod.GET)
	public ResponseEntity userDetails() {
		log.info("inside method userDetails.");
		List userDetails = userService.getUserDetails();
		return new ResponseEntity(userDetails, HttpStatus.OK);
	}

	@GetMapping("userDetailslistFromJpa")
	public ResponseEntity<List<EmployeeEntity>> getAllEmployees() {
		List<EmployeeEntity> list = repository.findAll();

		return new ResponseEntity<List<EmployeeEntity>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("getEmployee/{id}")
	public ResponseEntity<EmployeeEntity> getEmployeeById(@PathVariable("id") Long id) {
		Optional<EmployeeEntity> entity = repository.findById(id);

		return new ResponseEntity(entity, new HttpHeaders(), HttpStatus.OK);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/name")
	public ResponseEntity<EmployeeEntity> getEmployeeByName(@RequestParam("firstName") String name) {
		Optional<EmployeeEntity> entity = repository.findByFirstName(name);

		return new ResponseEntity(entity, new HttpHeaders(), HttpStatus.OK);
	}
	
	@RequestMapping(value= "/welcome", method = RequestMethod.GET)
	@ResponseBody
	public String welcomeMessage(@RequestParam("name") String name) {
		return "welcome to our side : "+name;
	}
	
	@ResponseStatus(value = HttpStatus.OK)
    @PostMapping(value="/user", consumes = MediaType.APPLICATION_JSON_VALUE) // consumes is optional if you want you can omit
    public void process2(@Valid @RequestBody EmployeeEntity user) {
		System.out.println("data is " + user.toString());
        log.info("User: {}", user);
    }
	
	@GetMapping("/next")
    public String nextData(Map<String, Object> model){
        model.put("message", "You are in new page !!");
        System.out.println("inside the method nextData");
        return "testing";
       
        
    }
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/getResultFromAnotherApplication")
	public ResponseEntity<EmployeeDetail> getResultFromAnotherApplication(){
		HttpHeaders headers = new HttpHeaders();
		headers.add("Autentication-Key", "Basic mhdghvdghvgh");
		
		HttpEntity<String> request = new HttpEntity<String>(headers);
		
		ResponseEntity<Collection> response = new RestTemplate()
				.exchange("http://localhost:8082/getEmpolyeeDetails", HttpMethod.GET, request, Collection.class);
		
		
		//Collection<EmployeeEntity> entity = new RestTemplate().getForObject("http://localhost:8082/getEmpolyeeDetails", Collection.class);
		//return entity;
		return new ResponseEntity(response.getBody(), new HttpHeaders(), HttpStatus.OK);
	}
}
