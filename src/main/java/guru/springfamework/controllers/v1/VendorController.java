package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.mapper.VendorMapper;
import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.domain.Vendor;
import guru.springfamework.services.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(VendorController.vendorUrl)
public class VendorController {
    public static final String vendorUrl = "api/v1/vendors";
    private final VendorService vendorService;
    private final VendorMapper vendorMapper;

    @Autowired
    public VendorController(VendorService vendorService, VendorMapper vendorMapper) {
        this.vendorService = vendorService;
        this.vendorMapper = vendorMapper;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<VendorDTO> getAllVendors(){
        return vendorService.getAllVendors()
                .stream()
                .map(vendorMapper::vendorToVendorDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO getVendorById(@PathVariable Long id){
        return vendorMapper.vendorToVendorDTO(vendorService.findVendorById(id));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO updateVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO){
        Vendor vendorToSave = vendorMapper.vendorDTOToVendor(vendorDTO);
        vendorToSave.setId(id);
        return vendorMapper.vendorToVendorDTO( vendorService.update(vendorToSave) );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendorDTO createVendor(@RequestBody VendorDTO vendorDTO){
        return vendorMapper.vendorToVendorDTO(vendorService.create(
                vendorMapper.vendorDTOToVendor(vendorDTO)
        ));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteVendorById(@PathVariable Long id){
        vendorService.delete(id);
    }
}
