/**
 * Created by tku on 9/12/2016.
 */

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.Map;
import java.util.Properties;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class UzBuy {
    public static void main(String[] args){
        Map<String, String> prop = System.getenv();
        Configuration.browser = "chrome";
        Configuration.chromeSwitches = "--use-mobile-user-agent --user-agent=Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.76 Mobile Safari/537.36";
        Configuration.browserSize = "400x900";
        String email = prop.getOrDefault("email", "kubai@meta.ua");
        Integer count = Integer.parseInt(prop.getOrDefault("count", "3"));
        String password = prop.getOrDefault("password", "123");
        String from = prop.getOrDefault("from", "Киев");
        String to = prop.getOrDefault("to", "Ивано-Франковск");
        String date = prop.getOrDefault("date", "11.11.2017");
        String train = prop.getOrDefault("train", "043 Л");
        String card = prop.getOrDefault("card", "1234567890123456");
        String month = prop.getOrDefault("month", "11");
        String year = prop.getOrDefault("year", "11");
        String cvv = prop.getOrDefault("cvv", "123");
        System.out.println(password);
        System.out.println(from);
        System.out.println(to);
        System.out.println(count);
        System.out.println(date);
        System.out.println(train);
        System.out.println(card);
        System.out.println(month);
        System.out.println(year);
        System.out.println(cvv);
        open("http://booking.uz.gov.ua/ru/authorization/");
        $("[name='login']").setValue(email);
        $("[name='passwd']").setValue(password);
        $("button[type='submit']").click();
        $("button[type='submit']").click();
        $$("tr.vToolsDataTableRow2").shouldBe(sizeNotEqual(count));
        $("#content>ul.menu>li:nth-child(1)").click();
        $("[name='station_from']").setValue(from);
        $("#station_from").shouldBe(visible);
        $("#stations_from>div").click();
        $("[name='station_till']").setValue(to);
        $("#stations_till").shouldBe(visible);
        $("#stations_till>div").click();
        $("#date_dep").setValue(date);
        $("[name='time_dep']").parent().click();//todo focus out
        $("#ui-datepicker-div").shouldBe(disappear);
        $("[name='search']").click();
        $("ts_res_not_found").shouldNotBe(visible);
        SelenideElement trainInTable = $(By.xpath("//table[@id='ts_res_tbl']/tbody//a[contains(.,'" + train + "')]"));
        trainInTable.shouldNotBe(visible);
        trainInTable.parent().parent().find("td.place>div[title]:nth-last-child(1)>button").click();
        //
//        $("div.vToolsPopup>center.vToolsPopupToolbar>button").click();
        //
        $("div.vToolsPopup.coachScheme").shouldBe(visible);
        $("div>a.free").click();
        $("#ts_chs_tbl td.cancel").should(appear);
        for (SelenideElement chb : $$("#ts_chs_tbl td.service>div>label>input")){
            if (chb.isSelected()){
                chb.parent().click();
            }
        }
        $("#ts_chs_tbl button.complex_btn").click();
        $("div.vToolsPopup.coachScheme").shouldBe(disappears);
        $("button.complex_btn").click();
        //
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if ($("div.vToolsPopup").exists()){
            $("center.vToolsPopupToolbar>button").click();
            $("div.vToolsPopup").should(disappear);
        }
        $("input.cnum").setValue(card);
        $("[name='expire_month']").setValue(month);
        $("[name='expire_year']").setValue(year);
        $("#input-cvv").setValue(cvv);
//        $("#service_confirm_payment_button").click();
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
