import React, {useEffect, useState} from "react";
import {
    fetchApartmentComplexesOfInsightsCreatedByMe,
    fetchInsights,
    fetchInsightsByApartmentComplex,
    fetchInsightsByDate,
    fetchInsightsByDistrict
} from "../api-insight.js";
import {Link} from "react-router-dom";
import "../App.css";
import {fetchEupMyeonDongs, fetchSiGunGus} from "../api-district.js";

export default function Home() {
    const [siGunGus, setSiGunGus] = useState([]);
    const [siGunGu, setSiGunGu] = useState(null);
    const [eupMyeonDongs, setEupMyeonDongs] = useState([]);
    const [eupMyeonDong, setEupMyeonDong] = useState(null);
    // 지역으로 찾기
    const [insightsByDistrict, setInsightsByDistrict] = useState([]);
    // 내가 다녀온 단지 목록
    const [myVisitedApartmentComplexes, setMyVisitedApartmentComplexes] = useState([]);
    // 내가 다녀온 단지
    const [myVisitedApartmentComplex, setMyVisitedApartmentComplex] = useState(null);
    // 내가 다녀온 단지의 다른 인사이트
    const [insightsOfMyVisitedApartmentComplex, setInsightsOfMyVisitedApartmentComplex] = useState([]);
    // 오늘 새롭게 올라온 인사이트
    const [insightsOfToday, setInsightsOfToday] = useState([]);
    // 추천수 TOP 10 인사이트
    const [insightsOfRecommendedTop10, setInsightsOfRecommendedTop10] = useState([]);
    
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
        fetchApartmentComplexesOfInsightsCreatedByMe()
            .then(data => {
                console.log(data);
                return data;
            })
            .then(setMyVisitedApartmentComplexes);
    }, []);

    useEffect(() => {
        if (myVisitedApartmentComplex !== null) {
            fetchInsightsByApartmentComplex(myVisitedApartmentComplex)
                .then(data => {
                    console.log(data);
                    return data.content;
                })
                .then(setInsightsOfMyVisitedApartmentComplex);
        }
    }, [myVisitedApartmentComplex])

    useEffect(() => {
        fetchInsightsByDate()
            .then(data => {
                return data.content;
            })
            .then(setInsightsOfToday);
    }, []);

    useEffect(() => {
        fetchInsights()
            .then(data => {
                return data.content;
            })
            .then(setInsightsOfRecommendedTop10);
    }, []);

    return (
        <div className="min-h-screen bg-gray-100 py-10 px-5">
            <h2 className="text-3xl font-bold text-black-800 mb-4">탐색</h2>
            <div className="max-w-6xl mx-auto space-y-8">

                {/* 검색(지역으로 찾기) */}
                <div className="flex space-x-8 mb-8">
                <div className="bg-white shadow-lg rounded-lg p-6">
                    <h2 className="text-2xl font-semibold text-gray-800 mb-4">검색(지역으로 찾기)</h2>
                    <table className="min-w-full table-auto border border-black-300">
                        <thead>
                        <tr className="bg-orange-400">
                            <th className="px-4 py-2 text-left text-black-700 font-medium border border-black-300">시/군/구</th>
                        </tr>
                        </thead>
                        <tbody>
                        {siGunGus.map((district) => (
                            <tr className="bg-orange-400" key={district.code}
                                style={{
                                    cursor: 'pointer',
                                    backgroundColor: siGunGu === district.siGunGu ? '#f0f0f0' : 'white',
                                }}
                                onClick={() => {
                                    setSiGunGu(district.siGunGu);
                                }}>
                                <td className="px-4 py-2 text-gray-700 border border-black-300">{district.siGunGu}</td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                    <table className="min-w-full table-auto border border-black-300">
                        <thead>
                        <tr className="bg-orange-400">
                            <th className="px-4 py-2 text-left text-black-700 font-medium border border-black-300">읍/면/동</th>
                        </tr>
                        </thead>
                        <tbody>
                        {eupMyeonDongs.map((district) => (
                            <tr className="bg-gray-50" key={district.code}
                                style={{
                                    cursor: 'pointer',
                                    backgroundColor: eupMyeonDong === district.eupMyeonDong ? '#f0f0f0' : 'white',
                                }}
                                onClick={() => {
                                    setEupMyeonDong(district.eupMyeonDong);
                                }}>
                                <td className="px-4 py-2 text-gray-700 border border-black-300">{district.eupMyeonDong}</td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                    <table className="min-w-full table-auto border border-black-300">
                        <thead>
                        <tr className="bg-orange-400">
                            <th className="px-4 py-2 text-left text-black-700 font-medium border border-black-300">Title</th>
                            <th className="px-4 py-2 text-left text-black-700 font-medium border border-black-300">Member Nickname</th>
                            <th className="px-4 py-2 text-left text-black-700 font-medium border border-black-300">Address</th>
                            <th className="px-4 py-2 text-left text-black-700 font-medium border border-black-300">Recommended Count</th>
                            <th className="px-4 py-2 text-left text-black-700 font-medium border border-black-300">Main Image</th>
                            <th className="px-4 py-2 text-left text-black-700 font-medium border border-black-300">Detail</th>
                        </tr>
                        </thead>
                        <tbody>
                        {insightsByDistrict.map((insight) => (
                            <tr className="bg-gray-50" key={insight.insightId}>
                                <td className="px-4 py-2 text-gray-700 border border-black-300">{insight.title}</td>
                                <td className="px-4 py-2 text-gray-700 border border-black-300">{insight.memberNickname}</td>
                                <td className="px-4 py-2 text-gray-700 border border-black-300">{insight.address.siDo} {insight.address.siGunGu} {insight.address.eupMyeonDong} {insight.address.buildingNumber}</td>
                                <td className="px-4 py-2 text-gray-700 border border-black-300">{insight.recommendedCount}</td>
                                <td className="px-4 py-2 text-gray-700 border border-black-300">{insight.mainImage}</td>
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

                {/* 내가 다녀온 단지의 다른 인사이트 */}
                <div className="flex space-x-8 mb-8">
                <div className="bg-white shadow-lg rounded-lg p-6">
                    <h2 className="text-2xl font-semibold text-gray-800 mb-4">내가 다녀온 단지의 다른 인사이트</h2>
                    <table className="min-w-full table-auto border border-black-300">
                        <thead>
                        <tr className="bg-orange-400">
                            <th className="px-4 py-2 text-left text-black-700 font-medium border border-black-300">내가 다녀온 단지</th>
                        </tr>
                        </thead>
                        <tbody>
                        {myVisitedApartmentComplexes.map((apartmentComplex) => (
                            <tr className="bg-orange-400" key={apartmentComplex.name}
                                style={{
                                    cursor: 'pointer',
                                    backgroundColor: myVisitedApartmentComplex === apartmentComplex.name ? '#f0f0f0' : 'white',
                                }}
                                onClick={() => {
                                    setMyVisitedApartmentComplex(apartmentComplex.name);
                                }}>
                                <td className="px-4 py-2 text-gray-700 border border-black-300">{apartmentComplex.name}</td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                    <table className="min-w-full table-auto border border-black-300">
                        <thead>
                        <tr className="bg-orange-400">
                            <th className="px-4 py-2 text-left text-black-700 font-medium border border-black-300">Title</th>
                            <th className="px-4 py-2 text-left text-black-700 font-medium border border-black-300">Member Nickname</th>
                            <th className="px-4 py-2 text-left text-black-700 font-medium border border-black-300">Address</th>
                            <th className="px-4 py-2 text-left text-black-700 font-medium border border-black-300">Recommended Count</th>
                            <th className="px-4 py-2 text-left text-black-700 font-medium border border-black-300">Main Image</th>
                            <th className="px-4 py-2 text-left text-black-700 font-medium border border-black-300">Detail</th>
                        </tr>
                        </thead>
                        <tbody>
                        {insightsOfMyVisitedApartmentComplex.map((insight) => (
                            <tr className="bg-gray-50" key={insight.insightId}>
                                <td className="px-4 py-2 text-gray-700 border border-black-300">{insight.title}</td>
                                <td className="px-4 py-2 text-gray-700 border border-black-300">{insight.memberNickname}</td>
                                <td className="px-4 py-2 text-gray-700 border border-black-300">{insight.address.siDo} {insight.address.siGunGu} {insight.address.eupMyeonDong} {insight.address.buildingNumber}</td>
                                <td className="px-4 py-2 text-gray-700 border border-black-300">{insight.recommendedCount}</td>
                                <td className="px-4 py-2 text-gray-700 border border-black-300">{insight.mainImage}</td>
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

                {/* 오늘 새롭게 올라온 인사이트 */}
                <div className="bg-white shadow-lg rounded-lg p-6">
                    <h2 className="text-2xl font-semibold text-gray-800 mb-4">오늘 새롭게 올라온 인사이트</h2>
                    <table className="min-w-full table-auto border border-black-300">
                        <thead>
                        <tr className="bg-orange-400">
                            <th className="px-4 py-2 text-left text-black-700 font-medium border border-black-300">Title</th>
                            <th className="px-4 py-2 text-left text-black-700 font-medium border border-black-300">Member Nickname</th>
                            <th className="px-4 py-2 text-left text-black-700 font-medium border border-black-300">Address</th>
                            <th className="px-4 py-2 text-left text-black-700 font-medium border border-black-300">Recommended Count</th>
                            <th className="px-4 py-2 text-left text-black-700 font-medium border border-black-300">Main Image</th>
                            <th className="px-4 py-2 text-left text-black-700 font-medium border border-black-300">Detail</th>
                        </tr>
                        </thead>
                        <tbody>
                        {insightsOfToday.map((insight) => (
                            <tr className="bg-gray-50" key={insight.insightId}>
                                <td className="px-4 py-2 text-gray-700 border border-black-300">{insight.title}</td>
                                <td className="px-4 py-2 text-gray-700 border border-black-300">{insight.memberNickname}</td>
                                <td className="px-4 py-2 text-gray-700 border border-black-300">{insight.address.siDo} {insight.address.siGunGu} {insight.address.eupMyeonDong} {insight.address.buildingNumber}</td>
                                <td className="px-4 py-2 text-gray-700 border border-black-300">{insight.recommendedCount}</td>
                                <td className="px-4 py-2 text-gray-700 border border-black-300">{insight.mainImage}</td>
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

                {/* 추천수 TOP 10 인사이트 */}
                <div className="bg-white shadow-lg rounded-lg p-6">
                    <h2 className="text-2xl font-semibold text-gray-800 mb-4">추천수 TOP 10 인사이트</h2>
                    <table className="min-w-full table-auto border border-black-300">
                        <thead>
                        <tr className="bg-orange-400">
                            <th className="px-4 py-2 text-left text-black-700 font-medium border border-black-300">Title</th>
                            <th className="px-4 py-2 text-left text-black-700 font-medium border border-black-300">Member Nickname</th>
                            <th className="px-4 py-2 text-left text-black-700 font-medium border border-black-300">Address</th>
                            <th className="px-4 py-2 text-left text-black-700 font-medium border border-black-300">Recommended Count</th>
                            <th className="px-4 py-2 text-left text-black-700 font-medium border border-black-300">Main Image</th>
                            <th className="px-4 py-2 text-left text-black-700 font-medium border border-black-300">Detail</th>
                        </tr>
                        </thead>
                        <tbody>
                        {insightsOfRecommendedTop10.map((insight) => (
                            <tr className="bg-gray-50" key={insight.insightId}>
                                <td className="px-4 py-2 text-gray-700 border border-black-300">{insight.title}</td>
                                <td className="px-4 py-2 text-gray-700 border border-black-300">{insight.memberNickname}</td>
                                <td className="px-4 py-2 text-gray-700 border border-black-300">{insight.address.siDo} {insight.address.siGunGu} {insight.address.eupMyeonDong} {insight.address.buildingNumber}</td>
                                <td className="px-4 py-2 text-gray-700 border border-black-300">{insight.recommendedCount}</td>
                                <td className="px-4 py-2 text-gray-700 border border-black-300">{insight.mainImage}</td>
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
    );
}
