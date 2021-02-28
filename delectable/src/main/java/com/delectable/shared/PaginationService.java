package com.delectable.shared;

import java.util.HashMap;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class PaginationService {

    public Pageable getPageable(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Direction.ASC, "name"));
        return pageable;
    }

    public <T> Map<String, Object> buildPageableResponse(Page<T> pages) {
        Map<String, Object> response = new HashMap<>();
        response.put("content", pages.getContent());
        response.put("page", pages.getNumber());
        response.put("size", pages.getSize());
        response.put("totalPages", pages.getTotalPages());
        response.put("totalElements", pages.getTotalElements());
        return response;
    }

}
