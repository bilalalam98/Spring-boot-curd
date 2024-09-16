package com.trmab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

//so now its getting error for missing annotation for spring configuration
//@ComponentScan(basePackages = "com.trmab") //to tell this is the package to instanciated from
//@EnableAutoConfiguration // tomcat is install to for configuration we apply this
//@Configuration // if we have any bean inside that we want to instanciated
@SpringBootApplication //now instead of the all above annotations i can use SpringBootApplication only which contains all these
@RestController // this means that any mehtod on this class that has get or post otc mapping they will expose to httop endpoint
//that client can acess
@RequestMapping("api/v1/customers")
public class Main {

    private final CustomerRepository customerRepository;

    public Main(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
//        tomcat is a implemntation of Jakarta Sarvlet Java Expression Language and websocket
//         technologies and provides pure http web server envoirnment in which java code can also run
//        we can also go for jetty and its on eclipse foundation
//        to change port you can do it from application.yml
    }

    @GetMapping
    public List<Customer> getCustomers(){
        System.out.println(customerRepository.findAll());
        return customerRepository.findAll();
    }


    //to add the customer

    record NewCustomerRequest(String name, String email, Integer age) {

    }

    @PostMapping
    public void addCustomer(@RequestBody NewCustomerRequest req) {
        Customer customer = new Customer();
        customer.setName(req.name());
        customer.setEmail(req.email());
        customer.setAge(req.age());
        customerRepository.save(customer);
    }

    @DeleteMapping("{customerId}")
    public void deleteCustomer(@PathVariable Integer id) {
        customerRepository.deleteById(id.longValue());
    }

//    @GetMapping("/greet")
//    public String greeting(){
//        return "Hello World!";
//    }

    //as we marked this class As rest Controller we are able to have methods in it that we can expose as rest enpoints to create apis
    //if i have class in here and in side i have response in it
    //so heere jason library helping us to convert java object to json object
//    @GetMapping("/greet")
//    public GreetResponse greet(){
//        GreetResponse response =  new GreetResponse("Hello World!",
//                List.of("java","golang","javascript"),
//                new Person("Asif", 23, 36000));
//        return response;
//    }
//
//
//    record Person(String name, int age, double savings){}
//    record GreetResponse(String greet,List<String> favProgrammingLanguages,Person person){}

    //before above we started from below
//    @GetMapping("/greet")
//    public GreetResponse greet(){
//        return new GreetResponse("Hello World!");
//    }
    //This is a Java record, which is a special kind of class introduced in Java 14.
    // It is used to model immutable data. The GreetResponse record has a single
    // field greet of type String and its because of jackson library so now i will get the json greeting now
//    record GreetResponse(String greet){}

    //or you can write below code then record
//   class GreetResponse {
//        private final String greet;
//        public GreetResponse(String greet) {
//            this.greet = greet;
//        }

    //.   i get response because of this getter
//        public String getGreet() {
//            return greet;
//        }
//          public String toString(){
//             return "GreetResponse{" +
//                     "greet='" + greet + '\'' +
//                     '}';
//          }
//
//       @Override
//       public boolean equals(Object o) {
//           if (this == o) return true;
//           if (o == null || getClass() != o.getClass()) return false;
//           GreetResponse that = (GreetResponse) o;
//           return Objects.equals(greet, that.greet);
//       }
//
//       @Override
//       public int hashCode() {
//           return Objects.hashCode(greet);
//       }
//   }

//
}
