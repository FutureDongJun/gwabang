import { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import axios from "axios";

export default function WriteArticle() {
  const { groupCode } = useParams(); // 현재 학과 코드
  const navigate = useNavigate();
  const API_URL = process.env.REACT_APP_API_URL || "http://localhost:8080";
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [categoryId, setCategoryId] = useState(null);
  const [loading, setLoading] = useState(true);
  const [departmentGroupName, setDepartmentGroupName] = useState("");
  useEffect(() => {
    const fetchDepartmentGroupName = async () => {
      try {
        const res = await axios.get(
          `${API_URL}/api/departmentGroup/${groupCode}`
        );
        setDepartmentGroupName(res.data);
        console.log(res);
      } catch (error) {
        console.error("학과그룹이름 조회 실패", error);
      } finally {
        setLoading(false);
      }
    };

    fetchDepartmentGroupName();
  }, [groupCode]);
  // 🚀 컴포넌트가 마운트될 때 categoryId를 먼저 가져오기
  useEffect(() => {
    const fetchCategoryId = async () => {
      try {
        const res = await axios.get(`${API_URL}/api/category/${groupCode}`);
        setCategoryId(res.data.id);
        setLoading(false);
      } catch (err) {
        console.error("카테고리 조회 실패:", err);
        alert("카테고리 정보를 가져올 수 없습니다.");
      }
    };

    fetchCategoryId();
  }, [API_URL, groupCode]);

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      console.log("요청 경로:", `${API_URL}/api/article/${groupCode}/write`);

      const token = localStorage.getItem("accessToken");
      console.log("Authorization:", token);
      console.log("typeof groupCode:", groupCode);
      const res = await axios.post(
        `${API_URL}/api/article/${groupCode}/write`,
        { title, content, categoryId: categoryId },
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );

      alert("게시글이 작성되었습니다.");
      navigate(`/article/${groupCode}`);
    } catch (err) {
      console.error("게시글 작성 실패:", err);
      const errorMessage =
        err.response?.data?.message || "게시글 작성에 실패했습니다.";
      alert(errorMessage);
    }
  };

  return (
    <div className="w-full min-h-screen px-16 py-10">
      <h2 className="text-3xl font-bold mb-8">
        {departmentGroupName} 게시글 작성
      </h2>
      <form onSubmit={handleSubmit} className="space-y-6">
        <div>
          <label className="block text-lg font-semibold mb-2">제목</label>
          <input
            type="text"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
            required
            className="w-full border border-gray-300 rounded-lg px-6 py-4 text-black text-lg shadow-sm bg-white"
            placeholder="제목을 입력하세요"
          />
        </div>

        <div>
          <label className="block text-lg font-semibold mb-2">내용</label>
          <textarea
            value={content}
            onChange={(e) => setContent(e.target.value)}
            required
            className="w-full border border-gray-300 rounded-lg px-6 py-4 h-[400px] text-black text-lg shadow-sm bg-white"
            placeholder="내용을 입력하세요"
          />
        </div>

        <div className="flex justify-end">
          <button
            type="submit"
            className="bg-green-600 hover:bg-green-700 text-white font-bold px-8 py-3 rounded-lg text-lg shadow-md"
          >
            ✏️ 작성하기
          </button>
        </div>
      </form>
    </div>
  );
}
