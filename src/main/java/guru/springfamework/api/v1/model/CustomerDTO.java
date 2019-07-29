package guru.springfamework.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CustomerDTO {
    @ApiModelProperty(value = "last name", required = true)
    private String lastName;
    private String firstName;
    private String customerUrl;
}
