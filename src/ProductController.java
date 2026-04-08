package com.example.shopping.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.shopping.model.Product;
import com.example.shopping.repository.ProductRepository;

/**
 * 商品一覧・詳細表示を担当するコントローラー
 */
@Controller
public class ProductController {

    private final ProductRepository productRepo;

    public ProductController(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    /**
     * トップページを表示する
     *
     * @param model モデルオブジェクト
     * @return index.html
     */
    @GetMapping("/")
    public String index(Model model) {
        List<Product> products = productRepo.findAll();
        model.addAttribute("products", products);
        return "index";
    }

    /**
     * 商品一覧ページを表示する
     *
     * @param model モデルオブジェクト
     * @return products.html
     */
    @GetMapping({"/products", "/product"})
    public String products(Model model) {
        List<Product> products = productRepo.findAll();
        model.addAttribute("products", products);
        return "products";
    }

    /**
     * 商品詳細ページを表示する
     *
     * @param id 商品ID
     * @param model モデルオブジェクト
     * @return product-detail.html または一覧ページへリダイレクト
     */
    @GetMapping("/product/{id}")
    public String productDetail(@PathVariable("id") Long id, Model model) {
        Product product = productRepo.findById(id).orElse(null);
        if (product == null) return "redirect:/products";
        model.addAttribute("product", product);
        return "product-detail";
    }
}