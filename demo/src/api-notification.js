import {API_URL, getAuthHeaders} from "./common.js";

export async function checkExistUnchecked() {
    const response = await fetch(`${API_URL}/notification/unchecked`, {
        // method: "GET",
        headers: {
            "Content-Type": "application/json",
            ...getAuthHeaders()
        },
        credentials: "include",  // 인증 정보 포함
    });

    if (!response.ok) {
        throw new Error();
    }
    return response.json();
}

export async function fetchNotifications() {
    const response = await fetch(`${API_URL}/notifications`, {
        // method: "GET",
        headers: {
            "Content-Type": "application/json",
            ...getAuthHeaders()
        },
        credentials: "include",  // 인증 정보 포함
    });

    if (!response.ok) {
        throw new Error();
    }
    return response.json();
}
