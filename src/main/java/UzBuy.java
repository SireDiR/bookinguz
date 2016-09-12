/**
 * Created by tku on 9/12/2016.
 */

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class UzBuy {
    public static void main(String[] args){
        Configuration.browser = "chrome";
        String email = "kubai@meta.ua";
        Integer count = 3;
        String password = "";
        String from = "Киев";
        String to = "Тернополь";
        String date = "16.09.2016";
        String train = "112 Л";
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
        $("#date_dep").setValue(date);//todo var
        $("[name='station_from']").click();//todo focus out
        $("#ui-datepicker-div").shouldBe(disappear);
        $("[name='search']").click();
        $("ts_res_not_found").shouldNotBe(visible);
        SelenideElement trainInTable = $("//table[@id='ts_res_tbl']/tbody//a[contains(.,'" + train + "')]");
        trainInTable.shouldNotBe(visible);
        trainInTable.parent().parent().parent().find("td.place>div[title]:nth-last-child(1)>button").click();
        //
//        $("div.vToolsPopup>center.vToolsPopupToolbar>button").click();
        //
        $("div.vToolsPopup.coachScheme").shouldBe(visible);
        $("div>a.free").click();
        $("#ts_chs_tbl td.cancel").shouldBe(attribute("opacity", "1"));
        for (SelenideElement chb : $$("#ts_chs_tbl td.service>div>label>input")){
            if (chb.isSelected()){
                chb.parent().click();
            }
        }
        $("#ts_chs_tbl button.complex_btn").click();
        $("div.vToolsPopup.coachScheme").shouldBe(disappears);
        System.out.println("dfgdfgf");


    }
}
