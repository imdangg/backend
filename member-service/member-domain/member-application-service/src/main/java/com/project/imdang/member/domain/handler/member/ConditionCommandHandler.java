package com.project.imdang.member.domain.handler.member;

import com.project.imdang.common.domain.exception.DomainException;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.common.domain.valueobject.Purpose;
import com.project.imdang.member.domain.MemberDomainService;
import com.project.imdang.member.domain.dto.member.*;
import com.project.imdang.member.domain.entity.ActualLiving;
import com.project.imdang.member.domain.entity.GapInvestment;
import com.project.imdang.member.domain.entity.Member;
import com.project.imdang.member.domain.exception.MemberDomainException;
import com.project.imdang.member.domain.handler.MemberHelper;
import com.project.imdang.member.domain.ports.output.repository.ConditionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
@RequiredArgsConstructor
public class ConditionCommandHandler {

    private final MemberDomainService memberDomainService;
    private final MemberHelper memberHelper;
    private final ConditionRepository conditionRepository;

    @Transactional
    public Boolean condition(ConditionCommand conditionCommand, PriorityCommand priorityCommand, InterestDistrictCommand interestDistrictCommand) {
        // 1. 토큰에서 유저 정보 추출 후 검증
        final MemberId memberId = conditionCommand.getMemberId();
        Member member = memberHelper.get(memberId);

        // 2. 우선순위 저장
        member = memberDomainService.setPriority(member, priorityCommand.getFirstPriority(), priorityCommand.getSecondPriority(), priorityCommand.getThirdPriority());

        // 3. 관심동네 저장
        if (interestDistrictCommand.getInterestDistrict() != null) {
            member = memberDomainService.setInterestDistrict(member, interestDistrictCommand.getInterestDistrict());
        }

        //4-1. 공통 조건 저장
        member = memberDomainService.setCommonCondition(member, conditionCommand.getPurpose(), conditionCommand.getBudget(), conditionCommand.getMonthIncome());
        memberHelper.save(member);

        //4-2. 실거주인 경우
        if (conditionCommand.getPurpose() == Purpose.LIVING) {
            ActualLivingConditionCommand actualLivingConditionCommand = (ActualLivingConditionCommand) conditionCommand;
            ActualLiving actualLiving = memberDomainService.setActualLivingCondition(
                    memberId,
                    actualLivingConditionCommand.getLivingPerson(),
                    actualLivingConditionCommand.getChildrenPlan(),
                    actualLivingConditionCommand.getSchoolDistrict(),
                    actualLivingConditionCommand.getTraffic(),
                    actualLivingConditionCommand.getCommutingArea(),
                    actualLivingConditionCommand.getInfra(),
                    actualLivingConditionCommand.getEnvironment()
            );
            saveAC(actualLiving);
        }
        //4-2. 갭투자인 경우
        else if (conditionCommand.getPurpose() == Purpose.GAP_INVESTMENT){
            GapInvestmentConditionCommand gapInvestmentConditionCommand = (GapInvestmentConditionCommand) conditionCommand;
            GapInvestment gapInvestment = memberDomainService.setGapInvestmentCondition(
                    memberId,
                    gapInvestmentConditionCommand.getHopeGap(),
                    gapInvestmentConditionCommand.getInvestmentPlan(),
                    gapInvestmentConditionCommand.getApartmentSquare(),
                    gapInvestmentConditionCommand.getHousehold(),
                    gapInvestmentConditionCommand.getHouseType(),
                    gapInvestmentConditionCommand.getCommutingArea(),
                    gapInvestmentConditionCommand.getInfra(),
                    gapInvestmentConditionCommand.getEnvironment()
            );
            saveGC(gapInvestment);
        }
        return true;
    }


    private ActualLiving saveAC(ActualLiving actualLiving) {
        ActualLiving savedActualLiving = conditionRepository.saveActualLiving(actualLiving);
        if (savedActualLiving == null) {
            String errorMessage = "Could not save ActualLiving Contidition!";
            log.error(errorMessage);
            throw new MemberDomainException(errorMessage);
        }
        log.info("Member[id : {}] ActualLiving Condition is saved.", actualLiving.getMemberId().getValue());
        return savedActualLiving;
    }

    private GapInvestment saveGC(GapInvestment gapInvestment) {
        GapInvestment savedGapInvestment = conditionRepository.saveGapInvestment(gapInvestment);
        if (savedGapInvestment == null) {
            String errorMessage = "Could not save GapInvestment Contidition!";
            log.error(errorMessage);
            throw new MemberDomainException(errorMessage);
        }
        log.info("Member[id : {}] GapInvestment Condition is saved.", gapInvestment.getMemberId().getValue());
        return savedGapInvestment;
    }
}
