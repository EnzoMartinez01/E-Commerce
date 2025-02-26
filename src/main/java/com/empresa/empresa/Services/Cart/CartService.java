package com.empresa.empresa.Services.Cart;

import com.empresa.empresa.Dto.Cart.CartDto;
import com.empresa.empresa.Dto.Cart.CartItemsDto;
import com.empresa.empresa.Dto.Product.AttributesDto;
import com.empresa.empresa.Dto.Product.CharacteristicsDto;
import com.empresa.empresa.Dto.Product.ProductsDto;
import com.empresa.empresa.Models.Authentication.CustomUserDetails;
import com.empresa.empresa.Models.Authentication.Users;
import com.empresa.empresa.Models.Cart.Cart;
import com.empresa.empresa.Models.Cart.CartItems;
import com.empresa.empresa.Models.Cart.PaymentMethod;
import com.empresa.empresa.Models.Cart.TypeShipment;
import com.empresa.empresa.Models.Products.Attributes;
import com.empresa.empresa.Models.Products.Characteristics;
import com.empresa.empresa.Models.Products.Products;
import com.empresa.empresa.Repositories.Authentication.UsersRepository;
import com.empresa.empresa.Repositories.Cart.CartItemRepository;
import com.empresa.empresa.Repositories.Cart.CartRepository;
import com.empresa.empresa.Repositories.Cart.PaymentMethodRepository;
import com.empresa.empresa.Repositories.Products.ProductsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {
    private final static Logger logger = LoggerFactory.getLogger(CartService.class);

    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final UsersRepository usersRepository;
    private final ProductsRepository productsRepository;
    private final PaymentMethodRepository paymentMethodRepository;

    public CartService(CartItemRepository cartItemRepository,
                       CartRepository cartRepository,
                       UsersRepository usersRepository,
                       ProductsRepository productsRepository,
                       PaymentMethodRepository paymentMethodRepository) {
        this.cartItemRepository = cartItemRepository;
        this.cartRepository = cartRepository;
        this.usersRepository = usersRepository;
        this.productsRepository = productsRepository;
        this.paymentMethodRepository = paymentMethodRepository;
    }

    // Get all Carts Items
    public Page<CartDto> getAllCart(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return cartRepository.findAll(pageable).map(this::mapToDto);
    }

    // Get Carts Items by User
    public Page<CartDto> getCartByUser(int page, int size, Integer idUser) {
        try {
            Users users = usersRepository.findById(idUser)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            Pageable pageable = PageRequest.of(page, size);
            return cartRepository.findByUsers(pageable, users).map(this::mapToDto);
        } catch (Exception e) {
            logger.error("Error al obtener los productos", e);
            throw new RuntimeException("Error al obtener los productos");
        }

    }

    // Map to Dto
    public CartDto mapToDto(Cart cart) {
        CartDto dto = new CartDto();
        dto.setIdCart(cart.getId());
        dto.setIdUser(cart.getUsers().getId());
        dto.setFullName(cart.getUsers().getFullname());
        dto.setTypeShipment(cart.getTypeShipment().name());
        dto.setIdPaymentMethod(cart.getPaymentMethod().getId());
        dto.setPaymentMethod(cart.getPaymentMethod().getName());
        dto.setCartItems(cart.getCartItems().stream().map(this::mapToCartItemsDto).toList());
        dto.setTotal(cart.getTotal());
        return dto;
    }

    //Map to Cart Items Dto
    public CartItemsDto mapToCartItemsDto(CartItems cartItems) {
        CartItemsDto dto = new CartItemsDto();
        dto.setIdCartItem(cartItems.getId());
        dto.setProduct(mapProductsToDto(cartItems.getProduct()));
        dto.setQuantity(cartItems.getQuantity());
        dto.setSubTotal(cartItems.getSubTotal());
        return dto;
    }

    //Map to Product Dto
    public ProductsDto mapProductsToDto (Products products) {
        ProductsDto dto = new ProductsDto();
        dto.setIdProduct(products.getId());
        dto.setProductName(products.getProductName());
        dto.setProductDescription(products.getProductDescription());
        dto.setProductSku(products.getSku());
        dto.setProductPrice(products.getPrice());
        dto.setPriceCreditCard(products.getPrice_creditcard());
        dto.setQuantity(products.getQuantity());
        dto.setStock(products.getStock());
        dto.setProductOfferDiscount(products.getOfferDescount());
        dto.setPriceOffer(products.getPriceOffer());
        dto.setIdBrand(products.getBrand().getIdBrand());
        dto.setBrandName(products.getBrand().getBrandName());
        dto.setIdCategory(products.getCategory().getIdCategory());
        dto.setCategoryName(products.getCategory().getCategoryName());
        dto.setPdfFile(products.getPdfFile());
        dto.setIsOffer(products.getIsOffer());
        dto.setIsActive(products.getIsActive());
        dto.setProductImg(products.getProduct_image());
        dto.setAttributes(products.getAttributes().stream().map(this::mapToAttributeDto).toList());
        dto.setCharacteristics(products.getCharacteristics().stream().map(this::mapToCharacteristicDto).toList());
        return dto;
    }

    // Map to Attribute Dto
    public AttributesDto mapToAttributeDto(Attributes attributes) {
        AttributesDto dto = new AttributesDto();
        dto.setIdAttribute(attributes.getId());
        dto.setAttributeName(attributes.getAttributeName());
        dto.setIdSubCategory(attributes.getSubCategories().getIdSubCategory());
        dto.setSubCategoryName(attributes.getSubCategories().getSubCategoryName());
        return dto;
    }

    // Map to Characteristic Dto
    public CharacteristicsDto mapToCharacteristicDto(Characteristics characteristics) {
        CharacteristicsDto dto = new CharacteristicsDto();
        dto.setIdCharacteristic(characteristics.getId());
        dto.setName(characteristics.getName());
        dto.setDescription(characteristics.getDescription());
        return dto;
    }

    // Add Items to Cart
    public Cart addProductToCart(Integer idProduct, Integer quantity, Integer paymentMethod) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            Users currentUser = customUserDetails.getUsers();

            Cart cart = cartRepository.findByUsers(currentUser)
                    .orElse(new Cart());

            PaymentMethod payment = paymentMethodRepository.findById(paymentMethod)
                    .orElseThrow(() -> new RuntimeException("Payment Method not found with ID: " + paymentMethod));

            Products products = productsRepository.findById(idProduct)
                    .orElseThrow(() -> new RuntimeException("Product not found with ID: " + idProduct));

            Optional<CartItems> existingItem = cart.getCartItems().stream()
                    .filter(cartItems -> cartItems.getProduct().getId().equals(idProduct))
                    .findFirst();

            if (existingItem.isPresent()) {
                CartItems cartItems = existingItem.get();
                cartItems.setQuantity(cartItems.getQuantity() + quantity);
                if (products.getIsOffer() != null && products.getIsOffer() && products.getOfferDescount() != null) {
                    cartItems.setSubTotal(cartItems.getQuantity() * products.getPriceOffer());
                } else {
                    cartItems.setSubTotal(cartItems.getQuantity() * products.getPrice());
                }
            } else {
                CartItems newItem = new CartItems();
                newItem.setProduct(products);
                newItem.setQuantity(quantity);
                if (products.getIsOffer() != null && products.getIsOffer() && products.getOfferDescount() != null) {
                    newItem.setSubTotal(newItem.getQuantity() * products.getPriceOffer());
                } else if (cart.getPaymentMethod().getId().equals(3)) {
                    newItem.setSubTotal(newItem.getQuantity() * products.getPrice_creditcard());
                } else {
                    newItem.setSubTotal(newItem.getQuantity() * products.getPrice());
                }
                newItem.setCart(cart);
                cart.getCartItems().add(newItem);
            }

            Double total = cart.getCartItems().stream().mapToDouble(CartItems::getSubTotal).sum();
            cart.setUsers(currentUser);
            cart.setTotal(total);
            cart.setPaymentMethod(payment);
            cart.setTypeShipment(TypeShipment.PICKUP);

            return cartRepository.save(cart);
        } catch (Exception e) {
            logger.error("Error al añadir producto al carrito", e);
            throw new RuntimeException("Error al añadir producto al carrito");
        }
    }

    // Update CartItems Quantity
    public CartItems updateCartItemQuantity(Integer idCartItem, Integer quantity) {
        CartItems existingCartItem = cartItemRepository.findById(idCartItem)
                .orElseThrow(() -> new RuntimeException("CartItem not found with ID: " + idCartItem));

        Integer stockDisponible = existingCartItem.getProduct().getStock();

        if (quantity > stockDisponible) {
            throw new RuntimeException("No hay suficiente stock disponible. Máximo permitido: " + stockDisponible);
        }

        if (quantity > 0) {
            existingCartItem.setQuantity(quantity);
            existingCartItem.setSubTotal(quantity * existingCartItem.getProduct().getPrice());
        } else {
            cartItemRepository.delete(existingCartItem);
            return null;
        }

        return cartItemRepository.save(existingCartItem);
    }

    //Delete Producto fromt Cart
    public void deleteProductToCart(Integer idProduct) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            Users currentUser = customUserDetails.getUsers();

            Cart cart = cartRepository.findByUsers(currentUser)
                    .orElseThrow(() -> new RuntimeException("Cart not found for the current user"));

            Optional<CartItems> existingItem = cart.getCartItems().stream()
                    .filter(cartItem -> cartItem.getProduct().getId().equals(idProduct))
                    .findFirst();

            if (existingItem.isPresent()) {
                CartItems cartItem = existingItem.get();
                cart.getCartItems().remove(cartItem);
                cartItemRepository.delete(cartItem);

                Double total = cart.getCartItems().stream().mapToDouble(CartItems::getSubTotal).sum();
                cart.setTotal(total);
                cartRepository.save(cart);
            } else {
                throw new RuntimeException("Product not found in the cart");
            }
        } catch (Exception e) {
            logger.error("Error al eliminar producto del carrito", e);
            throw new RuntimeException("Error al eliminar producto del carrito");
        }
    }
}