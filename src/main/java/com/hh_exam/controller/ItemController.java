package com.hh_exam.controller;

import com.hh_exam.dto.request.PostRequestDTO;
import com.hh_exam.dto.response.GetResponseDTO;
import com.hh_exam.dto.response.MessageResponse;
import com.hh_exam.dto.response.PostResponseDTO;
import com.hh_exam.dto.response.UpdateResponseDTO;
import com.hh_exam.entity.Item;
import com.hh_exam.service.ItemService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping()
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

    @GetMapping()
    public ResponseEntity<List<GetResponseDTO>> getAllPosts() {
        List<GetResponseDTO> posts = itemService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateResponseDTO> updatePost(@PathVariable Long id,
        @RequestBody PostRequestDTO postRequestDTO) {
        UpdateResponseDTO updatedPost = itemService.updatePost(id, postRequestDTO);
        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deletePost(@PathVariable Long id) {
        itemService.deletePost(id);
        return ResponseEntity.ok().body(new MessageResponse("삭제완료"));
    }
}
