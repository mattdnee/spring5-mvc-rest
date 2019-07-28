package guru.springfamework.api.v1.model;

import lombok.Data;

@Data
public class CustomerDTO {
    private String lastName;
    private String firstName;
    private String customerUrl;
}
