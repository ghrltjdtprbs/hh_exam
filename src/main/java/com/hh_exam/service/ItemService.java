package com.hh_exam.service;

import com.hh_exam.dto.request.PostRequestDTO;
import com.hh_exam.dto.response.GetResponseDTO;
import com.hh_exam.dto.response.UpdateResponseDTO;
import com.hh_exam.entity.Item;
import com.hh_exam.repository.ItemRepository;
import java.util.List;
import java.util.Optional;
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
    public List<GetResponseDTO> getAllPosts() {
        List<Item> items = itemRepository.findAll();
        return items.stream()
            .map(item -> new GetResponseDTO(
                item.getId(),
                item.getTitle(),
                item.getUsername(),
                item.getPrice()
            ))
            .collect(Collectors.toList());
    }

    public UpdateResponseDTO updatePost(Long id, PostRequestDTO updateRequestDTO) {
        Optional<Item> optionalItem = itemRepository.findById(id);

        if (optionalItem.isPresent()) {
            Item item = optionalItem.get();
            Item updatedItem = Item.builder()
                .id(item.getId())
                .title(updateRequestDTO.title())
                .content(updateRequestDTO.content())
                .price(updateRequestDTO.price())
                .username(updateRequestDTO.username())
                .build();
            itemRepository.save(updatedItem);

            return new UpdateResponseDTO(
                updatedItem.getId(),
                updatedItem.getTitle(),
                updatedItem.getContent(),
                updatedItem.getPrice(),
                updatedItem.getUsername()
            );
        } else {
            throw new RuntimeException("존재하지 않는 게시글입니다.");
        }
    }

    public void deletePost(Long id) {
        if (itemRepository.existsById(id)) {
            itemRepository.deleteById(id);
        } else {
            throw new RuntimeException("존재하지 않는 게시글입니다.");
        }
    }
}
