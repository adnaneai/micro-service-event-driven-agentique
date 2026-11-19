package ma.adnane.customerservice.mcp;

import lombok.AllArgsConstructor;
import ma.adnane.customerservice.entities.Customer;
import ma.adnane.customerservice.repository.CustomerRepository;
import org.springframework.ai.mcp.annotation.McpArg;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CustomerMCPController {
    private CustomerRepository customerRepository;
    @McpTool(description = "Get all customers")
    public List<Customer> findAll(){
        return customerRepository.findAll();
    }
    @McpTool(description = "Get customer by id")
    public Customer getCustomerById(@McpArg(description = "The customer id") Long id){
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
    }
    @McpTool(description = "Save new customer")
    public Customer saveCustomer(@McpArg(description = "Save new customer (name, email)") Customer customer){
        return customerRepository.save(customer);
    }
}
