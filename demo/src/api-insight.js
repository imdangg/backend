import {API_URL, getAuthHeaders} from "./common.js";

export async function fetchSiGunGus() {
    const response = await fetch(`${API_URL}/districts/si-gun-gu`, {
        // method: "GET",
        headers: {
            "Content-Type": "application/json",
            ...getAuthHeaders()
        },
        credentials: "include",  // 인증 정보 포함
    });

    if (!response.ok) {
        throw new Error("인사이트 목록을 불러오지 못했습니다.");
    }
    return response.json();
}

export async function fetchEupMyeonDongs(siGunGu) {
    console.log(siGunGu);
    const response = await fetch(`${API_URL}/districts/eup-myeon-dong?siGunGu=${siGunGu}`, {
        // method: "GET",
        headers: {
            "Content-Type": "application/json",
            ...getAuthHeaders()
        },
        credentials: "include",  // 인증 정보 포함
    });

    if (!response.ok) {
        throw new Error("인사이트 목록을 불러오지 못했습니다.");
    }
    return response.json();
}
export async function fetchInsightsByDistrict(siGunGu, eupMyeonDong) {
    const response = await fetch(`${API_URL}/insights/by-district?siGunGu=${siGunGu}&eupMyeonDong=${eupMyeonDong}`, {
        // method: "GET",
        headers: {
            "Content-Type": "application/json",
            ...getAuthHeaders()
        },
        credentials: "include",  // 인증 정보 포함
    });

    if (!response.ok) {
        throw new Error("인사이트 목록을 불러오지 못했습니다.");
    }
    return response.json();
}

export async function fetchInsights() {
    const response = await fetch(`${API_URL}/insights`, {
        // method: "GET",
        headers: {
            "Content-Type": "application/json",
            ...getAuthHeaders()
        },
        credentials: "include",  // 인증 정보 포함
    });

    if (!response.ok) {
        throw new Error("인사이트 목록을 불러오지 못했습니다.");
    }
    return response.json();
}

export async function fetchInsight(insightId) {
    const response = await fetch(`${API_URL}/insights/detail?insightId=${insightId}`, {
        // method: "GET",
        headers: {
            "Content-Type": "application/json",
            ...getAuthHeaders()
        },
        credentials: "include",  // 인증 정보 포함
    });

    if (!response.ok) {
        throw new Error("인사이트 상세를 불러오지 못했습니다.");
    }
    return response.json();
}

export async function recommendInsight(insightId) {
    const response = await fetch(`${API_URL}/insights/recommend`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            ...getAuthHeaders()
        },
        body: JSON.stringify({ insightId: insightId }),
    });

    if (!response.ok) {
        throw new Error('추천 요청에 실패했습니다.');
    }
    return response.json();
}

export async function accuseInsight(insightId) {
    const response = await fetch(`${API_URL}/insights/accuse`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            ...getAuthHeaders()
        },
        body: JSON.stringify({ insightId: insightId }),
    });

    if (!response.ok) {
        throw new Error('신고 요청에 실패했습니다.');
    }
    return response.json();
}

export async function createInsight(payload) {
    const response = await fetch(`${API_URL}/insights/create`, {
        method: 'POST',
        headers: {
            ...getAuthHeaders()
        },
        body: payload,
    });

    if (!response.ok) {
        throw new Error('생성 요청에 실패했습니다.');
    }
    return response.json();
}
