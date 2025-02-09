import React, {useEffect, useState} from 'react';
import {fetchMember} from "../api-member.js";

export default function MyPage() {
    const [member, setMember] = useState(null);

    useEffect(() => {
        fetchMember()
            .then(member => {
                console.log(member);
                return member;
            })
            .then(setMember);
    }, []);

    return (
        <>
            <div className="min-h-screen bg-gray-100 py-10 px-5">
                <h2 className="text-3xl font-bold text-black-800 mb-4">MY 페이지</h2>
                <div className="mt-8 p-6 bg-white shadow-md rounded-lg">
                    <div className="mt-4 text-sm text-gray-500">
                        {member && (
                            <>
                            <p><strong>nickname:</strong> {member.nickname}</p>
                            <p><strong>insightCount:</strong> {member.insightCount}</p>
                            <p><strong>requestCount:</strong> {member.requestCount}</p>
                            </>
                        )}
                    </div>
                </div>
            </div>
        </>
    );
}
