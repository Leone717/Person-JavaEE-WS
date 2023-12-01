package hu.leone.webservice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateful;
import javax.jws.*;

import hu.leone.datatypes.Post;

@WebService
public class BlogEngine {

	private List<Post> posts = new ArrayList<>();

	@WebMethod
	public void addToBlog(String post) {
		posts.add(new Post(post));
	}
	
	@WebMethod
    public Post[] getBlogPosts(String orderMode) {
		// Order posts by timestamp - orderMode ASC or DESC
		Collections.sort(posts, new Comparator<Post>() {
            @Override
            public int compare(Post p1, Post p2) {
                Date date1 = p1.getTimestamp();
                Date date2 = p2.getTimestamp();

                if ("ASC".equals(orderMode.toUpperCase())) {
                	return date1.compareTo(date2);
                }
                
                // DESC
                return date2.compareTo(date1);
                
            }
        });
		
		Post[] retval = null;
		if (posts != null) {
			retval = (Post[])posts.toArray(new Post[posts.size()]);
		}
		return retval;
	}
}

