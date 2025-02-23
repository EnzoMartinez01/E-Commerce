package com.empresa.empresa.Controllers.Cart;

import com.empresa.empresa.Dto.Cart.CartDto;
import com.empresa.empresa.Services.Cart.CartService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // Get all Carts
    @GetMapping("/getAllCart")
    public ResponseEntity<Page<CartDto>> getAllCarts(@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size) {
        Page<CartDto> carts = cartService.getAllCart(page, size);
        return ResponseEntity.ok(carts);
    }

    // Get Cart by User
    @GetMapping("/getCartByUser/{idUser}")
    public ResponseEntity<Page<CartDto>> getCartByUser(@PathVariable Integer idUser,
                                                       @RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int size) {
        Page<CartDto> carts = cartService.getCartByUser(page, size, idUser);
        return ResponseEntity.ok(carts);
    }

    //Add Product to Cart
    @PostMapping("/addProductToCart")
    public ResponseEntity<Map<String, String>> addProductToCart(@RequestParam Integer idProduct,
                                                                @RequestParam Integer quantity,
                                                                @RequestParam Integer paymentMethod) {
        cartService.addProductToCart(idProduct, quantity, paymentMethod);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Producto añadido al carrito satisfactoriamente.");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //Delete Product to Cart
    @DeleteMapping("/deleteProductToCart")
    public ResponseEntity<Map<String, String>> deleteProductToCart(@RequestParam Integer idProduct) {
        cartService.deleteProductToCart(idProduct);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Producto eliminado del carrito satisfactoriamente.");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


}
