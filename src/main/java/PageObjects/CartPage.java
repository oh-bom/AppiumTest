package PageObjects;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends abstractComponents{
    AndroidDriver driver;

    private final By cartItemsBy = AppiumBy.id("com.androidsample.generalstore:id/productName");

    public CartPage(AndroidDriver driver){
        super(driver);
        this.driver=driver;
    }

    public boolean verifyCartItems(List<String> expectedItems){
        waitForByToAppear(cartItemsBy);
        List<WebElement> cartItems = driver.findElements(cartItemsBy);

        boolean allItemsPresent = true;
        for(String expectedItem:expectedItems){
            boolean isItemFound=false;
            for(WebElement cartItem:cartItems){
                if(cartItem.getText().trim().equalsIgnoreCase(expectedItem)){
                    isItemFound=true;
                    break;
                }
            }

            if(!isItemFound){
                allItemsPresent=false;
                System.out.println(expectedItem+" 아이템이 장바구니에 없습니다.");
            }
        }

        return allItemsPresent;
    }

    public boolean verifyTotalPrice(Float expectedPrice){
        Float cartTotalPrice=Float.parseFloat(driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/totalAmountLbl")).getText().replace("$", "").trim());

        if(cartTotalPrice.equals(expectedPrice)) {
            System.out.println("장바구니 총 가격이 일치합니다");
            return true;
        }
        else{
            System.out.println("장바구니 총 가격이 일치하지 않습니다. 예상: " + expectedPrice + ", 실제: " + cartTotalPrice);
            return false;
        }

    }

}
