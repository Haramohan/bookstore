package com.hara.jhipster.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

import com.hara.jhipster.domain.enumeration.ChapterType;

import com.hara.jhipster.domain.enumeration.StatusFlag;

/**
 * A Chapter.
 */
@Entity
@Table(name = "chapter")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Chapter implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "chapter_title", nullable = false)
    private String chapterTitle;

    @NotNull
    @Column(name = "chapter_number", nullable = false)
    private String chapterNumber;

    @NotNull
    @Column(name = "pages", nullable = false)
    private Integer pages;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private ChapterType type;

    @NotNull
    @Column(name = "jhi_order", nullable = false)
    private Integer order;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusFlag status;

    @Column(name = "date_added")
    private LocalDate dateAdded;

    @Column(name = "date_modified")
    private LocalDate dateModified;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("isbns")
    private Book bookISBN;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChapterTitle() {
        return chapterTitle;
    }

    public Chapter chapterTitle(String chapterTitle) {
        this.chapterTitle = chapterTitle;
        return this;
    }

    public void setChapterTitle(String chapterTitle) {
        this.chapterTitle = chapterTitle;
    }

    public String getChapterNumber() {
        return chapterNumber;
    }

    public Chapter chapterNumber(String chapterNumber) {
        this.chapterNumber = chapterNumber;
        return this;
    }

    public void setChapterNumber(String chapterNumber) {
        this.chapterNumber = chapterNumber;
    }

    public Integer getPages() {
        return pages;
    }

    public Chapter pages(Integer pages) {
        this.pages = pages;
        return this;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public ChapterType getType() {
        return type;
    }

    public Chapter type(ChapterType type) {
        this.type = type;
        return this;
    }

    public void setType(ChapterType type) {
        this.type = type;
    }

    public Integer getOrder() {
        return order;
    }

    public Chapter order(Integer order) {
        this.order = order;
        return this;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public StatusFlag getStatus() {
        return status;
    }

    public Chapter status(StatusFlag status) {
        this.status = status;
        return this;
    }

    public void setStatus(StatusFlag status) {
        this.status = status;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public Chapter dateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
        return this;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }

    public LocalDate getDateModified() {
        return dateModified;
    }

    public Chapter dateModified(LocalDate dateModified) {
        this.dateModified = dateModified;
        return this;
    }

    public void setDateModified(LocalDate dateModified) {
        this.dateModified = dateModified;
    }

    public Book getBookISBN() {
        return bookISBN;
    }

    public Chapter bookISBN(Book book) {
        this.bookISBN = book;
        return this;
    }

    public void setBookISBN(Book book) {
        this.bookISBN = book;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Chapter)) {
            return false;
        }
        return id != null && id.equals(((Chapter) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Chapter{" +
            "id=" + getId() +
            ", chapterTitle='" + getChapterTitle() + "'" +
            ", chapterNumber='" + getChapterNumber() + "'" +
            ", pages=" + getPages() +
            ", type='" + getType() + "'" +
            ", order=" + getOrder() +
            ", status='" + getStatus() + "'" +
            ", dateAdded='" + getDateAdded() + "'" +
            ", dateModified='" + getDateModified() + "'" +
            "}";
    }
}
