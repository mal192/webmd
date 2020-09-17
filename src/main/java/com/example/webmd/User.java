package com.example.webmd;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table (name = "usr") //имя таблицы
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private boolean active;

    //добавляем роли для пользователей admin, user, root
    //FetchType.LAZY и  FetchType.EAGER параметр отвечающий за загрузку данных
    //параметр LAZY грузится долго, занимает меньше памяти. Грузится в момент обращения
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    //Создаем табличку пользовательских групп, которую соединяется с текущей табличкой (group) через
    //user_id
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING) // enup хранить в виде строки
    private Set<Role> roles; //может лучше group

    //для всех создаем Getter and Setter
    //запускаем генератор конструкторов alt+insert -> и выбираем Getter and Setter


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
///V4 -> автоматом добавленная implents
    @Override
    public boolean isAccountNonExpired() {  //делаем true
        return true;
    }

    @Override
    public boolean isAccountNonLocked() { //делаем true
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() { //делаем true
        return true;
    }

    @Override
    public boolean isEnabled() { //делаем isActive()
        return isActive();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { ///делаем getRoles
        return getRoles();
    }
    //<---V4

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    ///V4 ->


}
