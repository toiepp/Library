package org.mikholskiy.domains;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "clients")
public class Client {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "surname")
  private String surname;

  @Column(name = "age")
  private int age;

  @OneToMany(
      cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH},
      fetch = FetchType.LAZY,
      mappedBy = "tenant")
  @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
  private Set<Book> books;

  public Client() {
  }

  public Client(String firstName, String surname, int age) {
    this.firstName = firstName;
    this.surname = surname;
    this.age = age;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public Set<Book> getBooks() {
    return books;
  }

  public void setBooks(Set<Book> books) {
    this.books = books;
  }

  @Override
  public String toString() {
    return "Client{" +
        "id=" + id +
        ", firstName='" + firstName + '\'' +
        ", surname='" + surname + '\'' +
        ", age=" + age +
        '}';
  }
}
