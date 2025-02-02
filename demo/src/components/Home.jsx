import { useEffect, useState } from "react";
import { Link } from 'react-router-dom';
import { fetchInsights, fetchInsight } from "../api-insight.js";

export default function Home() {
    const [insights, setInsights] = useState([]);
    // const [selectedInsight, setSelectedInsight] = useState(null);
    // const handleShowDetail = (insight) => {
    //     const insightId = insight.insightId;
    //     if (selectedInsight && selectedInsight.insightId === insightId) {
    //         setSelectedInsight(null);
    //     } else {
    //         fetchInsight(insightId)
    //             .then(data => {
    //                 // console.log(data);
    //                 return data;
    //             })
    //             .then(setSelectedInsight);
    //     }
    // };

    useEffect(() => {
        fetchInsights()
            .then(data => {
                return data.content;
            })
            .then(setInsights);
    }, []);

    return (
        <div className="insight-list">
            <h1>탐색</h1>
            <h2>내가 다녀온 단지의 다른 인사이트</h2>
            <table>
                <thead>
                <tr>
                    {/*<th>ID</th>*/}
                    <th>Title</th>
                    <th>Member Nickname</th>
                    <th>Address</th>
                    <th>Recommended Count</th>
                    <th>Main Image</th>
                    <th>Detail</th>
                </tr>
                </thead>
                <tbody>
                {insights.map((insight) => (
                    <tr key={insight.insightId}>
                        {/*<td>{insight.insightId}</td>*/}
                        <td>{insight.title}</td>
                        <td>{insight.memberNickname}</td>
                        <td>{insight.address.siDo} {insight.address.siGunGu} {insight.address.eupMyeonDong} {insight.address.buildingNumber}</td>
                        <td>{insight.recommendedCount}</td>
                        <td>{insight.mainImage}</td>
                        <td>
                            <Link to={`/insight/${insight.insightId}`}>
                                <button>상세 보기</button>
                            </Link>
                            {/*<button onClick={() => handleShowDetail(insight)}>상세 보기</button>*/}
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
            <h2>오늘 새롭게 올라온 인사이트</h2>
            <h2>추천수 TOP 10 인사이트</h2>
        </div>
    );
}
