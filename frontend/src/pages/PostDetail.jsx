import axios from "axios";
import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";

export default function PostDetail() {
  const { groupCode, id } = useParams();
  const [post, setPost] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const API_URL = process.env.REACT_APP_API_URL;
  const navigate = useNavigate();

  const fetchPost = async () => {
    try {
      const res = await axios.get(`${API_URL}/api/article/${groupCode}/${id}`);
      setPost(res.data);
      console.log(res.data);
    } catch (err) {
      setError("게시글을 불러오는데 실패했습니다.");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchPost();
  }, [id]);

  const handleDelete = async () => {
    try {
      const token = localStorage.getItem("accessToken"); // 인증 필요 시
      await axios.delete(`${API_URL}/api/article/${groupCode}/${id}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      alert("삭제가 완료되었습니다.");
      navigate(`/article/${groupCode}`);
    } catch (err) {
      console.error(err);
      alert("삭제 중 오류가 발생했습니다.");
    }
  };

  const handleEdit = () => {
    navigate(`/article/${groupCode}/${id}/edit`);
  };

  if (loading) return <p>불러오는 중</p>;
  if (error) return <p className="text-red-500">{error}</p>;
  if (!post) return <p>게시글이 없습니다.</p>;

  return (
    <div>
      <h1 className="text-2xl font-bold mb-2">{post.title}</h1>
      <div className="text-sm text-gray-500 mb-4">
        {post.nickname} • {new Date(post.createdAt).toLocaleDateString()}
      </div>
      <div className="prose prose-sm dark:prose-invert max-w-none mb-4">
        <p>{post.content}</p>
      </div>

      <div className="flex space-x-2">
        <button
          onClick={handleEdit}
          className="px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-600"
        >
          수정
        </button>
        <button
          onClick={handleDelete}
          className="px-4 py-2 bg-red-500 text-white rounded hover:bg-red-600"
        >
          삭제
        </button>
      </div>
    </div>
  );
}
