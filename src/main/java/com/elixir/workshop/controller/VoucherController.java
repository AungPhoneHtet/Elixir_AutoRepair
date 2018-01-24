package com.elixir.workshop.controller;


import com.elixir.workshop.Messages;
import com.elixir.workshop.beans.BaseResponse;
import com.elixir.workshop.beans.Voucher;
import com.elixir.workshop.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class VoucherController extends MainController {

    @Autowired
    Messages messages;

    @GetMapping("/newVoucher")
    public String getNewVoucherForm(Model model, HttpServletRequest request) {
        return super.replaceContentPage(model, request, Constants.ContentPages.NEW_VOUCHER);
    }

    @PostMapping("/saveVoucher")
    @ResponseBody
    public ResponseEntity<BaseResponse> saveVoucher(@RequestBody Voucher voucher) {
        voucher.setVoucherNo("20190124-00001");
        System.out.println(voucher);
        return ResponseEntity.ok().body(BaseResponse.builder().messageType(BaseResponse.MESSAGE_TYPE_SUCCESS).messageTitle(BaseResponse.MESSAGE_TITLE_SUCCESS).messageDesc(messages.get("save.success")).data(new String[]{voucher.getVoucherNo()}).build());
    }

}
