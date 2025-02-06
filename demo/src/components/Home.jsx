import {useEffect, useState} from "react";
import "../App.css";
import {Link} from 'react-router-dom';
import {fetchEupMyeonDongs, fetchInsights, fetchInsightsByDistrict, fetchSiGunGus} from "../api-insight.js";

export default function Home() {
    const [siGunGus, setSiGunGus] = useState([]);
    const [siGunGu, setSiGunGu] = useState(null);
    const [eupMyeonDongs, setEupMyeonDongs] = useState([]);
    const [eupMyeonDong, setEupMyeonDong] = useState(null);
    const [insightsByDistrict, setInsightsByDistrict] = useState([]);
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
        fetchSiGunGus()
            .then(data => {
                console.log(data);
                return data.content;
            })
            .then(setSiGunGus);
    }, [])

    useEffect(() => {
        if (siGunGu !== null) {
            fetchEupMyeonDongs(siGunGu)
                .then(data => {
                    console.log(data);
                    return data.content;
                })
                .then(setEupMyeonDongs);
        }
    }, [siGunGu])

    useEffect(() => {
        if (eupMyeonDong !== null) {
            fetchInsightsByDistrict(siGunGu, eupMyeonDong)
                .then(data => {
                    console.log(data);
                    return data.content;
                })
                .then(setInsightsByDistrict);
        }
    }, [eupMyeonDong])

    useEffect(() => {
        fetchInsights()
            .then(data => {
                return data.content;
            })
            .then(setInsights);
    }, []);

    return (
        <>
        <h1>탐색</h1>
        <h2>검색(지역으로 찾기)</h2>
        <div>
            <table className="table" style={{display: "inline-block"}}>
                <thead>
                <tr>
                    <th>시/군/구</th>
                </tr>
                </thead>
                <tbody>
                {siGunGus.map((district) => (
                    <tr key={district.code}
                        style={{
                            cursor: 'pointer',
                            backgroundColor: siGunGu === district.siGunGu ? '#f0f0f0' : 'white',
                        }}
                        onClick={() => {
                            setSiGunGu(district.siGunGu);
                        }}>
                        <td>{district.siGunGu}</td>
                    </tr>
                ))}
                </tbody>
            </table>

            <table className="table" style={{display: "inline-block"}}>
                <thead>
                <tr>
                    <th>읍/면/동</th>
                </tr>
                </thead>
                <tbody>
                {eupMyeonDongs.map((district) => (
                    <tr key={district.code}
                        style={{
                            cursor: 'pointer',
                            backgroundColor: eupMyeonDong === district.eupMyeonDong ? '#f0f0f0' : 'white',
                        }}
                        onClick={() => {
                            setEupMyeonDong(district.eupMyeonDong);
                        }}>
                        <td>{district.eupMyeonDong}</td>
                    </tr>
                ))}
                </tbody>
            </table>

            <table className="table" style={{display: "inline-block"}}>
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
                {insightsByDistrict.map((insight) => (
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
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>

        <h2>내가 다녀온 단지의 다른 인사이트</h2>
        <div className="insight-list">
            <table className="table">
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
        </div>

        <h2>오늘 새롭게 올라온 인사이트</h2>
        <div>
        </div>

        <h2>추천수 TOP 10 인사이트</h2>
        <div>
        </div>
        </>
    );
}
