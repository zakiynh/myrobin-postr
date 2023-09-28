package com.myrobin.postr.service;

import com.myrobin.postr.dto.PostDto;
import com.myrobin.postr.repository.CommentRepository;
import com.myrobin.postr.repository.PostRepository;
import com.myrobin.postr.table.Comment;
import com.myrobin.postr.table.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostrService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    public String saveNewPost(String post) {
        Post newPost = new Post();
        newPost.setPost(post);
        postRepository.save(newPost);
        return newPost.getId();
    }

    public List<PostDto> getAllPosts() {
        List<Post> postList = postRepository.findAll();
        return postList.stream().map(post -> {
            PostDto postDto = new PostDto();
            postDto.setPost(post.getPost());
            return postDto;
        }).collect(Collectors.toList());
    }

    public String saveNewComment(String comment, String postId) {
        Optional<Post> postOpt = postRepository.findById(postId);
        if (postOpt.isEmpty()) {
            throw new RuntimeException("Post not found");
        }
        Comment newComment = new Comment();
        newComment.setPost(postOpt.get());
        newComment.setComment(comment);
        commentRepository.save(newComment);
        return newComment.getId();
    }
}
