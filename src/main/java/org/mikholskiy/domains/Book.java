package org.mikholskiy.domains;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.*;


@Entity
@Table(name = "books")
public class Book {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "title")
  private String title;

  @Column(name = "author")
  private String author;

  @Column(name = "publication_date")
  private Date publicationDate;

  @Column(name = "publication")
  private int publication;

  @ManyToMany(
      cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},
      fetch = FetchType.LAZY)
  @JoinTable(
      name = "book_to_category",
      joinColumns = @JoinColumn(name = "book_id"),
      inverseJoinColumns = @JoinColumn(name = "category_id"))
  @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
  private Set<BookCategory> categories;

  @ManyToOne(
      cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},
      fetch = FetchType.EAGER)
  @JoinColumn(name = "client_id")
  @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
  private Client tenant;

  public Book() {
  }

  public Book(String title, String author, Date publicationDate, int publication) {
    this.title = title;
    this.author = author;
    this.publicationDate = publicationDate;
    this.publication = publication;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public Date getPublicationDate() {
    return publicationDate;
  }

  public void setPublicationDate(Date publicationDate) {
    this.publicationDate = publicationDate;
  }

  public int getPublication() {
    return publication;
  }

  public void setPublication(int publication) {
    this.publication = publication;
  }

  public Set<BookCategory> getCategories() {
    return categories;
  }

  public void setCategories(BookCategory... category) {
    if (categories == null) categories = new HashSet<>();
    categories.addAll(Arrays.asList(category));
  }

  public Client getTenant() {
    return tenant;
  }

  public void setTenant(Client tenant) {
    this.tenant = tenant;
  }

  @Override
  public String toString() {
    return "Book{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", author='" + author + '\'' +
        ", publicationDate=" + publicationDate +
        ", publication=" + publication +
        '}';
  }
}
