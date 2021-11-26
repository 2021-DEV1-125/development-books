package co.okizuys.bnp.developmentbooks.domain;

import org.apache.commons.lang3.RandomUtils;

import java.util.List;

public class Book {

  private final long bookId;

  private String title;

  private List<String> authors;

  private int year;

  private Book() {
    this.bookId = RandomUtils.nextLong();
  }

  public Book(String title, List<String> authors, int year) {
    this();
    this.title = title;
    this.authors = authors;
    this.year = year;
  }

  public long getBookId() {
    return bookId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public List<String> getAuthors() {
    return authors;
  }

  public void setAuthors(List<String> authors) {
    this.authors = authors;
  }
}
