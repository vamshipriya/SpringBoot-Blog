package blog.services;

import blog.models.Post;
import blog.models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PostServiceStubImpl implements PostService{
    private List<Post> posts = new ArrayList<Post>() {{
      add(new Post(1L,"First Post","<p>Line #1</p>",null));
      add(new Post(2L,"Second Post","<p>Line #2</p>",new User(10L,"emma204","emma long")));
        add(new Post(4L,"fourth Post","<p>Line #1</p>",null));
        add(new Post(6L,"sixth Post","<p>Line #1</p>",null));
    }};

    @Override
    public List<Post> findAll() {
        return this.posts;
    }

    @Override
    public List<Post> findLatest5() {
        return this.posts.stream().sorted((a,b)->b.getDate().compareTo(a.getDate()))
                .limit(5).collect(Collectors.toList());
    }

    @Override
    public Post findById(Long id) {
        return this.posts.stream()
                .filter(p-> Objects.equals(p.getId(),id))
                .findFirst().orElse(null);
    }

    @Override
    public Post create(Post post) {
        post.setId(this.posts.stream().mapToLong(p->p.getId()).max().getAsLong()+1);
        this.posts.add(post);
        return post;
    }

    @Override
    public Post edit(Post post) {
        for(int i=0;i<this.posts.size();i++)
        {
            if(Objects.equals(this.posts.get(i).getId(),post.getId()))
            {
                this.posts.set(i,post);
                return post;
            }
        }
        throw new RuntimeException("Post not found"+post.getId());
    }

    @Override
    public void deleteById(Long id) {
        for(int i=0;i<this.posts.size();i++)
        {
            if(Objects.equals(this.posts.get(i).getId(),id))
            {
                this.posts.remove(i);
                return;
            }
        }
        throw new RuntimeException("Post not found" +id);

    }
}