package com.example.shopping.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.shopping.dto.CartItem;
import com.example.shopping.model.Order;
import com.example.shopping.model.OrderItem;
import com.example.shopping.service.OrderService;
import com.example.shopping.service.ProductService;

/**
 * カート関連の操作を担当するコントローラー
 * カート追加、表示、更新、チェックアウトなどを扱う
 */
@Controller
@RequestMapping("/cart")
public class CartController {

    private final ProductService productService;
    private final OrderService orderService;

    public CartController(ProductService productService, OrderService orderService) {
        this.productService = productService;
        this.orderService = orderService;
    }

    /**
     * セッションからカートを取得する
     * カートが存在しない場合は新規作成する
     *
     * @param session HTTPセッション
     * @return カートのリスト
     */
    @SuppressWarnings("unchecked")
    private List<CartItem> getCart(HttpSession session) {
        var cart = (List<CartItem>) session.getAttribute("CART");
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("CART", cart);
        }
        return cart;
    }

    /**
     * 商品をカートに追加する（Ajax用）
     *
     * @param productId 商品ID
     * @param qty 数量（デフォルト1）
     * @param session HTTPセッション
     * @return カート内の合計数量
     */
    @PostMapping("/add")
    @ResponseBody
    public int addToCartAjax(@RequestParam Long productId,
                             @RequestParam(defaultValue = "1") int qty,
                             HttpSession session) {

        var prodOpt = productService.findById(productId);
        if (prodOpt.isEmpty()) return 0;

        var cart = getCart(session);
        boolean found = false;

        for (CartItem item : cart) {
            if (item.getProductId().equals(productId)) {
                item.setQuantity(item.getQuantity() + qty);
                found = true;
                break;
            }
        }

        if (!found) {
            cart.add(new CartItem(prodOpt.get(), qty));
        }

        return cart.stream()
                .mapToInt(CartItem::getQuantity)
                .sum();
    }

    /**
     * カート内容を表示する
     *
     * @param model モデルオブジェクト
     * @param session HTTPセッション
     * @return cart.html
     */
    @GetMapping("/view")
    public String viewCart(Model model, HttpSession session) {
        var cart = getCart(session);
        double total = cart.stream().mapToDouble(CartItem::getSubtotal).sum();
        model.addAttribute("cart", cart);
        model.addAttribute("total", total);
        return "cart";
    }

    /**
     * カート内の商品数量を更新する
     *
     * @param productId 商品ID
     * @param qty 数量
     * @param session HTTPセッション
     * @return カートページへリダイレクト
     */
    @PostMapping("/update")
    public String updateCart(@RequestParam Long productId, @RequestParam int qty, HttpSession session) {
        var cart = getCart(session);
        cart.removeIf(i -> {
            if (i.getProductId().equals(productId)) {
                if (qty <= 0) return true;
                i.setQuantity(qty);
            }
            return false;
        });
        return "redirect:/cart/view";
    }

    /**
     * カート内の商品をチェックアウトして注文を確定する
     *
     * @param principal 認証情報
     * @param session HTTPセッション
     * @param model モデルオブジェクト
     * @return 注文成功ページまたはリダイレクト
     */
    @PostMapping("/checkout")
    public String checkout(Principal principal, HttpSession session, Model model) {
        if (principal == null) return "redirect:/login";

        var cart = getCart(session);
        if (cart.isEmpty()) return "redirect:/";

        Order order = new Order();
        List<OrderItem> items = new ArrayList<>();
        double total = 0;

        for (var c : cart) {
            OrderItem oi = new OrderItem(c.getProductId(), c.getName(), c.getPrice(), c.getQuantity());
            items.add(oi);
            total += c.getSubtotal();
        }

        order.setItems(items);
        order.setTotal(total);
        orderService.save(order);

        session.removeAttribute("CART");

        model.addAttribute("order", order);
        return "order-success";
    }
}