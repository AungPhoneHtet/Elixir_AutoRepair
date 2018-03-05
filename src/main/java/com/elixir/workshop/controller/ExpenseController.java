package com.elixir.workshop.controller;

import com.elixir.workshop.Messages;
import com.elixir.workshop.beans.BaseResponse;
import com.elixir.workshop.beans.Expense;
import com.elixir.workshop.constants.Constants;
import com.elixir.workshop.exceptions.CoreException;
import com.elixir.workshop.service.ExpenseService;
import com.elixir.workshop.service.ExpenseTypeService;
import com.elixir.workshop.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
public class ExpenseController extends MainController {

    @Autowired
    ExpenseService expenseService;

    @Autowired
    ExpenseTypeService expenseTypeService;

    @Autowired
    Messages messages;

    @GetMapping("/expense")
    public String getExpense(Model model, HttpServletRequest request) {
        try {
            model.addAttribute("expenses", expenseService.findByDate(DateUtils.getCurrentDate()));
            model.addAttribute("expenseTypes", expenseTypeService.findAll());
        } catch (CoreException e) {
            return super.replaceContentPage(model, request, Constants.ContentPages.EXPENSE);
        }
        return super.replaceContentPage(model, request, Constants.ContentPages.EXPENSE);
    }

    @PostMapping("/saveExpense")
    @ResponseBody
    public ResponseEntity<BaseResponse> saveExpense(@RequestBody Expense expense) {
        try {
            expense = expenseService.save(expense);
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
                        .messageDesc(String.valueOf(expense.getId()))
                        .build());
    }

    @PostMapping("/deleteExpense")
    @ResponseBody
    public ResponseEntity<BaseResponse> deleteExpense(@RequestBody long id) {
        try {
            expenseService.delete(id);
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
                        .messageDesc(messages.get("delete.success"))
                        .build());
    }

    @PostMapping("/paidExpense")
    @ResponseBody
    public ResponseEntity<BaseResponse> paidExpense(@RequestBody long id) {
        try {
            expenseService.paid(id);
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
                        .messageDesc(messages.get("paid.success"))
                        .build());
    }

    @GetMapping(value = "/getExpenses", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<Expense>> getExpenses(@RequestParam String date) {
        try {
            return ResponseEntity.ok().body(expenseService.findByDate(DateUtils.changeUIDateToSQLDate(date)));
        } catch (CoreException e) {
            return ResponseEntity.ok().body(null);
        }
    }
}
