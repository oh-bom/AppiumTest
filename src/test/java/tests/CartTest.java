package tests;

import PageObjects.CartPage;
import PageObjects.LandingPage;
import PageObjects.ProductsPage;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class CartTest extends BaseTest {

    @Test
    public void VerifyAddProductToCart(){
        List<String>testItems=GlobalDataReader.getList("cartItems");
        String country=GlobalDataReader.get("country");
        String name=GlobalDataReader.get("name");
        String gender=GlobalDataReader.get("gender");

        ProductsPage productsPage=gotoProductPage(landingPage,country, name,gender);
        ProductsPageResultDto productsPageResult=addToCartProducts(productsPage,testItems);

        Float expectedPrice=productsPageResult.getExpectedPrice();
        CartPage cartPage=productsPageResult.getCartPage();

        Assert.assertEquals(cartPage.verifyCartItems(testItems), true,"장바구니에 모든 아이템이 올바르게 들어가지 않았어요.");
        Assert.assertEquals(cartPage.verifyTotalPrice(expectedPrice), true,"장바구니 총 가격이 일치하지 않아요.");

    }
    public ProductsPage gotoProductPage(LandingPage landingPage,String country, String name, String gender){
        landingPage.selectCountry(country);
        landingPage.enterName(name);
        landingPage.selectGender(gender);
        ProductsPage productsPage=landingPage.gotoShopPage();

        return productsPage;
    }

    public ProductsPageResultDto addToCartProducts(ProductsPage productsPage, List<String> testProducts){
        Float expectedPrice=productsPage.addToCart(testProducts);
        CartPage cartPage=productsPage.gotoCartPage();

        return new ProductsPageResultDto(expectedPrice,cartPage);
    }

}
