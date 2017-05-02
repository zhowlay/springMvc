package com.titan.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.titan.model.Greeting;
import com.titan.model.Quote;
import com.titan.model.User;
import com.titan.model.Customer;
import com.titan.myinterface.CustomerRepository;

@ControllerAdvice
@RestController
@RequestMapping("/api")
public class GreetingController {

	private static Logger logger  = Logger.getLogger(GreetingController.class);  
	private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    @Autowired
    CustomerRepository repository;
   
//    @Autowired
//    JdbcTemplate jdbcTemplate;
//    @RequestMapping("/greeting")
//    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name,@RequestParam(value="name1", defaultValue="World2")String name1) {
//        return new Greeting(counter.incrementAndGet(),
//                            String.format(template, name1));
//    }
//    @RequestMapping("/person")
//    public String toPerson(@RequestParam(value="name", defaultValue="World") String name,@RequestParam(value="age", defaultValue="0.0") double age){
//        System.out.println(name+" "+age);
//        return "hello";
//    }
//    @RequestMapping(value="/user/{id}",method=RequestMethod.GET)
//    public String get(@PathVariable("id") Integer id){
//        System.out.println("get"+id);
//        return "/hello";
//    }
//     
//    @RequestMapping(value="/user/{id}",method=RequestMethod.POST)
//    public String post(@PathVariable("id") Integer id){
//        System.out.println("post"+id);
//        return "/hello";
//    }
//     
//    @RequestMapping(value="/user/{id}",method=RequestMethod.PUT)
//    public String put(@PathVariable("id") Integer id){
//        System.out.println("put"+id);
//        return "/hello";
//    }
//     
//    @RequestMapping(value="/user/{id}",method=RequestMethod.DELETE)
//    public String delete(@PathVariable("id") Integer id){
//        System.out.println("delete"+id);
//        return "/hello";
//    }
//    @ResponseBody
//    @RequestMapping("/user")
//    public  User get(){
//        User u = new User();
//        u.setId(1);
//        u.setName("jayjay");
//        u.setBirthday(new Date().getTime()/1000);
//        return u;
//    }
//    
//    @ExceptionHandler
//    @ResponseBody
//    public User exceptionHandler(Exception ex){
//    	User u = new User();
//        u.setId(1);
//        u.setName("数据格式有误");
//        u.setBirthday(new Date().getTime()/1000);
//        return u;
//    }
//    
//    @RequestMapping("/error")
//    public String error(){
//        int i = 5/0;
//        return "hello";
//    }
//    @RequestMapping("/person2")
//    public String toPerson2(@RequestParam(value="name", required=true) String name){
//        System.out.println(name+" ");
//        return "hello";
//    }
//    @RequestMapping("/getJsonString")
//    public String getJsonString(){
//    	 RestTemplate restTemplate = new RestTemplate();
//         Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
//         logger.info(quote.getValue().toString());
//		return "";
//    }
//    @RequestMapping("/jdbc")
//    public String jdbc(){
//    	jdbcTemplate.execute("DROP TABLE if EXISTS customers");
//        jdbcTemplate.execute("CREATE TABLE customers(" +
//                "id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");
//        // Split up the array of whole names into an array of first/last names
//        List<Object[]> splitUpNames = Arrays.asList("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long").stream()
//                .map(name -> name.split(" "))
//                .collect(Collectors.toList());
////
//        // Use a Java 8 stream to print out each tuple of the list
//        splitUpNames.forEach(name -> logger.info(String.format("Inserting customer record for %s %s", name[0], name[1])));
////
//        // Uses JdbcTemplate's batchUpdate operation to bulk load data
//        jdbcTemplate.batchUpdate("INSERT INTO customers(first_name, last_name) VALUES (?,?)", splitUpNames);
//
//        logger.info("Querying for customer records where first_name = 'Josh':");
//        jdbcTemplate.query(
//                "SELECT id, first_name, last_name FROM customers WHERE first_name = ?", new Object[] { "Josh" },
//                (rs, rowNum) ->new Customer (rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"))
//        ).forEach(customer -> logger.info(customer.toString()));
//		return "f";
//    	 
//    }
    @RequestMapping("/jpa")
    public String jpa(){
     // save a couple of customers
        repository.save(new Customer("Jack", "Bauer"));
		repository.save(new Customer("Chloe", "O'Brian"));
		repository.save(new Customer("Kim", "Bauer"));
		repository.save(new Customer("David", "Palmer"));
		repository.save(new Customer("Michelle", "Dessler"));
//		Page<Customer> page=repository.findAll(new PageRequest(1, 2));
		@SuppressWarnings("unused")
		List<Customer> page2=repository.findByLastName("Dessler",new Sort(Direction.DESC,"id"));
//		List<Customer> list= page.getContent();
//		for (Customer customer : list) {
//			System.out.println(customer.getFirstName());
//		}
		return "ok";
    	
    }
}
