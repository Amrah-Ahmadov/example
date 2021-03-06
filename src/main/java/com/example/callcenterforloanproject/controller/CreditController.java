package com.example.callcenterforloanproject.controller;

import com.example.callcenterforloanproject.dto.CreditDto;
import com.example.callcenterforloanproject.model.entity.Credit;
import com.example.callcenterforloanproject.service.ConverterService;
import com.example.callcenterforloanproject.service.CreditService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/credit")
public class CreditController {
    private final CreditService creditService;
    private final ConverterService converterService;

    public CreditController(CreditService creditService, ConverterService converterService) {
        this.creditService = creditService;
        this.converterService = converterService;
    }
    @PostMapping
    public ResponseEntity<CreditDto> addNewCredit(@RequestBody Credit credit){
        CreditDto creditDto = converterService.convertCreditToCreditDto(creditService.addNewCredit(credit));
        return new ResponseEntity<>(creditDto, HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<CreditDto> deleteCreditById(@PathVariable Long id){
        Credit credit = creditService.removeCreditById(id);
        return new ResponseEntity<>(converterService.convertCreditToCreditDto(credit), HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<CreditDto> getCreditById(@PathVariable Long id){
        Credit credit = creditService.getCreditById(id);
        return new ResponseEntity<>(converterService.convertCreditToCreditDto(credit), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<CreditDto>> getAllCredits(){
        List<Credit> allCredits = creditService.getAllCredits();
        return new ResponseEntity<>(allCredits.stream()
                .map(converterService::convertCreditToCreditDto).collect(Collectors.toList()),
                HttpStatus.OK);
    }
}
