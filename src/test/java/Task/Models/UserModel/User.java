package Task.Models.UserModel;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class User {
    @EqualsAndHashCode.Exclude
    private int id;
    private String name;
    private String username;
    private String email;
    
    private Address address;
    
    private String phone;
    private String website;
    
    private Company company;
    
}
