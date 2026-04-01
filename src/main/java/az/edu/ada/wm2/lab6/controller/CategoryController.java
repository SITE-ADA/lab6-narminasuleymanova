package az.edu.ada.wm2.lab6.controller;

import az.edu.ada.wm2.lab6.model.dto.CategoryRequestDto;
import az.edu.ada.wm2.lab6.model.dto.CategoryResponseDto;
import az.edu.ada.wm2.lab6.model.dto.ProductResponseDto;
import az.edu.ada.wm2.lab6.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDto> create(@RequestBody CategoryRequestDto dto) {
        CategoryResponseDto created = categoryService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> getAll() {
        List<CategoryResponseDto> categories = categoryService.getAll();
        return ResponseEntity.ok(categories);
    }

    @PostMapping("/{categoryId}/products/{productId}")
    public ResponseEntity<CategoryResponseDto> addProduct(@PathVariable UUID categoryId, @PathVariable UUID productId) {
        CategoryResponseDto updated = categoryService.addProduct(categoryId, productId);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/{categoryId}/products")
    public ResponseEntity<List<ProductResponseDto>> getProducts(@PathVariable UUID categoryId) {
        List<ProductResponseDto> products = categoryService.getProducts(categoryId);
        return ResponseEntity.ok(products);
    }
}
