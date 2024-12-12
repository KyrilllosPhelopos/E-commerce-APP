package com.sawiris.ecommerce.request;
import com.sawiris.ecommerce.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RegisterRequest {

	  @NotEmpty(message = "First name cannot be empty")
	  private String firstname;

	  @NotEmpty(message = "Last name cannot be empty")
	  private String lastname;

	  @NotEmpty(message = "Email cannot be empty")
	  @Email(message = "Invalid email format")
	  private String email;

	  @NotEmpty(message = "Password cannot be empty")
	  private String password;

	  @NotNull(message = "Role cannot be null")
	  private Role role;
	  @NotEmpty(message = "phone cannot be empty")
	  private String phone;
	  @NotEmpty(message = "address cannot be empty")
	  private String address;
	  

}