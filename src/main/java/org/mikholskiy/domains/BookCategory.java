package org.mikholskiy.domains;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "book_categories")
public class BookCategory {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column(name = "name")
  private String name;
  @ManyToMany(
      cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},
      fetch = FetchType.LAZY)
  @JoinTable(
      name = "book_to_category",
      joinColumns = @JoinColumn(name = "category_id"),
      inverseJoinColumns = @JoinColumn(name = "book_id"))
  @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
  private Set<Book> books;

  public BookCategory() {
  }

  public BookCategory(String name) {
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<Book> getBooks() {
    return books;
  }

  public void setBooks(Book... book) {
    if (books == null) books = new HashSet<>();
    books.addAll(Arrays.asList(book));
  }

  @Override
  public String toString() {
    return "BookCategory{" +
        "id=" + id +
        ", name='" + name + '\'' +
        '}';
  }
}
