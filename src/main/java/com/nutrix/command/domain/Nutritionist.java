package com.nutrix.command.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name="nutritionist")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Nutritionist implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="username", nullable = false, length = 16)
    private String username;

    @Column(name="password", nullable = false, length = 50)
    private String password;

    @Column(name="first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name="last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name="email", nullable = false, length = 50)
    private String email;

    @Column(name="cnp_number", nullable = false, length = 6)
    private Integer cnpNumber;

    @Column(name="created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    public Nutritionist save(Nutritionist nutritionist) throws Exception{return null;}
    public void delete(Integer id) throws Exception{}
    protected List<Nutritionist> getAll() throws  Exception{return null;}
    public Optional<Nutritionist> getById(Integer id) throws Exception{return null;};
    public Nutritionist findByUsername(String username) throws Exception{return null;};
    public Nutritionist findByCnpNumber(Integer cnp_number) throws Exception{return null;};
    public List<Nutritionist> findByFirstName(String firstname) throws Exception{return null;};
    public List<Nutritionist> findByLastName(String lastname) throws Exception{return null;};
    public List<Nutritionist> findByFirstNameAndLastName(String firstname, String lastname) throws Exception{return null;};

}