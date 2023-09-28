package com.myrobin.postr.controller;

import com.myrobin.postr.dto.PostDto;
import com.myrobin.postr.service.PostrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PostrController {

    @Autowired
    private PostrService postrService;

    @GetMapping(path = "/")
    public ResponseEntity<Map<String, Object>> postrPingTest() {
        // This controller only use for testing. will be deleted
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("data", "POSTR app RUNNING");
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/posts")
    public ResponseEntity<Map<String, Object>> createPost(@RequestBody Map<String, String> requestBody) {
        if (!isContentValid(requestBody.get("post"))) {
            Map<String, Object> errResponse = new HashMap<>();
            errResponse.put("error", "Post must be between 1 and 100 characters");
            return ResponseEntity.badRequest().body(errResponse);
        }
        String postId = postrService.saveNewPost(requestBody.get("post"));

        Map<String, String> responseData = new HashMap<>();
        responseData.put("postId", postId);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("data", responseData);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(path = "/posts")
    public ResponseEntity<Map<String, Object>> getAllPosts() {
        List<PostDto> postDtos = postrService.getAllPosts();
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("data", postDtos);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/posts/users")
    public ResponseEntity<Map<String, Object>> getAllUsers(@RequestParam(name = "limit") String limit) {
        List<String> users = postrService.getAllUsers(limit);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("data", users);
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/posts/{postId}/comments")
    public ResponseEntity<Map<String, Object>> createComment(@RequestBody Map<String, String> requestBody,
                                                             @PathVariable String postId) {
        if (!isContentValid(requestBody.get("comment"))) {
            Map<String, Object> errResponse = new HashMap<>();
            errResponse.put("error", "Comment must be between 1 and 100 characters");
            return ResponseEntity.badRequest().body(errResponse);
        }
        String commentId = postrService.saveNewComment(requestBody.get("comment"), postId);

        Map<String, String> responseData = new HashMap<>();
        responseData.put("commentId", commentId);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("data", responseData);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    private boolean isContentValid(String content) {
        return !content.isEmpty() && content.length() <= 100;
    }
}
