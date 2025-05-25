import axios from "axios";
import { useEffect } from "react";

export default function DepartmentBoard() {
    const { groupCode } = useParams(); // url에서 학과 코드 추출하는 거임
    const [articles, setArticles] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const fetchArticles = async () => {
            try {
                const res = await axios.get(`/api/article/${groupCode}`);
                setArticles(res.data);
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
            <h1 className="text-2xl font-bold mb-6">{groupCode} 게시판</h1>

            {articles.length === 0 ? (
                <p className="text-gray-500">게시글이 없습니다.</p>
            ) : (
                <ul className="space-y-4">
                    {articles.map((article) => (
                        <li key = {article.id} className="p-4 bg-white dark:bg-[#2A2C36] rounded shadow">
                            <h2 className="text-lg font-semibold">{article.title}</h2>
                            <p className="text-sm text-gray-500">
                            {article.author} • {new Date(article.createdAt).toLocaleDateString()}
                            </p>
                            <p className="mt-2 text-gray-700 dark:text-gray-300 line-clamp-2">
                                {article.content}
                            </p>
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
}