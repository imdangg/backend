import React, {useEffect, useState} from 'react';
import {useParams} from 'react-router-dom';
import {accuseInsight, fetchInsight, recommendInsight} from "../api-insight.js";
import {fetchInsightsCreatedByMe} from "../api-my-insight.js";
import {requestExchange} from "../api-exchange.js";

export default function InsightDetail() {
    const { id } = useParams();
    const [insight, setInsight] = useState(null);
    const [recommended, setRecommended] = useState(null);
    const [accused, setAccused] = useState(null);
    const [exchangeRequestStatus, setExchangeRequestStatus] = useState(null);
    const [exchangeRequestCreatedByMe, setExchangeRequestCreatedByMe] = useState(null);

    const [exchangeRequested, setExchangeRequested] = useState(false);
    const [myInsights, setMyInsights] = useState([]);
    const [myInsightId, setMyInsightId] = useState(null);

    useEffect(() => {
        fetchInsight(id)
            .then(insight => {
                console.log(insight);
                return insight;
            })
            .then(insight => {
                setInsight(insight);
                setRecommended(insight.recommended);
                setAccused(insight.accused);
                setExchangeRequestStatus(insight.exchangeRequestStatus);
                setExchangeRequestCreatedByMe(insight.exchangeRequestCreatedByMe);
            });
    }, [recommended, accused, exchangeRequestStatus]);

    const handleExchangeRequest = () => {
        if (!exchangeRequested) {
            setExchangeRequested(true);
            fetchInsightsCreatedByMe()
                .then(data => {
                    // console.log(data);
                    return data.content;
                })
                .then(setMyInsights)
        } else {
            setExchangeRequested(false);
            setMyInsightId(null);
        }
    }

    const handleChooseMyInsight = (myInsightId) => {
        setMyInsightId(myInsightId);
    }

    const handleConfirm = () => {
        requestExchange(id, myInsightId).then(result => {
            handleExchangeRequest();
            // console.log(result);
            fetchInsight(id)
                .then(insight => {
                    // console.log(insight);
                    return insight;
                })
                .then(insight => {
                    setInsight(insight);
                    setRecommended(insight.recommended);
                    setAccused(insight.accused);
                    setExchangeRequestStatus(insight.exchangeRequestStatus);
                    setExchangeRequestCreatedByMe(insight.exchangeRequestCreatedByMe);
                });
        })
    }

    const handleAcceptExchangeRequest = (insight) => {

    }

    const handleRejectExchangeRequest = (insight) => {

    };

    const handleRecommendInsight = (insight) => {
        if (recommended) return;
        recommendInsight(insight.insightId).then(result => {
            fetchInsight(id)
                .then(insight => {
                    // console.log(insight);
                    return insight;
                })
                .then(insight => {
                    setInsight(insight);
                    setRecommended(insight.recommended);
                    setAccused(insight.accused);
                    setExchangeRequestStatus(insight.exchangeRequestStatus);
                    setExchangeRequestCreatedByMe(insight.exchangeRequestCreatedByMe);
                });
        });
    }

    const handleAccuseInsight = (insight) => {
        if (accused) return;
        accuseInsight(insight.insightId).then(result => {
            fetchInsight(id)
                .then(insight => {
                    // console.log(insight);
                    return insight;
                })
                .then(insight => {
                    setInsight(insight);
                    setRecommended(insight.recommended);
                    setAccused(insight.accused);
                    setExchangeRequestStatus(insight.exchangeRequestStatus);
                    setExchangeRequestCreatedByMe(insight.exchangeRequestCreatedByMe);
                });
        })
    }

    return (
        <div style={{ marginTop: '20px', border: '1px solid #ddd', padding: '10px' }}>
            <h2>인사이트 상세</h2>
            <div>
                <button onClick={() => handleRecommendInsight(insight)}>추천({recommended ? 'O' : 'X'})</button>
                <button onClick={() => handleAccuseInsight(insight)}>신고({accused ? 'O' : 'X'})</button>
            </div>
            <div>
                {(exchangeRequestStatus === null || exchangeRequestStatus === 'REJECTED') && (
                    <button onClick={() => handleExchangeRequest()}>교환 요청</button>
                )}
                {(exchangeRequestStatus === 'PENDING' && exchangeRequestCreatedByMe === false) && (
                    <>
                        <button onClick={() => handleRejectExchangeRequest(insight)}>거절</button>
                        <button onClick={() => handleAcceptExchangeRequest(insight)}>수락</button>
                    </>
                )}
                {(exchangeRequestStatus === 'PENDING' && exchangeRequestCreatedByMe === true) && (
                    <button disabled>대기중</button>
                )}
                {(exchangeRequestStatus === 'ACCEPTED') && (
                    <button disabled>교환 완료</button>
                )}

                {exchangeRequested && (
                    <>
                    <table border="1" style={{ marginTop: '10px', width: '100%', textAlign: 'center' }}>
                        <thead>
                        <tr>
                            {/*<th>ID</th>*/}
                            <th>Title</th>
                            <th>Recommended Count</th>
                            <th>Address</th>
                        </tr>
                        </thead>
                        <tbody>
                        {myInsights.map((myInsight) => (
                            <tr key={myInsight.insightId}
                                onClick={() => handleChooseMyInsight(myInsight.insightId)}
                                style={{
                                    cursor: 'pointer',
                                    backgroundColor: myInsightId === myInsight.insightId ? '#f0f0f0' : 'white',
                                }}>
                                {/*<td>{myInsight.insightId}</td>*/}
                                <td>{myInsight.title}</td>
                                <td>{myInsight.recommendedCount}</td>
                                <td>{myInsight.address.siDo} {myInsight.address.siGunGu} {myInsight.address.eupMyeonDong}</td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                    <button onClick={handleConfirm}>확인</button>
                    </>
                )}
            </div>
            {insight && (
                <div>
                    <p style={{ color:'blue' }}><strong>access:</strong> {insight.access}</p>
                    <p><strong>accused:</strong> {insight.accused ? 'O' : 'X'}</p>
                    <p><strong>accusedCount:</strong> {insight.accusedCount}</p>
                    <p style={{ color:'blue' }}><strong>address:</strong> {insight.address.siDo} {insight.address.siGunGu} {insight.address.eupMyeonDong}</p>
                    <p style={{ color:'blue' }}><strong>apartmentComplex:</strong> {insight.apartmentComplex.name}</p>
                    <p><strong>complexEnvironment:</strong> {insight.complexEnvironment}</p>
                    <p><strong>complexFacility:</strong> {insight.complexFacility}</p>
                    <p><strong>createdAt:</strong> {insight.createdAt}</p>
                    <p style={{ color:'blue' }}><strong>exchangeRequestCreatedByMe:</strong> {insight.exchangeRequestCreatedByMe ? 'O' : 'X'}</p>
                    <p style={{ color:'blue' }}><strong>exchangeRequestId:</strong> {insight.exchangeRequestId}</p>
                    <p style={{ color:'blue' }}><strong>exchangeRequestStatus:</strong> {insight.exchangeRequestStatus}</p>
                    <p><strong>favorableNews:</strong> {insight.favorableNews}</p>
                    <p><strong>infra:</strong> {insight.infra}</p>
                    <p style={{ color:'blue' }}><strong>insightId:</strong> {insight.insightId}</p>
                    <p style={{ color:'blue' }}><strong>mainImage:</strong> {insight.mainImage}</p>
                    <p><strong>memberId:</strong> {insight.memberId}</p>
                    <p style={{ color:'blue' }}><strong>memberNickname:</strong> {insight.memberNickname}</p>
                    <p style={{ color:'blue' }}><strong>recommended:</strong> {insight.recommended ? 'O' : 'X'}</p>
                    <p style={{ color:'blue' }}><strong>recommendedCount:</strong> {insight.recommendedCount}</p>
                    <p><strong>score:</strong> {insight.score}</p>
                    <p><strong>snapshotId:</strong> {insight.snapshotId}</p>
                    <p style={{ color:'blue' }}><strong>summary:</strong> {insight.summary}</p>
                    <p style={{ color:'blue' }}><strong>title:</strong> {insight.title}</p>
                    <p><strong>viewCount:</strong> {insight.viewCount}</p>
                    <p style={{ color:'blue' }}><strong>visitAt:</strong> {insight.visitAt}</p>
                    <p style={{ color:'blue' }}><strong>visitMethods:</strong> {insight.visitMethods}</p>
                    <p style={{ color:'blue' }}><strong>visitTimes:</strong> {insight.visitTimes}</p>
                </div>
            )}
        </div>
    );
}
