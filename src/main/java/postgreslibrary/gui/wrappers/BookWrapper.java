package postgreslibrary.gui.wrappers;

public class BookWrapper {

    private String bookTitle;
    private String bookIsbn;
    private Double bookPrice;
    private Integer bookSeqNo;
    private String bookAuthor;
    private String bookPublisher;
    
    public BookWrapper() {
    }

    public BookWrapper(String bookTitle, String bookIsbn, Double bookPrice, 
    Integer bookSeqNo, String bookAuthor, String bookPublisher) {
        this.bookTitle = bookTitle;
        this.bookIsbn = bookIsbn;
        this.bookPrice = bookPrice;
        this.bookSeqNo = bookSeqNo;
        this.bookAuthor = bookAuthor;
        this.bookPublisher = bookPublisher;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookIsbn() {
        return bookIsbn;
    }

    public void setBookIsbn(String bookIsbn) {
        this.bookIsbn = bookIsbn;
    }

    public Double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(Double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public Integer getBookSeqNo() {
        return bookSeqNo;
    }

    public void setBookSeqNo(Integer bookSeqNo) {
        this.bookSeqNo = bookSeqNo;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookPublisher() {
        return bookPublisher;
    }

    public void setBookPublisher(String bookPublisher) {
        this.bookPublisher = bookPublisher;
    }

}