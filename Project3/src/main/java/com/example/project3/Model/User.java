package com.example.project3.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;


    @NotEmpty(message = "user name can not be null")
    @Size(min = 3,max = 6,message = "user name can not be less than 3 ir more than 6")
    @Column(columnDefinition = "varchar(6) not null unique")
    private String userName;


    @Column(columnDefinition = "varchar(200) not null")
    @NotEmpty(message = "password can not be null")
    private String password;


    @NotEmpty(message = "name can not be null ")
    @Size(min = 2, max = 20, message = "name can not be less than 2 or more than 20")
    @Column(columnDefinition = "varchar(20) not null ")
    private String name ;

 @Email(message = "you should enter Valid email ")
 @Size(max = 20, message = "email character can not be more tha 20 character")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String email;


    @Column(columnDefinition = "varchar(8) not null")
    @Pattern(regexp = "^(CUSTOMER|EMPLOYEE|ADMIN)$")
    private String role;


    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Customer customer;


    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Employee employee;

 @Override
 public Collection<? extends GrantedAuthority> getAuthorities() {
  return  Collections.singleton(new SimpleGrantedAuthority(this.role));

 }

 @Override
 public String getUsername() {
  return "";
 }

 @Override
 public boolean isAccountNonExpired() {
  return true;
 }

 @Override
 public boolean isAccountNonLocked() {
  return true;
 }

 @Override
 public boolean isCredentialsNonExpired() {
  return true;
 }

 @Override
 public boolean isEnabled() {
  return true;
 }



}
