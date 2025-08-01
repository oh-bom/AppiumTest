package tests;

import PageObjects.CartPage;

public class ProductsPageResultDto {
    private Float expectedPrice;
    private CartPage cartPage;

    public ProductsPageResultDto(Float expectedPrice, CartPage cartPage){
        this.expectedPrice=expectedPrice;
        this.cartPage=cartPage;
    }

    public Float getExpectedPrice(){
        return expectedPrice;
    }
    public CartPage getCartPage(){
        return cartPage;
    }
}
