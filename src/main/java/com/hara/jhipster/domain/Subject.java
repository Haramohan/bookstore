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

import com.hara.jhipster.domain.enumeration.StatusFlag;

/**
 * A Subject.
 */
@Entity
@Table(name = "subject")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Subject implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "subject_name", nullable = false)
    private String subjectName;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusFlag status;

    @NotNull
    @Column(name = "jhi_order", nullable = false)
    private Integer order;

    @Column(name = "date_added")
    private LocalDate dateAdded;

    @Column(name = "date_modified")
    private LocalDate dateModified;

    @OneToMany(mappedBy = "subjectName")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Book> subjectNames = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("categoryNames")
    private Category subjectCategoryName;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public Subject subjectName(String subjectName) {
        this.subjectName = subjectName;
        return this;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public StatusFlag getStatus() {
        return status;
    }

    public Subject status(StatusFlag status) {
        this.status = status;
        return this;
    }

    public void setStatus(StatusFlag status) {
        this.status = status;
    }

    public Integer getOrder() {
        return order;
    }

    public Subject order(Integer order) {
        this.order = order;
        return this;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public Subject dateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
        return this;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }

    public LocalDate getDateModified() {
        return dateModified;
    }

    public Subject dateModified(LocalDate dateModified) {
        this.dateModified = dateModified;
        return this;
    }

    public void setDateModified(LocalDate dateModified) {
        this.dateModified = dateModified;
    }

    public Set<Book> getSubjectNames() {
        return subjectNames;
    }

    public Subject subjectNames(Set<Book> books) {
        this.subjectNames = books;
        return this;
    }

    public Subject addSubjectName(Book book) {
        this.subjectNames.add(book);
        book.setSubjectName(this);
        return this;
    }

    public Subject removeSubjectName(Book book) {
        this.subjectNames.remove(book);
        book.setSubjectName(null);
        return this;
    }

    public void setSubjectNames(Set<Book> books) {
        this.subjectNames = books;
    }

    public Category getSubjectCategoryName() {
        return subjectCategoryName;
    }

    public Subject subjectCategoryName(Category category) {
        this.subjectCategoryName = category;
        return this;
    }

    public void setSubjectCategoryName(Category category) {
        this.subjectCategoryName = category;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Subject)) {
            return false;
        }
        return id != null && id.equals(((Subject) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Subject{" +
            "id=" + getId() +
            ", subjectName='" + getSubjectName() + "'" +
            ", status='" + getStatus() + "'" +
            ", order=" + getOrder() +
            ", dateAdded='" + getDateAdded() + "'" +
            ", dateModified='" + getDateModified() + "'" +
            "}";
    }
}
