package ma.adnane.customerservice.web;

import lombok.AllArgsConstructor;
import ma.adnane.customerservice.entities.Customer;
import ma.adnane.customerservice.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customers")
@AllArgsConstructor
public class CustomerRestController {
    private CustomerRepository customerRepository;
    @GetMapping
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }
    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id){
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
    }
    @PostMapping
    public Customer saveCustomer(@RequestBody Customer customer){
        return customerRepository.save(customer);
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String,String> exceptionHandler(Exception exception){
        return Map.of("errorMessage",exception.getMessage());
    }
}
