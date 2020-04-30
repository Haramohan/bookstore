package com.hara.jhipster.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.hara.jhipster.domain.enumeration.Lang;

import com.hara.jhipster.domain.enumeration.StatusFlag;

/**
 * A Book.
 */
@Entity
@Table(name = "book")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "booktitle", nullable = false)
    private String booktitle;

    @NotNull
    @Column(name = "text", nullable = false)
    private String text;

    @NotNull
    @Column(name = "isbn", nullable = false)
    private String isbn;

    @NotNull
    @Column(name = "publisher", nullable = false)
    private String publisher;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "language", nullable = false)
    private Lang language;

    @NotNull
    @Column(name = "pubyear", nullable = false)
    private Integer pubyear;

    @NotNull
    @Column(name = "pages", nullable = false)
    private Integer pages;

    @NotNull
    @Column(name = "price", nullable = false)
    private Integer price;

    @NotNull
    @Column(name = "author", nullable = false)
    private String author;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusFlag status;

    @Column(name = "date_added")
    private LocalDate dateAdded;

    @Column(name = "date_modified")
    private LocalDate dateModified;

    @OneToMany(mappedBy = "bookISBN")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Chapter> isbns = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("subjectNames")
    private Subject subjectName;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBooktitle() {
        return booktitle;
    }

    public Book booktitle(String booktitle) {
        this.booktitle = booktitle;
        return this;
    }

    public void setBooktitle(String booktitle) {
        this.booktitle = booktitle;
    }

    public String getText() {
        return text;
    }

    public Book text(String text) {
        this.text = text;
        return this;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIsbn() {
        return isbn;
    }

    public Book isbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublisher() {
        return publisher;
    }

    public Book publisher(String publisher) {
        this.publisher = publisher;
        return this;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Lang getLanguage() {
        return language;
    }

    public Book language(Lang language) {
        this.language = language;
        return this;
    }

    public void setLanguage(Lang language) {
        this.language = language;
    }

    public Integer getPubyear() {
        return pubyear;
    }

    public Book pubyear(Integer pubyear) {
        this.pubyear = pubyear;
        return this;
    }

    public void setPubyear(Integer pubyear) {
        this.pubyear = pubyear;
    }

    public Integer getPages() {
        return pages;
    }

    public Book pages(Integer pages) {
        this.pages = pages;
        return this;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getPrice() {
        return price;
    }

    public Book price(Integer price) {
        this.price = price;
        return this;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public Book author(String author) {
        this.author = author;
        return this;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public StatusFlag getStatus() {
        return status;
    }

    public Book status(StatusFlag status) {
        this.status = status;
        return this;
    }

    public void setStatus(StatusFlag status) {
        this.status = status;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public Book dateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
        return this;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }

    public LocalDate getDateModified() {
        return dateModified;
    }

    public Book dateModified(LocalDate dateModified) {
        this.dateModified = dateModified;
        return this;
    }

    public void setDateModified(LocalDate dateModified) {
        this.dateModified = dateModified;
    }

    public Set<Chapter> getIsbns() {
        return isbns;
    }

    public Book isbns(Set<Chapter> chapters) {
        this.isbns = chapters;
        return this;
    }

    public Book addIsbn(Chapter chapter) {
        this.isbns.add(chapter);
        chapter.setBookISBN(this);
        return this;
    }

    public Book removeIsbn(Chapter chapter) {
        this.isbns.remove(chapter);
        chapter.setBookISBN(null);
        return this;
    }

    public void setIsbns(Set<Chapter> chapters) {
        this.isbns = chapters;
    }

    public Subject getSubjectName() {
        return subjectName;
    }

    public Book subjectName(Subject subject) {
        this.subjectName = subject;
        return this;
    }

    public void setSubjectName(Subject subject) {
        this.subjectName = subject;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Book)) {
            return false;
        }
        return id != null && id.equals(((Book) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Book{" +
            "id=" + getId() +
            ", booktitle='" + getBooktitle() + "'" +
            ", text='" + getText() + "'" +
            ", isbn='" + getIsbn() + "'" +
            ", publisher='" + getPublisher() + "'" +
            ", language='" + getLanguage() + "'" +
            ", pubyear=" + getPubyear() +
            ", pages=" + getPages() +
            ", price=" + getPrice() +
            ", author='" + getAuthor() + "'" +
            ", status='" + getStatus() + "'" +
            ", dateAdded='" + getDateAdded() + "'" +
            ", dateModified='" + getDateModified() + "'" +
            "}";
    }
}
