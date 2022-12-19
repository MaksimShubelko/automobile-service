package com.example.automobileservice.entity;

import com.example.automobileservice.converter.UserRoleConverter;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class UserEntity extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Convert(converter = UserRoleConverter.class)
    @Column(name = "role")
    private UserRole role;

    @OneToOne
    @JoinColumn(name = "request_id")
    private Request request;

    @Builder.Default
    @Transient
    private boolean accountNonExpired = true;

    @Builder.Default
    @Transient
    private boolean accountNonLocked = true;

    @Builder.Default
    @Transient
    private boolean credentialsNonExpired = true;

    @Builder.Default
    @Transient
    private boolean enabled = true;




}
