package com.lr.projects.tenhochance.utils.Scrapping;

import java.util.List;

import javax.annotation.PostConstruct;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.lr.projects.tenhochance.entity.Candidato;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Component
@NoArgsConstructor
@Getter
@Setter
public class ScrappingCandidato {

    private WebDriver webDriver;

    private ChromeOptions chromeOptions;

    @Value("${url.cespe}")
    private String urlCespe;

    @PostConstruct
    public void init() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        this.webDriver = new ChromeDriver(this.setChromeOptions());
    }

    public ChromeOptions setChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("--disable-infobars");
        chromeOptions.addArguments("--disable-popup-blocking");
        chromeOptions.addArguments("--remote-allow-origins=*");
        return chromeOptions;
    }

    public boolean consultaCandidato(String nomeCandidato, String numeroInscricao) {

        this.webDriver.get(urlCespe);
    
        WebElement nomeConsulta = this.webDriver.findElement(By.id("NomeConsulta"));
        Actions actions = new Actions(this.webDriver);
        actions.click(nomeConsulta).sendKeys(nomeCandidato).sendKeys(Keys.RETURN).perform();
    
        String html = this.webDriver.getPageSource();
        if (html.contains("<h2 style=\"text-align:center; margin-top:50px\">Nenhum candidato encontrado</h2>")) {
            return false;
        } else {
            List<WebElement> rows = this.webDriver.findElements(By.xpath("//table[@class='table table-vcenter card-table table-striped table-bordered']//tbody/tr"));
            for (WebElement row : rows) {
                List<WebElement> cols = row.findElements(By.tagName("td"));
                if (cols.size() > 0) {
                    String foundNomeCandidato = cols.get(1).getText().trim();
                    String foundNumeroInscricao = cols.get(0).getText().trim();
                    if (foundNomeCandidato.equals(nomeCandidato) && foundNumeroInscricao.equals(numeroInscricao)) {
                        return true;
                    }
                }
            }
        }
    
        return false;
    }
    

}
