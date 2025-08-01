package PageObjects;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

public class ProductsPage extends abstractComponents {
    AndroidDriver driver;

    private final By productContainerBy = AppiumBy.id("com.androidsample.generalstore:id/rvProductList");
    private final By productsCardsBy = AppiumBy.xpath("//android.support.v7.widget.RecyclerView[@resource-id='com.androidsample.generalstore:id/rvProductList']/android.widget.RelativeLayout");
    private final By addToCartButtonBy = AppiumBy.xpath(".//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productAddCart']");
    private final By productPriceBy = AppiumBy.xpath(".//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productPrice']");
    private final By productNameBy = AppiumBy.xpath(".//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productName']");
    private final By gotoCartButtonBy = AppiumBy.id(("com.androidsample.generalstore:id/appbar_btn_cart"));

    public ProductsPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
    }


    public Float addToCart(List<String> targetProducts) {
        List<String> foundProducts = new ArrayList<>();
        Float priceSum = 0f;

        waitForByToAppear(productContainerBy);

        while(foundProducts.size()<targetProducts.size()) {
            List<WebElement> productCards = driver.findElements(productsCardsBy);
            for (WebElement productCard : productCards) {
                try{
                    String productName = productCard.findElement(productNameBy).getText().trim();

                    if (targetProducts.contains(productName) && !foundProducts.contains(productName)) {
                        String priceText = productCard.findElement(productPriceBy).getText();
                        Float price = Float.parseFloat(priceText.replace("$", "").trim());
                        productCard.findElement(addToCartButtonBy).click();

                        foundProducts.add(productName);
                        priceSum += price;
                        System.out.println("추가됨: " + productName + " $" + price);
                    }

                } catch (Exception e) {
//                    System.out.println("다음 제품 카드의 요소가 보이지 않음: " + e.getMessage());
                    continue; // 다음 제품 카드로 넘어감
                }

                if (foundProducts.size() == targetProducts.size()) {
                    break;
                }
            }

            scrollDown();
        }
        return priceSum;
    }

    public CartPage gotoCartPage(){
        driver.findElement(gotoCartButtonBy).click();
        return new CartPage(driver);
    }


}

