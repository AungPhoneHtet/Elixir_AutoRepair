package com.elixir.workshop.controller;

import com.elixir.workshop.Messages;
import com.elixir.workshop.beans.BaseResponse;
import com.elixir.workshop.beans.Stock;
import com.elixir.workshop.constants.Constants;
import com.elixir.workshop.exceptions.CoreException;
import com.elixir.workshop.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class StockController extends MainController {

    private Messages messages;
    private StockService stockService;

    @Autowired
    public StockController(Messages messages, StockService stockService) {
        this.messages = messages;
        this.stockService = stockService;
    }

    @GetMapping("/stockEntry")
    public String stockEntryForm(Model model, HttpServletRequest request) {
        model.addAttribute("stock", new Stock());
        return super.replaceContentPage(model, request, Constants.ContentPages.STOCK);
    }

    @PostMapping("/saveStock")
    @ResponseBody
    public ResponseEntity<BaseResponse> saveStock(@RequestBody Stock stock) {
        try {
            stockService.save(stock);
        } catch (CoreException e) {
            return ResponseEntity.ok().body(
                    BaseResponse.builder()
                            .messageType(BaseResponse.MESSAGE_TYPE_ERROR)
                            .messageTitle(BaseResponse.MESSAGE_TITLE_FAIL)
                            .messageDesc(e.getMessage())
                            .build());
        }
        return ResponseEntity.ok().body(
                BaseResponse.builder()
                        .messageType(BaseResponse.MESSAGE_TYPE_SUCCESS)
                        .messageTitle(BaseResponse.MESSAGE_TITLE_SUCCESS)
                        .messageDesc(messages.get("save.success"))
                        .build());
    }

    @GetMapping("/existingStocks")
    public String existingStocks(Model model, HttpServletRequest request) {
        try {
            model.addAttribute("stocks", stockService.findAll());
        } catch (CoreException e) {
            e.printStackTrace();
            return super.replaceContentPage(model, request, Constants.ContentPages.EXISTING_STOCKS);
        }
        return super.replaceContentPage(model, request, Constants.ContentPages.EXISTING_STOCKS);
    }

    @GetMapping("/getStock")
    public String getStock(Model model, @RequestParam(name = "stockCode") String stockCode, HttpServletRequest request) {
        try {
            model.addAttribute("stock", stockService.findByStockCode(stockCode));
        } catch (CoreException e) {
            e.printStackTrace();
            return super.replaceContentPage(model, request, Constants.ContentPages.STOCK);
        }
        return super.replaceContentPage(model, request, Constants.ContentPages.STOCK);
    }
}
