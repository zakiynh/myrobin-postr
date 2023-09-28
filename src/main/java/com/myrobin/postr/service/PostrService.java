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
import java.util.Random;
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
        newPost.setUsername(generateRandomUsername());
        postRepository.save(newPost);
        return newPost.getId();
    }

    public List<PostDto> getAllPosts() {
        List<Post> postList = postRepository.findAll();
        return postList.stream().map(post -> {
            PostDto postDto = new PostDto();
            postDto.setPost(post.getPost());
            List<String> comments = post.getComments().stream().map(Comment::getComment).collect(Collectors.toList());
            postDto.setComments(comments);
            return postDto;
        }).collect(Collectors.toList());
    }

    public List<String> getAllUsers(String limit) {
        return postRepository.findAllUsers(limit);
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

    private String generateRandomUsername() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder username = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < 11; i++) {
            int index = random.nextInt(characters.length());
            username.append(characters.charAt(index));
        }

        return username.toString();
    }
}
