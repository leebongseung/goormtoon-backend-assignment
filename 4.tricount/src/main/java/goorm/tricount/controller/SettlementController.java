package goorm.tricount.controller;

import goorm.tricount.annotation.Login;
import goorm.tricount.api.ErrorCode;
import goorm.tricount.api.response.ApiResponse;
import goorm.tricount.domain.dto.request.settlement.CreateSettlementDto;
import goorm.tricount.domain.dto.response.settlement.SettlementDetailDto;
import goorm.tricount.domain.dto.response.settlement.SettlementDto;
import goorm.tricount.service.SettlementService;
import goorm.tricount.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/settle")
@RequiredArgsConstructor
public class SettlementController {

    private final SettlementService settlementService;

    @GetMapping
    public ApiResponse<SettlementDto> findAllSettlement(@Login Long usersId){
        log.info("SettlementController.findAllSettlement");
        List<SettlementDto> settlementDtos= settlementService.findAllSettlementByUserId(usersId);
        return new ApiResponse<>(ErrorCode.OK, settlementDtos);
    }

    @PostMapping("/add")
    public ApiResponse<SettlementDetailDto> addSettlement(@RequestBody CreateSettlementDto settlementDto, @Login Long usersId){
        log.info("SettlementController.addSettlment");
        SettlementDetailDto settlementDetailDto = settlementService.createSettlement(usersId, settlementDto);

        return new ApiResponse<>(ErrorCode.OK, settlementDetailDto);
    }
//
    @GetMapping("/{settlementId}")
    public ApiResponse<SettlementDetailDto> findSettlement(@PathVariable("settlementId") Long settlementId){
        log.info("SettlementController.findSettlement");
        SettlementDetailDto settlementDetailDto= settlementService.findSettlementDetailDtoBySettlementId(settlementId);
        return new ApiResponse<>(ErrorCode.OK, settlementDetailDto);
    }

    @DeleteMapping("/{settlementId}")
    public ApiResponse<String> deleteSettlement(@PathVariable ("settlementId") Long settlementId){
        log.info("SettlementController.deleteSettlement");
        settlementService.deleteSettlementBySettlementId(settlementId);
        return new ApiResponse<>(ErrorCode.OK, "계모임 삭제 완료");
    }

    @PostMapping("/{settlementId}/join")
    public ApiResponse<SettlementDetailDto> joinSettlement(@PathVariable("settlementId") Long settlementId, @Login Long usersId){
        log.info("SettlementController.joinSettlement");
        SettlementDetailDto settlementDetailDto = settlementService.joinSettlementBySettlementId(usersId, settlementId);

        return new ApiResponse<>(ErrorCode.OK, settlementDetailDto);
    }
}
