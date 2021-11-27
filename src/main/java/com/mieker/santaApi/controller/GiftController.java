package com.mieker.santaApi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mieker.santaApi.model.Gift;
import com.mieker.santaApi.service.GiftService;

@RestController
@RequestMapping("/gift")
public class GiftController {
    
    private final GiftService giftService;
    
    public GiftController(GiftService giftService) {
        this.giftService = giftService;
    }

    @GetMapping
    public List<Gift> getAllGifts() {
        return giftService.getAllGifts();
    }
    
    @PostMapping
    public Gift createGift(@RequestBody Gift gift) {
        return giftService.createGift(gift);
    }
    
    @DeleteMapping("/{giftId}")
    public void deleteGift(@PathVariable String giftId) {
        giftService.deleteGift(giftId);
    }
    
    @GetMapping("/{giftId}")
    public Gift getGiftById(@PathVariable String giftId) {
        return giftService.getGiftById(giftId);
    }
    
    @PutMapping("/{giftId}")
    public Gift putGift(@PathVariable String giftId, @RequestBody Gift gift) {
        return giftService.putGift(giftId, gift);
    }
    
    @PatchMapping("/{giftId}")
    public Gift patchGift(@PathVariable String giftId, @RequestBody Gift gift) {
        return giftService.patchGift(giftId, gift);
    }
}
