package com.faisaljarkass.demo.services;

import com.faisaljarkass.demo.domains.Blog;

import java.util.List;

public interface BlogService {

    List<Blog> getAllBlogs();

    void addBlog(String text);
}
