package guru.springfamework.bootstrap;

import guru.springfamework.domain.Category;
import guru.springfamework.domain.Customer;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import guru.springfamework.repositories.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 9/24/17.
 */
@Component
public class Bootstrap implements CommandLineRunner{

    private CategoryRepository categoryRespository;
    private CustomerRepository customerRepository;
    private VendorRepository vendorRepository;

    @Autowired
    public Bootstrap(CategoryRepository categoryRespository, CustomerRepository customerRepository, VendorRepository vendorRepository) {
        this.categoryRespository = categoryRespository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        bootStrapCategories();
        bootStrapCustomers();
        bootStrapVendors();
    }
    private void bootStrapCustomers(){
        Customer matt = new Customer();
        matt.setFirstName("Matt");
        matt.setLastName("Nee");

        customerRepository.save(matt);
    }
    private void bootStrapCategories() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRespository.save(fruits);
        categoryRespository.save(dried);
        categoryRespository.save(fresh);
        categoryRespository.save(exotic);
        categoryRespository.save(nuts);


        System.out.println("Data Loaded = " + categoryRespository.count() );
    }
    private void bootStrapVendors(){
        Vendor acme = new Vendor();
        acme.setName("ACME");

        Vendor giant = new Vendor();
        giant.setName("Giant");

        Vendor traderJoes = new Vendor();
        traderJoes.setName("Trader Joes");

        vendorRepository.save(acme);
        vendorRepository.save(giant);
        vendorRepository.save(traderJoes);
    }
}
