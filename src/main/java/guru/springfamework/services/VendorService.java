package guru.springfamework.services;

import guru.springfamework.domain.Vendor;

import java.util.List;

public interface VendorService {
    List<Vendor> getAllVendors();
    Vendor findVendorById(Long id);
    Vendor create(Vendor vendor);
    Vendor update(Vendor vendor);
    void delete(Long id);
}
