package com.hh_exam.controller;

import com.hh_exam.dto.request.PostRequestDTO;
import com.hh_exam.dto.response.PostResponseDTO;
import com.hh_exam.entity.Item;
import com.hh_exam.service.ItemService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("/post")
    public ResponseEntity<PostResponseDTO> createPost(@RequestBody PostRequestDTO postRequestDTO) {
        Item createdItem = itemService.createPost(postRequestDTO);
        PostResponseDTO responseDTO = new PostResponseDTO(
            createdItem.getId(),
            createdItem.getUsername(),
            createdItem.getTitle(),
            createdItem.getContent(),
            createdItem.getPrice()
        );
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/post")
    public ResponseEntity<List<PostResponseDTO>> getAllPosts() {
        List<PostResponseDTO> posts = itemService.getAllPosts();
        return ResponseEntity.ok(posts);
    }
}
