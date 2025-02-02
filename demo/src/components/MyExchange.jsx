import { useEffect, useState } from "react";
import { fetchInsightsRequestedByMe, fetchInsightsRequestedByOthers } from "../api-my-exchange.js";

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
        <div className="insight-list">
            <h1>교환소</h1>
            {/* 탭 버튼 */}
            <div>
                <button onClick={() => handleTabChange('requestedByMe')}>내가 요청한 내역</button>
                <button onClick={() => handleTabChange('requestedByOthers')}>요청 받은 내역</button>
            </div>

            {/* 현재 활성화된 탭에 따라 버튼 표시 */}
            <div>
                <h2>{activeTab === 'requestedByMe' ? '내가 요청한 내역' : '요청 받은 내역'}</h2>
                <button onClick={() => handleExchangeRequestStatusClick('PENDING')}>대기 중</button>
                <button onClick={() => handleExchangeRequestStatusClick('REJECTED')}>거절</button>
                <button onClick={() => handleExchangeRequestStatusClick('ACCEPTED')}>교환 완료</button>
            </div>

            <table>
                <thead>
                <tr>
                    {/*<th>ID</th>*/}
                    <th>Title</th>
                    <th>Member Nickname</th>
                    <th>Address</th>
                    <th>Recommended Count</th>
                    <th>Main Image</th>
                    <th>Created At</th>
                </tr>
                </thead>
                <tbody>
                {insights.map((insight) => (
                    <tr key={insight.insightId}>
                        {/*<td>{insight.insightId}</td>*/}
                        <td>{insight.title}</td>
                        <td>{insight.memberNickname}</td>
                        <td>{insight.address.siDo} {insight.address.siGunGu} {insight.address.eupMyeonDong}</td>
                        <td>{insight.recommendedCount}</td>
                        <td>{insight.mainImage}</td>
                        <td>{insight.createdAt}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
}
