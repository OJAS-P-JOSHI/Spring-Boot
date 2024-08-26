package com.example.myproject.controllers;

import com.example.myproject.models.Blog;
import com.example.myproject.repositories.BlogRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {

    private final BlogRepository blogRepository;

    public BlogController(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @GetMapping
    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    @PostMapping
    public Blog createBlog(@RequestBody Blog blog) {
        return blogRepository.save(blog);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Blog> updateBlog(@PathVariable String id, @RequestBody Blog blogDetails) {
        Blog blog = blogRepository.findById(id).orElseThrow();
        blog.setTitle(blogDetails.getTitle());
        blog.setContent(blogDetails.getContent());
        return ResponseEntity.ok(blogRepository.save(blog));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlog(@PathVariable String id) {
        blogRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
