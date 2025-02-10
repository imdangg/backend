package rest;

import com.project.imdang.domain.valueobject.CouponId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.member.service.domain.entity.Coupon;
import com.project.imdang.member.service.domain.entity.Member;
import com.project.imdang.member.service.domain.valueobject.Gender;

import java.util.UUID;

public final class TestData {
    static UUID memberId = UUID.fromString("8a1e1c3d-74ef-42c8-9239-a33455b02eda");
    static UUID couponId = UUID.fromString("597f5ac7-e3ae-4d34-b1c2-990a3ace5160");
    static String fcmToken = "fcm-token";
    static String accessToken = "access-token";
    static Member member = Member.builder()
            .id(new MemberId(memberId))
            .nickname("imdang")
            .birthDate("2024-12-31")
            .gender(Gender.MALE)
            .deviceToken(fcmToken)
            .insightCount(1)
            .exchangeCount(2)
            .rejectedCount(3)
            .build();

    static Member member_2 = Member.builder()
            .id(new MemberId(memberId))
            .nickname("imdang")
            .birthDate("2024-12-31")
            .gender(Gender.MALE)
            .deviceToken(fcmToken)
            .insightCount(1)
            .exchangeCount(2)
            .rejectedCount(5)
            .build();

    static Member member_3 = Member.builder()
            .id(new MemberId(memberId))
            .nickname("imdang")
            .birthDate("2024-12-31")
            .gender(Gender.MALE)
            .deviceToken(fcmToken)
            .insightCount(1)
            .exchangeCount(2)
            .rejectedCount(10)
            .build();

    static Member member_4 = Member.builder()
            .id(new MemberId(memberId))
            .nickname("imdang")
            .birthDate("2024-12-31")
            .gender(Gender.MALE)
            .deviceToken(fcmToken)
            .insightCount(1)
            .exchangeCount(2)
            .rejectedCount(10)
            .build();

    static Coupon welcomeCoupon = Coupon.builder()
            .id(new CouponId(couponId))
            .name("Welcome").build();

    static Coupon firstCoupon = Coupon.builder()
            .id(new CouponId(couponId))
            .name("FirstCheerup").build();

    static Coupon secondCoupon = Coupon.builder()
            .id(new CouponId(couponId))
            .name("SecondCheerup").build();

    static Coupon thirdCoupon = Coupon.builder()
            .id(new CouponId(couponId))
            .name("ThirdCheerup").build();
}
