package ma.adnane.customerservice.graphql;

import graphql.GraphQLError;
import lombok.AllArgsConstructor;
import ma.adnane.customerservice.entities.Customer;
import ma.adnane.customerservice.projection.CustomerDTO;
import ma.adnane.customerservice.repository.CustomerRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class CustomerGraphQLController {
    private CustomerRepository customerRepository;
    @QueryMapping
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }
    @QueryMapping
    public Customer getCustomerById(@Argument Long id){
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
    }
    @MutationMapping
    public Customer saveCustomer(@Argument CustomerDTO customerDTO ){
        Customer customerToSave = Customer.builder()
                .name(customerDTO.name())
                .email(customerDTO.email()).build();
        return customerRepository.save(customerToSave);
    }
    @GraphQlExceptionHandler
    public GraphQLError exceptionHandler(Exception exception){
        return GraphQLError.newError()
                .message(exception.getMessage()).build();
    }
}
