package com.example.darpartmente.Department.Controller;

import com.example.darpartmente.Department.Model.DTO.PaymentSummaryDTO;
import com.example.darpartmente.Department.Model.Product;
import com.example.darpartmente.Department.Model.SaleDetails;
import com.example.darpartmente.Department.Service.InterImp.SaleDetailsServiceImp;
import com.example.darpartmente.Department.repositery.ProductRepository;
import com.example.darpartmente.Department.repositery.SaleDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SaleDetailsController {

    @Autowired
    private SaleDetailsServiceImp saleService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SaleDetailsServiceImp saleDetailsServiceImp;

    @Autowired
    private SaleDetailsRepository saleDetailsRepository;

    @GetMapping("/sale/buy/{id}")
    public String showBuyForm(@PathVariable Long id, Model model) {
        SaleDetails saleDetails = new SaleDetails();
        saleDetails.setProduct(productRepository.findById(id).orElseThrow());
        model.addAttribute("saleDetails", saleDetails);
        Product product = productRepository.findById(id).orElseThrow();
        saleDetails.setProduct(productRepository.findById(id).orElseThrow());
        saleDetails.setPrice(product.getPrice());
        model.addAttribute("states", List.of("Maharashtra", "Gujarat", "Karnataka", "Delhi", "Other"));
        model.addAttribute("items", productRepository.findAll());
        model.addAttribute("selectedItem", saleDetails.getProduct()); // ✅ Important for image
        return "buy-form";
    }


//    @PostMapping("/submitSale")
//    public String submitSale(@ModelAttribute SaleDetails saleDetails, Model model) {
//        try {
//            saleService.calculateAndSave(saleDetails);
//        } catch (IllegalArgumentException e) {
//            model.addAttribute("error", e.getMessage());
//            model.addAttribute("states", List.of("Maharashtra", "Gujarat", "Karnataka", "Delhi", "Other"));
//            return "buy-form";
//        }
//        return "redirect:/product/" + saleDetails.getProduct().getId();
//    }

    @PostMapping("/submitSale")
    public String submitSale(@ModelAttribute SaleDetails saleDetails, Model model) {
        // Don't save yet! Show confirmation first
        model.addAttribute("saleDetails", saleDetails);
        model.addAttribute("selectedItem", saleDetails.getProduct());
        model.addAttribute("states", List.of("Maharashtra", "Gujarat", "Karnataka", "Delhi", "Other"));
        model.addAttribute("items", productRepository.findAll());
        return "sale-confirmation";
    }


//    @Transactional
    @PostMapping("/confirmSale")
    public String confirmSale(@ModelAttribute SaleDetails saleDetails, Model model) {
        try {
            // 🔁 Refetch full product using the ID
            Long productId = saleDetails.getProduct().getId();
            saleDetails.setProduct(productRepository.findById(productId).orElseThrow());

            // ✅ Now proceed with calculation and saving
            System.out.println("Houn"+saleDetails);
            System.out.println("Product ID: " + saleDetails.getProduct().getId());

            saleService.calculateAndSave(saleDetails);

        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "buy-form";
        }

        return "redirect:/product/" + saleDetails.getProduct().getId();
    }

//    @PostMapping("/confirmSale")
//     public String confirmSale(@ModelAttribute("saleDetails") SaleDetails saleDetails,
//                          Model model) {
//    try {
//        SaleDetails savedSale = saleService.calculateAndSave(saleDetails);
//        return "redirect:/order-success/" + savedSale.getItem_id();
//    } catch (IllegalArgumentException e) {
//        model.addAttribute("error", e.getMessage());
//        model.addAttribute("states", List.of("Maharashtra", "Gujarat", "Karnataka", "Delhi", "Other"));
//        return "buy-form";
//    }
//     }






    @PostMapping("/cancelSale/{id}")
    public String cancelSale(@PathVariable Long id) {
        saleService.cancelSale(id);
        return "redirect:/getAll";
    }

    @GetMapping("/sale/search")
    public String searchSales(@RequestParam(value = "field", required = false) String field,
                              @RequestParam(value = "value", required = false) String value,
                              @RequestParam(defaultValue = "0") int page,
                              Model model) {

        Page<SaleDetails> sales = Page.empty();
        Pageable pageable = PageRequest.of(page, 20);

        if (field != null && value != null && !field.isBlank() && !value.isBlank()) {
            switch (field) {
                case "itemName" -> sales = saleDetailsServiceImp.searchSales(value, null, null, null, pageable);
                case "custName" -> sales = saleDetailsServiceImp.searchSales(null, value, null, null, pageable);
                case "mobileNo" -> sales = saleDetailsServiceImp.searchSales(null, null, value, null, pageable);
                case "amount" -> {
                    try {
                        Double amount = Double.parseDouble(value);
                        sales = saleDetailsServiceImp.searchSales(null, null, null, amount, pageable);
                    } catch (NumberFormatException e) {
                        // leave sales as empty
                    }
                }
            }
        }

        model.addAttribute("sales", sales.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", sales.getTotalPages());
        return "search-sales";
    }

    @GetMapping("/report/payments")
    public String showPaymentSummary(Model model) {
        List<PaymentSummaryDTO> summaries = saleDetailsServiceImp.getItemCustomerPaymentSummary();
        model.addAttribute("summaries", summaries);
        return "payment-summary";
    }

    @GetMapping("/report/top-customers")
    public String getTopCustomers(Model model) {
        model.addAttribute("top5ByPayment", saleDetailsServiceImp.getTop5ByPayment());
        model.addAttribute("top10ByCount", saleDetailsServiceImp.getTop10ByPurchases());
        return "top-customers"; // Thymeleaf view
    }












}
