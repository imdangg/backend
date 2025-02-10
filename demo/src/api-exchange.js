import {API_URL, getAuthHeaders} from "./common.js";

export async function requestExchange(requestedInsightId, requestMemberInsightId, memberCouponId) {
    const response = await fetch(`${API_URL}/exchanges/request`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            ...getAuthHeaders()
        },
        body: JSON.stringify({
            requestedInsightId: requestedInsightId,
            requestMemberInsightId: requestMemberInsightId,
            memberCouponId: memberCouponId
        }),
    });

    if (!response.ok) {
        throw new Error('교환 요청에 실패했습니다.');
    }
    return response.json();
}

export async function acceptExchangeRequest(exchangeRequestId) {
    const response = await fetch(`${API_URL}/exchanges/accept`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            ...getAuthHeaders()
        },
        body: JSON.stringify({
            exchangeRequestId: exchangeRequestId
        }),
    });

    if (!response.ok) {
        throw new Error('수락에 실패했습니다.');
    }
    return response.json();
}

export async function rejectExchangeRequest(exchangeRequestId) {
    const response = await fetch(`${API_URL}/exchanges/reject`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            ...getAuthHeaders()
        },
        body: JSON.stringify({
            exchangeRequestId: exchangeRequestId
        }),
    });

    if (!response.ok) {
        throw new Error('거절에 실패했습니다.');
    }
    return response.json();
}
