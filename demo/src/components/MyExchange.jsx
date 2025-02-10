import React, { useEffect, useState } from "react";
import { fetchInsightsRequestedByMe, fetchInsightsRequestedByOthers } from "../api-my-exchange.js";
import {Link} from "react-router-dom";

export default function MyExchange() {
    const [activeTab, setActiveTab] = useState('requestedByMe');
    const [insights, setInsights] = useState([]);

    const handleTabChange = (tab) => {
        setActiveTab(tab);
        setInsights([]);
    };

    const handleExchangeRequestStatusClick = (exchangeRequestStatus) => {
        if (activeTab && activeTab === 'requestedByMe') {
            fetchInsightsRequestedByMe(exchangeRequestStatus)
                .then(data => {
                    // console.log(data);
                    return data.content;
                })
                .then(setInsights);
        } else {
            // requestedByOthers
            fetchInsightsRequestedByOthers(exchangeRequestStatus)
                .then(data => {
                    // console.log(data);
                    return data.content;
                })
                .then(setInsights);
        }
    }

    return (
        <div className="min-h-screen bg-gray-100 py-10 px-5">
            <h2 className="text-3xl font-bold text-black-800 mb-4">교환소</h2>
            <div className="max-w-6xl mx-auto space-y-8">

                <div className="flex space-x-8 mb-8">
                    <div className="bg-white shadow-lg rounded-lg p-6">
                        <h2 className="text-2xl font-semibold text-gray-800 mb-4">{activeTab === 'requestedByMe' ? '내가 요청한 내역' : '요청 받은 내역'}</h2>
                        <div className="mb-2">
                            <button
                                className="mr-2 px-4 py-1 bg-gray-400 text-white font-semibold rounded-lg shadow-md hover:bg-gray-600 focus:outline-none focus:ring-2 focus:ring-gray-400 focus:ring-opacity-75 transition duration-200 ease-in-out"
                                onClick={() => handleTabChange('requestedByMe')}>
                                내가 요청한 내역
                            </button>
                            <button
                                className="mr-2 px-4 py-1 bg-gray-400 text-white font-semibold rounded-lg shadow-md hover:bg-gray-600 focus:outline-none focus:ring-2 focus:ring-gray-400 focus:ring-opacity-75 transition duration-200 ease-in-out"
                                onClick={() => handleTabChange('requestedByOthers')}>
                                요청 받은 내역
                            </button>
                        </div>
                        <div className="mb-2">
                            <button
                                className="mr-2 px-6 py-2 bg-gray-400 text-white font-semibold rounded-lg shadow-md hover:bg-gray-600 focus:outline-none focus:ring-2 focus:ring-gray-400 focus:ring-opacity-75 transition duration-200 ease-in-out"
                                onClick={() => handleExchangeRequestStatusClick('PENDING')}>
                                대기 중
                            </button>
                            <button
                                className="mr-2 px-6 py-2 bg-red-400 text-white font-semibold rounded-lg shadow-md hover:bg-red-600 focus:outline-none focus:ring-2 focus:ring-red-400 focus:ring-opacity-75 transition duration-200 ease-in-out"
                                onClick={() => handleExchangeRequestStatusClick('REJECTED')}>
                                거절
                            </button>
                            <button
                                className="mr-2 px-6 py-2 bg-green-400 text-white font-semibold rounded-lg shadow-md hover:bg-green-600 focus:outline-none focus:ring-2 focus:ring-green-400 focus:ring-opacity-75 transition duration-200 ease-in-out"
                                onClick={() => handleExchangeRequestStatusClick('ACCEPTED')}>
                                교환 완료
                            </button>
                        </div>

                        <table className="min-w-full table-auto border border-black-300">
                            <thead>
                            <tr className="bg-orange-400">
                                <th className="px-4 py-2 text-left text-black-700 font-medium border border-black-300">Title</th>
                                <th className="px-4 py-2 text-left text-black-700 font-medium border border-black-300">Member Nickname</th>
                                <th className="px-4 py-2 text-left text-black-700 font-medium border border-black-300">Address</th>
                                <th className="px-4 py-2 text-left text-black-700 font-medium border border-black-300">Recommended Count</th>
                                <th className="px-4 py-2 text-left text-black-700 font-medium border border-black-300">Main Image</th>
                                <th className="px-4 py-2 text-left text-black-700 font-medium border border-black-300">Created At</th>
                                <th className="px-4 py-2 text-left text-black-700 font-medium border border-black-300">Detail</th>
                            </tr>
                            </thead>
                            <tbody>
                            {insights.map((insight) => (
                                <tr className="bg-gray-50" key={insight.insightId}>
                                    <td className="px-4 py-2 text-gray-700 border border-black-300">{insight.title}</td>
                                    <td className="px-4 py-2 text-gray-700 border border-black-300">{insight.memberNickname}</td>
                                    <td className="px-4 py-2 text-gray-700 border border-black-300">{insight.address.siDo} {insight.address.siGunGu} {insight.address.eupMyeonDong}</td>
                                    <td className="px-4 py-2 text-gray-700 border border-black-300">{insight.recommendedCount}</td>
                                    <td className="px-4 py-2 text-gray-700 border border-black-300">{insight.mainImage}</td>
                                    <td className="px-4 py-2 text-gray-700 border border-black-300">{insight.createdAt}</td>
                                    <td className="px-4 py-2 text-gray-700 border border-black-300">
                                        <Link to={`/insight/${insight.insightId}`}>
                                            <button>상세 보기</button>
                                        </Link>
                                    </td>
                                </tr>
                            ))}
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    );
}
