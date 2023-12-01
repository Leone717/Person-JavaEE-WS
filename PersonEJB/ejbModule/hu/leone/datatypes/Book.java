package hu.leone.datatypes;

import java.io.Serializable;

public class Book implements Serializable {

	private String title;
	private String author;
	private String publicationDate;
	
	public Book(String title, String author, String publicationDate) {
		super();
		this.title = title;
		this.author = author;
		this.publicationDate = publicationDate;
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

	public String getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}

	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", publicationDate=" + publicationDate + "]";
	}	
	
}
