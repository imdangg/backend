import {API_URL, getAuthHeaders} from "./common.js";

export async function issueCoupon(name) {
    const response = await fetch(`${API_URL}/coupons/issue`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            ...getAuthHeaders()
        },
        body: JSON.stringify({
            name: name
        }),
    });

    if (!response.ok) {
        throw new Error();
    }
    return response.json();
}
