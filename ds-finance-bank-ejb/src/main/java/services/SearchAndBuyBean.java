/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.xml.ws.BindingProvider;

import net.froihofer.dsfinance.ws.trading.PublicStockQuote;
import net.froihofer.dsfinance.ws.trading.TradingWebService;
import net.froihofer.dsfinance.ws.trading.TradingWebServiceService;
/**
 *
 * @author slasherer
 */
@Stateless

@LocalBean
public class SearchAndBuyBean{
    
    TradingWebServiceService tradingWebServiceService = new TradingWebServiceService();
    private TradingWebService servicePort;
    private static SearchAndBuyBean searchAndBuyBean;
    

    public void connect() {
        servicePort = tradingWebServiceService.getTradingWebServicePort();
        Map<String, Object> requestContext = ((BindingProvider) servicePort).getRequestContext();
        requestContext.put("javax.xml.ws.security.auth.username", "bic4b23_francesm");
        requestContext.put("javax.xml.ws.security.auth.password", "Shii1eis3");
    }
    public BigDecimal BuyStock(String name,int numOfShares) {
        connect();
        try {
            return servicePort.buy(name, numOfShares);

        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    
    public List<PublicStockQuote> SearchStockQuotes(String keyWord) {
        connect();
        try {
            List<PublicStockQuote> quotes = new ArrayList<>();
            quotes = servicePort.findStockQuotesByCompanyName(keyWord);
            return quotes;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    
}
