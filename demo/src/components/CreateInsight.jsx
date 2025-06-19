import {useState} from "react";
import {useNavigate} from "react-router-dom";
import {createInsight} from "../api-insight.js";

export default function CreateInsight() {
    const [title, setTitle] = useState("");
    const [mainImage, setMainImage] = useState(null);
    const [siDo, setSiDo] = useState("");
    const [siGunGu, setSiGunGu] = useState("");
    const [eupMyeonDong, setEupMyeonDong] = useState("");
    const [apartmentComplexName, setApartmentComplexName] = useState("");
    const navigate = useNavigate();

    /*
    const [formData, setFormData] = useState({
        // memberId: "",
        score: 0,
        mainImage: null,
        title: "",
        address: {
            // buildingNumber: "",
            // detail: "",
            eupMyeonDong: "효제동",
            // roadName: "",
            siDo: "서울특별시",
            siGunGu: "종로구",
            // latitude: "",
            // longitude: ""
        },
        apartmentComplex: {
            name: "태릉해링턴플레이스"
        },
        visitAt: "2024-12-31",
        visitTimes: ["아침", "저녁"],
        visitMethods: ["자차", "도보"],
        summary: "지하철역과 도보 10분 거리로 접근성이 좋지만, 근처 공사로 소음 문제가 있을 수 있을 것 같아요. 하지만 단지 내 공원이 잘 조성되어 있어 가족 단위 거주자에게 적합할 것 같아요. 지하철역과 도보 10분 거리로 접근성이 좋지만, 근처 공사로 소음 문제가 있을 수 있을 것 같아요.",
        access: "허락시_가능",
        infra: {
            transportations: ["해당_없음"],
            schoolDistricts: ["초품아"],
            amenities: ["소형마트"],
            facilities: ["헬스장"],
            surroundings: ["산책로"],
            text: "총평"
        },
        complexEnvironment: {
            buildingCondition: "잘_모르겠어요",
            security: "잘_모르겠어요",
            childrenFacility: "잘_모르겠어요",
            text: "총평"
        }
    });

    const handleChange = (e) => {
        const {name, value, type, files} = e.target;
        setFormData((prev) => {
            const keys = name.split(".");
            if (keys.length > 1) {
                return {
                    ...prev,
                    [keys[0]]: {
                        ...prev[keys[0]],
                        [keys[1]]: value
                    },
                };
            }
            return {
                ...prev,
                [name]: type === "file" ? files[0] : value,
            };
        });
    };*/

    const handleSubmit = async (e) => {
        e.preventDefault();

        // console.log(formData);
        const payload = new FormData();
        payload.append(
            "createInsightCommand",
            new Blob([JSON.stringify({
                score: 0,
                mainImage: null,
                title: title,
                address: {
                    // buildingNumber: "",
                    // detail: "",
                    eupMyeonDong: eupMyeonDong,
                    // roadName: "",
                    siDo: siDo,
                    siGunGu: siGunGu,
                    // latitude: "",
                    // longitude: ""
                },
                apartmentComplex: {
                    name: apartmentComplexName
                },
                visitAt: "2024-12-31",
                visitTimes: ["아침", "저녁"],
                visitMethods: ["자차", "도보"],
                summary: "지하철역과 도보 10분 거리로 접근성이 좋지만, 근처 공사로 소음 문제가 있을 수 있을 것 같아요. 하지만 단지 내 공원이 잘 조성되어 있어 가족 단위 거주자에게 적합할 것 같아요. 지하철역과 도보 10분 거리로 접근성이 좋지만, 근처 공사로 소음 문제가 있을 수 있을 것 같아요.",
                access: "허락시_가능",
                infra: {
                    transportations: ["해당_없음"],
                    schoolDistricts: ["초품아"],
                    amenities: ["소형마트"],
                    facilities: ["헬스장"],
                    surroundings: ["산책로"],
                    text: "총평"
                },
                complexEnvironment: {
                    buildingCondition: "잘_모르겠어요",
                    security: "잘_모르겠어요",
                    childrenFacility: "잘_모르겠어요",
                    text: "총평"
                }
                // access: formData.access,
                // address: formData.address,
                // apartmentComplex: formData.apartmentComplex,
                // complexEnvironment: formData.complexEnvironment,
                // infra: formData.infra,
                // score: formData.score,
                // summary: formData.summary,
                // title: formData.title,
                // visitAt: formData.visitAt,
                // visitMethods: formData.visitMethods,
                // visitTimes: formData.visitTimes
            })], { type: "application/json" }));
        payload.append("mainImage", mainImage);
        await createInsight(payload);
        navigate("/");
    };


    return (
        <div className="max-w-lg mx-auto p-6 bg-white rounded-lg shadow-md">
            <h2 className="text-2xl font-bold mb-4">인사이트 작성</h2>
            <form onSubmit={handleSubmit} className="space-y-4">
                <label className="block">
                    Title
                    <input
                        type="text"
                        value={title}
                        onChange={(e) => setTitle(e.target.value)}
                        className="w-full p-2 border rounded"
                        required
                    />
                </label>
                <label className="block">
                    시/도
                    <input
                        type="text"
                        value={siDo}
                        onChange={(e) => setSiDo(e.target.value)}
                        className="w-full p-2 border rounded"
                        required
                    />
                </label>
                <label className="block">
                    시/군/구
                    <input
                        type="text"
                        value={siGunGu}
                        onChange={(e) => setSiGunGu(e.target.value)}
                        className="w-full p-2 border rounded"
                        required
                    />
                </label>
                <label className="block">
                    읍/면/동
                    <input
                        type="text"
                        value={eupMyeonDong}
                        onChange={(e) => setEupMyeonDong(e.target.value)}
                        className="w-full p-2 border rounded"
                        required
                    />
                </label>
                <label className="block">
                    단지
                    <input
                        value={apartmentComplexName}
                        onChange={(e) => setApartmentComplexName(e.target.value)}
                        className="w-full p-2 border rounded"
                        required
                    />
                </label>

                <label className="block">
                    Main Image
                    <input type="file" name="mainImage" onChange={(e) => setMainImage(e.target.files[0])} className="w-full p-2 border rounded" required />
                </label>
                <button type="submit" className="w-full p-2 bg-blue-500 text-white rounded">작성 완료</button>
            </form>
        </div>
    );
}
