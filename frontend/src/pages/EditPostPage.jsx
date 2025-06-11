import axios from "axios";
import { useEffect, useState } from "react";
import { Outlet, useNavigate, useParams } from "react-router-dom";

export default function EditPostPage() {
  const { groupCode, id } = useParams();
  const navigate = useNavigate();
  const API_URL = process.env.REACT_APP_API_URL;

  const [post, setPost] = useState({ title: "", content: "" });
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  // 게시글 불러오기
  useEffect(() => {
    const fetchPost = async () => {
      try {
        const res = await axios.get(
          `${API_URL}/api/article/${groupCode}/${id}`
        );
        setPost({ title: res.data.title, content: res.data.content });
      } catch (err) {
        setError("게시글을 불러오는데 실패했습니다.");
      } finally {
        setLoading(false);
      }
    };
    fetchPost();
  }, [id]);

  // 변경사항 저장
  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const token = localStorage.getItem("accessToken");
      await axios.put(`${API_URL}/api/article/${groupCode}/${id}`, post, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      alert("게시글이 수정되었습니다.");
      navigate(`/article/${groupCode}/${id}`);
    } catch (err) {
      console.error(err);
      setError("게시글 수정에 실패했습니다.");
    }
  };

  if (loading) return <p>불러오는 중...</p>;
  if (error) return <p className="text-red-500">{error}</p>;

  return (
    <section>
      <div className="max-w-3xl mx-auto mt-10">
        <h2 className="text-2xl font-bold mb-6">게시글 수정</h2>
        <form onSubmit={handleSubmit} className="space-y-4">
          <div>
            <label className="block mb-1 font-medium">제목</label>
            <input
              type="text"
              value={post.title}
              onChange={(e) => setPost({ ...post, title: e.target.value })}
              className="w-full p-2 border rounded-md text-black"
              required
            />
          </div>
          <div>
            <label className="block mb-1 font-medium">내용</label>
            <textarea
              value={post.content}
              onChange={(e) => setPost({ ...post, content: e.target.value })}
              className="w-full p-2 h-48 border rounded-md text-black"
              required
            />
          </div>
          <button
            type="submit"
            className="px-6 py-2 bg-orange-500 text-white rounded hover:bg-orange-600"
          >
            저장하기
          </button>
        </form>
      </div>
      <Outlet />
    </section>
  );
}
