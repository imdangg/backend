package com.project.imdang.member.application.dto.member;

import com.project.imdang.common.domain.valueobject.*;
import com.project.imdang.common.domain.valueobject.gapInvestment.*;
import com.project.imdang.common.domain.valueobject.living.ChildrenPlan;
import com.project.imdang.common.domain.valueobject.living.LivingPerson;
import com.project.imdang.common.domain.valueobject.living.SchoolDistrict;
import com.project.imdang.common.domain.valueobject.living.Traffic;
import io.swagger.v3.oas.annotations.media.Schema;

public record OnboardingRequest(
        @Schema(description = "목적", example = "실거주")
        Purpose purpose,
        @Schema(description = "예산", example = "3억 이하")
        Budget budget,
        @Schema(description = "월수입", example = "300만원 이하")
        MonthIncome monthIncome,
        @Schema(description = "함께 살 인원", example = "신혼부부")
        LivingPerson livingPerson,
        @Schema(description = "아이 유무", example = "저녀 계획 있음")
        ChildrenPlan childrenPlan,
        @Schema(description = "희망 갭 정도", example = "갭 20% 이상")
        HopeGap hopeGap,
        @Schema(description = "투자 계획", example = "2년 미만")
        InvestmentPlan investmentPlan,
        @Schema(description = "교통", example = "역세권")
        Traffic traffic,
        @Schema(description = "학군", example = "학원가 근접")
        SchoolDistrict schoolDistrict,
        @Schema(description = "아파트 평수", example = "초소형(21~40m2)")
        ApartmentSquare apartmentSquare,
        @Schema(description = "세대수", example = "300세대 이상")
        Household household,
        @Schema(description = "유형", example = "신축")
        HouseType houseType,
        @Schema(description = "출퇴근 지역", example = "여의도")
        CommutingArea commutingArea,
        @Schema(description = "인프라", example = "대형마트")
        InfraNew infra,
        @Schema(description = "환경", example = "공원")
        Environment environment,
        @Schema(description = "1순위", example = "교통,역세권")
        String firstPriority,
        @Schema(description = "2순위", example = "학군,학원가 근접")
        String secondPriority,
        @Schema(description = "3순위", example = "환경,아파트 밀집")
        String thirdPriority,
        @Schema(description = "관심동네", example = "종로구,강남구,마포구")
        String interestDistrict
) {
}
