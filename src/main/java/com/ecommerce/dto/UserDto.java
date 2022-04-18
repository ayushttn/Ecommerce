package com.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    @Email
    @NotNull
    private String email;
    @Size(min = 3)
    @NotNull
    private String firstName;
    @Size(min = 3)
    private String middleName;
    @Size(min = 3)
    private String lastName;
    @NotNull
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,15}$")
    private String password;
    private String confirmPassword;
    @Pattern(regexp = "^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[1-9A-Z]{1}Z[0-9A-Z]{1}$")
    private String gst;
    private Long companyContact;
    private String companyName;
    private Long contact;
    private String city;
    private String state;
    private String country;
    private String addressLine;
    private Integer zipCode;
    private String label;
}
