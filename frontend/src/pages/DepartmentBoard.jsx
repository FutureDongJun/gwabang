import axios from "axios";
import { useState, useEffect } from "react";
import { Outlet, useParams, useNavigate } from "react-router";

export default function DepartmentBoard() {
  const { groupCode } = useParams(); // url에서 학과 코드 추출하는 거임
  const [articles, setArticles] = useState([]);
  const [loading, setLoading] = useState(true);
  const [departmentGroupName, setDepartmentGroupName] = useState("");
  const navigate = useNavigate();
  const API_URL = process.env.REACT_APP_API_URL;

  useEffect(() => {
    console.log("departmentboard groupcode: ", groupCode);
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

  useEffect(() => {
    const fetchArticles = async () => {
      try {
        console.log("일단오긴함");
        const res = await axios.get(
          `${API_URL}/api/article/${groupCode}/list`,
          {
            headers: {
              Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
            },
          }
        );
        setArticles(res.data);
        console.log(res);
      } catch (error) {
        console.error("게시글 목록 조회 실패", error);
      } finally {
        setLoading(false);
      }
    };

    fetchArticles();
  }, [groupCode]);

  if (loading) return <p>불러오는 중</p>;

  return (
    <div className="p-6">
      <div className="flex justify-between items-center mb-6">
        <h1 className="text-2xl font-bold">{departmentGroupName} 게시판</h1>

        <div className="ml-4">
          <button
            onClick={() => navigate(`/article/${groupCode}/write`)}
            className="px-4 py-2 bg-green-500 hover:bg-green-600 text-white rounded-lg font-semibold shadow"
          >
            게시글 작성하기
          </button>
        </div>
      </div>

      {articles.length === 0 ? (
        <p className="text-gray-500">게시글이 없습니다.</p>
      ) : (
        <ul className="space-y-4">
          {articles.map((article) => (
            <li
              key={article.id}
              className="p-4 bg-white dark:bg-[#2A2C36] rounded shadow"
              onClick={() => {
                console.log(
                  "navigate groupCode:",
                  groupCode,
                  "article id:",
                  article.id
                );
                navigate(`/article/${groupCode}/${article.id}`);
              }}
            >
              <h2 className="text-lg font-semibold">{article.title}</h2>
              <p className="text-sm text-gray-500">
                {new Date(article.createdAt).toLocaleDateString()} •{" "}
                {article.author}
              </p>
              <p className="mt-2 text-gray-700 dark:text-gray-300 line-clamp-2">
                {article.content}
              </p>
            </li>
          ))}
        </ul>
      )}
      <Outlet />
    </div>
  );
}
