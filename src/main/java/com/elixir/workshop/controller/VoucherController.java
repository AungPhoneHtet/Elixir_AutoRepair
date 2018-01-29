package com.elixir.workshop.controller;


import com.elixir.workshop.Messages;
import com.elixir.workshop.beans.BaseResponse;
import com.elixir.workshop.beans.Voucher;
import com.elixir.workshop.constants.Constants;
import com.elixir.workshop.exceptions.CoreException;
import com.elixir.workshop.service.VoucherService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class VoucherController extends MainController {

    private Messages messages;
    private VoucherService voucherService;

    @Autowired
    public VoucherController(Messages messages, VoucherService voucherService) {
        this.messages = messages;
        this.voucherService = voucherService;
    }

    @GetMapping("/newVoucher")
    public String getNewVoucherForm(Model model, HttpServletRequest request) {
        model.addAttribute("voucher", new Voucher());
        return super.replaceContentPage(model, request, Constants.ContentPages.NEW_VOUCHER);
    }

    @GetMapping("/getVoucher")
    public String getVoucherForm(Model model, @RequestParam(name = "voucherNo") String voucherNo, HttpServletRequest request) {
        try {
            model.addAttribute("voucher", voucherService.findByVoucherNo(voucherNo));
        } catch (CoreException e) {
            e.printStackTrace();
            return super.replaceContentPage(model, request, Constants.ContentPages.NEW_VOUCHER);
        }
        return super.replaceContentPage(model, request, Constants.ContentPages.NEW_VOUCHER);
    }

    @GetMapping("/existingVouchers")
    public String existingVouchers(Model model, HttpServletRequest request) {
        try {
            model.addAttribute("vouchers", voucherService.findAll());
        } catch (CoreException e) {
            e.printStackTrace();
            return super.replaceContentPage(model, request, Constants.ContentPages.EXISTING_VOUCHERS);
        }
        return super.replaceContentPage(model, request, Constants.ContentPages.EXISTING_VOUCHERS);
    }


    @PostMapping("/saveVoucher")
    @ResponseBody
    public ResponseEntity<BaseResponse> saveVoucher(@RequestBody Voucher voucher) {
        try {
            voucher.setStatus(Constants.Status.SAVE);
            if (StringUtils.isBlank(voucher.getVoucherNo())) {
                voucherService.save(voucher);
            } else {
                voucherService.update(voucher);
            }
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
                        .data(new String[]{voucher.getVoucherNo()}).build());
    }

    @PostMapping("/paidVoucher")
    @ResponseBody
    public ResponseEntity<BaseResponse> paidVoucher(@RequestBody String voucherNo) {
        try {
            voucherService.paidVoucher(voucherNo);
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

}
