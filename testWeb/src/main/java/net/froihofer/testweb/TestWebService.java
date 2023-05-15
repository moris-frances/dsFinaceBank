/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package net.froihofer.testweb;

import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.naming.InitialContext;
import javax.ws.rs.core.MediaType;
import net.froihofer.dsfinance.ws.trading.PublicStockQuote;
import services.SearchAndBuyBean;

/**
 * REST Web Service
 *
 * @author slasherer
 */
@Path("test")
@RequestScoped
public class TestWebService {

     @EJB
    private SearchAndBuyBean searchAndBuyBean;
    
    public void setUp() throws Exception {
        javax.naming.Context context = new InitialContext();
        searchAndBuyBean = (SearchAndBuyBean) context.lookup("java:global/ds-finance-bank/ds-finance-bank-ejb/SearchAndBuyBean");
    }
    @GET
    @Path("buy")
    @Produces(MediaType.TEXT_PLAIN)
    public String testBuyStock() {
        BigDecimal result = searchAndBuyBean.BuyStock("AAPL", 10);
        if (result == null) {
            return "testBuyStock failed: result is null";
        } else {
             return "testBuyStock passed";
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
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of TestWebService
     */
    public TestWebService() {
    }

    /**
     * Retrieves representation of an instance of testFul.TestWebService
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getText() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of TestWebService
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    public void putText(String content) {
    }
}
