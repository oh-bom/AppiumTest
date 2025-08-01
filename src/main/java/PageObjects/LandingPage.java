package PageObjects;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class LandingPage {
    AndroidDriver driver;

    public LandingPage(AndroidDriver driver) {
        this.driver = driver;
    }

    private final By countryDropdownBy = AppiumBy.xpath("//android.widget.TextView[@resource-id='android:id/text1']");
    private final By nameFieldBy = AppiumBy.id("com.androidsample.generalstore:id/nameField");
    private final By genderMaledBy= AppiumBy.xpath("//*[@text='Male']");
    private final By genderFemaleBy = AppiumBy.xpath("//*[@text='Female']");
    private final By gotoShopButtonBy = AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop");

    public void selectCountry(String country){
        driver.findElement(countryDropdownBy).click();
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector()).scrollIntoView(text(\""+country+"\"))")).click();
    }

    public void enterName(String name){
        driver.findElement(nameFieldBy).sendKeys(name);
        driver.hideKeyboard();
    }

    public void selectGender(String gender){
        if(gender.equalsIgnoreCase("male")) driver.findElement(genderMaledBy).click();
        else driver.findElement(genderFemaleBy).click();
    }

    public ProductsPage gotoShopPage(){
        driver.findElement(gotoShopButtonBy).click();
        return new ProductsPage(driver);
    }
}
