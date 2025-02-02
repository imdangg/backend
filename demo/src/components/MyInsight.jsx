import {useEffect, useState} from "react";
import {fetchDistricts} from "../api-my-insight.js";

export default function MyInsight() {
    const [districts, setDistricts] = useState([]);
    const [insights, setInsights] = useState([]);

    useEffect(() => {
        fetchDistricts()
            .then(data => {
                console.log(data);
                return data;
            })
            .then(setDistricts);
    }, []);

    return (
        <div className="insight-list">
            <h1>보관함</h1>

            <table>
                <thead>
                <tr>
                    <th>자치구</th>
                    <th>단지</th>
                    <th>인사이트</th>
                </tr>
                </thead>
                <tbody>
                {districts.map((district) => (
                    <tr key={`${district.siDo}-${district.siGunGu}-${district.eupMyeonDong}`}>
                        <td>{district.siDo} {district.siGunGu} {district.eupMyeonDong}</td>
                        <td>{district.apartmentComplexCount}</td>
                        <td>{district.insightCount}</td>
                    </tr>
                ))}
                </tbody>
            </table>
{/*
            <table>
                <thead>
                <tr>
                    <th>ID</th>
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
                        <td>{insight.insightId}</td>
                        <td>{insight.title}</td>
                        <td>{insight.memberNickname}</td>
                        <td>{insight.address.siDo} {insight.address.siGunGu} {insight.address.eupMyeonDong}</td>
                        <td>{insight.recommendedCount}</td>
                        <td>{insight.mainImage}</td>
                        <td>{insight.createdAt}</td>
                    </tr>
                ))}
                </tbody>
            </table>*/}
        </div>
    );
}
