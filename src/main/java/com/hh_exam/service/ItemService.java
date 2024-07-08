package com.hh_exam.service;

import com.hh_exam.dto.request.PostRequestDTO;
import com.hh_exam.dto.response.PostResponseDTO;
import com.hh_exam.entity.Item;
import com.hh_exam.repository.ItemRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public Item createPost(PostRequestDTO postRequestDTO) {
        Item item = Item.builder()
            .username(postRequestDTO.username())
            .title(postRequestDTO.title())
            .content(postRequestDTO.content())
            .price(postRequestDTO.price())
            .build();
        return itemRepository.save(item);
    }

    @Transactional(readOnly = true)
    public List<PostResponseDTO> getAllPosts() {
        List<Item> items = itemRepository.findAll();
        return items.stream()
            .map(item -> new PostResponseDTO(
                item.getId(),
                item.getUsername(),
                item.getTitle(),
                item.getContent(),
                item.getPrice()
            ))
            .collect(Collectors.toList());
    }
}
