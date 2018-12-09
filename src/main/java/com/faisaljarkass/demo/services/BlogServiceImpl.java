package com.faisaljarkass.demo.services;

import com.faisaljarkass.demo.domains.Blog;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    private List<Blog> list = new ArrayList<>();
    private boolean flag = false;

    @Override
    public List<Blog> getAllBlogs() {
        if (!flag) {
            setupDummyData();
            flag = true;
        }
        return list;
    }

    @Override
    public void addBlog(String text) {
        list.add(new Blog((long) list.size(), text));
    }

    private void setupDummyData() {
        for (int i = 0; i < 3; i++) {
            list.add(new Blog((long) i, "Some text: " + (long) i));
        }
    }

}
