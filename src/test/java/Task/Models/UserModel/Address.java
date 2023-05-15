package Task.Models.UserModel;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Address {
    
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;
    
}
