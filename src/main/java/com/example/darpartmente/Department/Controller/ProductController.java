package com.example.darpartmente.Department.Controller;

import com.example.darpartmente.Department.Model.Product;
import com.example.darpartmente.Department.Model.SaleDetails;
import com.example.darpartmente.Department.Service.InterImp.SaleDetailsServiceImp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;

import com.example.darpartmente.Department.Service.inter.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

import java.util.Base64;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;



    @GetMapping("/addProduct")
    public String addProductForm(Model model) {
        return "add-product";
    }

    @PostMapping("/addProduct")
    public String saveProduct(@RequestParam("file") MultipartFile file,
                              @RequestParam("name") String name,
                              @RequestParam("description") String description,
                              @RequestParam("price") int price) {
        productService.saveProduct(file, name, description, price);
        return "redirect:/getAll";
    }


    @GetMapping("/getAll")
    public String getAllProducts(@RequestParam(defaultValue = "0") int page, Model model) {
        Page<Product> productPage = productService.getAllProducts(PageRequest.of(page, 10));
        model.addAttribute("products", productPage.getContent());
        model.addAttribute("pageNumber", page);
        model.addAttribute("totalPages", productPage.getTotalPages());
        return "product-list";
    }

    @GetMapping("/product/{id}")
    public String viewProductDetails(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "product-details";
    }

    @GetMapping("/editProduct/{id}")
    public String editProduct(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "edit-product";
    }

    @PostMapping("/updateProduct")
    public String updateProduct(@ModelAttribute Product product,
                                @RequestParam(value = "photoFile", required = false) MultipartFile photoFile) throws IOException {

        if (photoFile != null && !photoFile.isEmpty()) {
            product.setPhoto(Base64.getEncoder().encodeToString(photoFile.getBytes()));
        } else {
            Product existingProduct = productService.getProductById(product.getId());
            product.setPhoto(existingProduct.getPhoto());
        }

        productService.save(product);
        return "redirect:/product/" + product.getId();
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            productService.deleteProductById(id);
            redirectAttributes.addFlashAttribute("msg", "Product deleted successfully!");
        } catch (Exception e) {
            e.printStackTrace(); // Logs error in console
            redirectAttributes.addFlashAttribute("fail", "Unable to delete product. It may be in use.");
        }
        return "redirect:/getAll";
    }


    @GetMapping("/buyProduct/{id}")
    public String buyProduct(@PathVariable Long id, Model model) {
        // you can show a confirmation or directly proceed
        Product product = productService.getProductById(id);
        // Maybe reduce stock, create an order, etc.
        return "buy-confirmation"; // or handle logic in POST
    }





}

