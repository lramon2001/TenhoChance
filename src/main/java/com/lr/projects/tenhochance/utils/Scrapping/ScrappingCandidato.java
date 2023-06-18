package com.lr.projects.tenhochance.utils.Scrapping;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;


import lombok.Data;

@Data
public class ScrappingCandidato {

    private String nomeCandidato;

    private WebDriver driver;

    private ChromeOptions chromeOptions;

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

    public boolean consultaCandidato() {

        this.driver.get("https://security.cebraspe.org.br/ConsultaOnline/UNB_23_ACESSOENEM/1983/7d6638f3-928d-46f4-b7bd-9ace99ad2b70/Consulta");

        WebElement nomeConsultaInput = this.driver.findElement(By.id("NomeConsulta"));
        Actions actions = new Actions(this.driver);
        CharSequence charSequence = this.nomeCandidato;
        actions.click(nomeConsultaInput).sendKeys(charSequence).sendKeys(Keys.RETURN).perform();

        String html = this.driver.getPageSource();
        if (html.contains("<h2 style=\"text-align:center; margin-top:50px\">Nenhum candidato encontrado</h2>")) {
            return false;
        } else {
            return true;
        }
    }

    public ScrappingCandidato (String nomeCandidato) {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        this.nomeCandidato = nomeCandidato;
        this.driver = new ChromeDriver(this.setChromeOptions());
        
    }
}
