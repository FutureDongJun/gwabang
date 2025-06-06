import axios from "axios";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

export default function PostDetail() {
  const { groupcode, id } = useParams();
  const [post, setPost] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchPost = async () => {
      try {
        const res = await axios.get(`/api/article/${groupcode}/${id}`);
        setPost(res.data);
      } catch (err) {
        setError("게시글을 불러오는데 실패했습니다.");
        console.error(err);
      } finally {
        setLoading(false);
      }
    };

    fetchPost();
  }, [id]);

  if (loading) return <p>불러오는 중</p>;
  if (error) return <p className="text-red-500">{error}</p>;
  if (!post) return <p>게시글이 없습니다.</p>;

  return (
    <div>
      <h1 className="text-2xl font-bold mb-2">{post.title}</h1>
      <div className="text-sm text-gray-500 mb-4">
        {post.author} • {new Date(post.createdAt).toLocaleDateString()}
      </div>
      <div className="prose prose-sm dark:prose-invert max-w-none">
        <p>{post.content}</p>
      </div>
    </div>
  );
}
