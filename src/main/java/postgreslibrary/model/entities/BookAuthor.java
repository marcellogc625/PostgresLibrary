package postgreslibrary.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "bookauthors")
public class BookAuthor {

    @Id
    @OneToOne(targetEntity = Book.class)
    @JoinColumn(name = "isbn")
    private String isbn;

    @Id
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @Column(name = "seq_no")
    private Integer seqNo;

    public BookAuthor() {
    }

    public BookAuthor(String isbn, Author author, Integer seqNo) {
        this.isbn = isbn;
        this.author = author;
        this.seqNo = seqNo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Integer getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BookAuthor other = (BookAuthor) obj;
        if (isbn == null) {
            if (other.isbn != null)
                return false;
        } else if (!isbn.equals(other.isbn))
            return false;
        return true;
    }

}