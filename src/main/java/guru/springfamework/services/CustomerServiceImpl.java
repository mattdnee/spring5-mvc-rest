package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import guru.springfamework.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository
                .findAll()
                .stream()
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setCustomerUrl("/api/v1/customers/"+customer.getId());
                    return customerDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO findCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(customerMapper::customerToCustomerDTO)
                .map(customerDTO -> {
                    //set API URL
                    customerDTO.setCustomerUrl("api/v1/customers/"+id);
                    return customerDTO;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO){
        return saveCustomer(customerMapper.customerDTOToCustomer(customerDTO));
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO){
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        customer.setId(id);
        return saveCustomer(customerMapper.customerDTOToCustomer(customerDTO));
    }

    private CustomerDTO saveCustomer(Customer customer){
        Customer savedCustomer = customerRepository.save(customer);
        CustomerDTO returnDTO = customerMapper.customerToCustomerDTO(savedCustomer);
        returnDTO.setCustomerUrl("/api/v1/customers/"+savedCustomer.getId());
        return returnDTO;
    }

    @Override
    public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {
        return customerRepository.findById(id).map(customer -> {
            if(customerDTO.getFirstName() != null){
                customer.setFirstName(customerDTO.getFirstName());
            }
            if(customerDTO.getLastName() != null){
                customer.setLastName(customerDTO.getLastName());
            }
            CustomerDTO returnDto = customerMapper.customerToCustomerDTO(customerRepository.save(customer));
            returnDto.setCustomerUrl("/api/v1/customer/" + id);
            return returnDto;
        }).orElseThrow(ResourceNotFoundException::new); //todo implement better exception handling;
    }

    @Override
    public void deleteById(Long id){
        customerRepository.deleteById(id);
    }
}
