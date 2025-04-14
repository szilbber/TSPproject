package com.example.demo.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
 // указываем имя таблицы в базе данных
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // автоматически генерируем значение для id
    @Column(name = "id_user")
    private Long id_user;
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name = "phone")
    private String phone;
    @Column(name = "mail") //валидация//
    private String mail;
    @Column(name = "bday")
    private LocalDate bday;

    @Enumerated(EnumType.STRING)
    @Column(name  = "user_role")
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "favourite_recipes",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_recipe")
    )
    private Set<Recipe> favouriteRecipes = new HashSet<>();

    @OneToMany(mappedBy = "userId", orphanRemoval = false)
    private Set<Recipe> recipes = new HashSet<>();

// Конструкторы, геттеры и сеттеры

    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public void setResipes(Set<Recipe> recipes) {
        this.recipes= recipes;
    }

    public void setRoleAdmin(){
        this.role = Role.ADMIN;
    }
    public Set<Recipe> getFavouriteRecipes() {
        return favouriteRecipes;
    }

    public void setFavouriteRecipes(Set<Recipe> favouriteRecipes) {
        this.favouriteRecipes = favouriteRecipes;
    }
    // Конструкторы, геттеры и сеттеры
    public User() {}

    public User(String mail, String password, String name, String phone, LocalDate bday) {

        this.mail = mail;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.bday = bday;
    }

    // Геттеры и сеттеры
    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getBday() {
        return bday;
    }

    public void setBday(LocalDate bday) {
        this.bday = bday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(("ROLE_"+ role.name())));
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

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }
}