/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import net.froihofer.dsfinance.ws.trading.PublicStockQuote;

@Stateless
@Path("/stest")
public class TestSearchAndBuy {
    @EJB
    private SearchAndBuyBean searchAndBuyBean;
    
    public void setUp() throws Exception {
        Context context = new InitialContext();
        searchAndBuyBean = (SearchAndBuyBean) context.lookup("java:global/ds-finance-bank/ds-finance-bank-ejb/SearchAndBuyBean");
    }
    @GET
    @Path("/buy")
    @Produces(MediaType.TEXT_PLAIN)
    public String testBuyStock() {
        BigDecimal result = searchAndBuyBean.BuyStock("AAPL", 10);
        if (result == null) {
            return "testBuyStock failed: result is null";
        } else {
             return "testBuyStock passed " + result.toString();
        }
    }
    @GET
    @Path("/search")
    @Produces(MediaType.TEXT_PLAIN)
    public String testSearchStockQuotes() {
        List<PublicStockQuote> result = searchAndBuyBean.SearchStockQuotes("Microsoft");
        if (result == null || result.isEmpty()) {
           return "testSearchStockQuotes failed: result is null or empty";
        } else {
            return "testSearchStockQuotes passed";
        }
    }

    
}