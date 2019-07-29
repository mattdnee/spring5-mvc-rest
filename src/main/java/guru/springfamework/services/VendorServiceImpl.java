package guru.springfamework.services;

import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorServiceImpl implements VendorService {
    private final VendorRepository vendorRepository;

    @Autowired
    public VendorServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @Override
    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    @Override
    public Vendor findVendorById(Long id) {
        return vendorRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public Vendor create(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    @Override
    public Vendor update(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    @Override
    public void delete(Long id) {
        vendorRepository.deleteById(id);
    }
}
