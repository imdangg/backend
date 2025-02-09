import {API_URL, getAuthHeaders} from "./common.js";

export async function fetchMember() {
    const response = await fetch(`${API_URL}/members/detail`, {
        // method: "GET",
        headers: {
            "Content-Type": "application/json",
            ...getAuthHeaders()
        },
        credentials: "include",  // 인증 정보 포함
    });

    if (!response.ok) {
        throw new Error("멤버 정보를 불러오지 못했습니다.");
    }
    return response.json();
}

export async function fetchMemberInfo(memberId) {
    const response = await fetch(`${API_URL}/members/info?memberId=${memberId}`, {
        // method: "GET",
        headers: {
            "Content-Type": "application/json",
            ...getAuthHeaders()
        },
        credentials: "include",  // 인증 정보 포함
    });

    if (!response.ok) {
        throw new Error("멤버 정보를 불러오지 못했습니다.");
    }
    return response.json();
}
