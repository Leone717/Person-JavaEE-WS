package hu.leone.datatypes;

import java.io.Serializable;
import java.util.Date;

public class Post implements Serializable{

	private String post;
	private Date timestamp;
	
	public Post() {
		super();
	}
	public Post(String post) {
		super();
		this.post = post;
		this.timestamp = new Date();
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
}
