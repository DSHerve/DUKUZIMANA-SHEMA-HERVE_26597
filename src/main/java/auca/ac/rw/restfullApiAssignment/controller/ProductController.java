package auca.ac.rw.restfullApiAssignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import auca.ac.rw.restfullApiAssignment.modal.Product;
import auca.ac.rw.restfullApiAssignment.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productserve;

    // Create Product
    @PostMapping(value = "/addProduct", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        String response = productserve.saveProduct(product);
        if(response.equals("Product saved successfully.")) {
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

    // Read All Products
    @GetMapping("/getAllProducts")
    public List<Product> getAllProducts() {
        return productserve.getAllProducts();
    }

    // Update entire product
    @PutMapping("/updateProduct")
    public ResponseEntity<String> updateProduct(@RequestBody Product product) {
        String response = productserve.updateProduct(product);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Partial update: stock quantity only
    @PatchMapping("/updateProductStock/{id}/{stockQuantity}")
    public ResponseEntity<String> updateProductStock(@PathVariable Long id, @PathVariable int stockQuantity) {
        String response = productserve.updateProductStock(id, stockQuantity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Delete Product
    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        String response = productserve.deleteProduct(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}