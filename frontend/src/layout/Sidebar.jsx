import axios from "axios";
import { useEffect, useState } from "react";
import { Link, Outlet, useNavigate } from "react-router-dom";

export default function Sidebar() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const navigate = useNavigate();
  const API_URL = process.env.REACT_APP_API_URL;

  //로그인 상태 유지 (localStorage)
  useEffect(() => {
    const token = localStorage.getItem("accessToken");
    setIsLoggedIn(!!token); // 토큰이 있다면 로그인 상태인것임
  }, []);

  //로그아웃 기능
  const handleLogout = async () => {
    try {
      await axios.get(`${API_URL}/api/user/logout`); // 백엔드 API 호출

      // 토큰 삭제
      localStorage.removeItem("accessToken");
      localStorage.removeItem("refreshToken");

      setIsLoggedIn(false);
      navigate("/"); // 홈으로 이동
      alert("로그아웃 완료");
    } catch (error) {
      alert("로그아웃 실패: 다시 시도해주세요.");
    }

  }

  return (
    <div className="min-h-screen flex bg-gray-50 dark:bg-gray-900 text-gray-900 dark:text-white">
      {/* 좌측 사이드바 */}
      <aside className="w-64 p-6 bg-white dark:bg-[#1E2028] shadow-md">
        <div className="text-xl font-bold mb-6">학과방</div>

        {isLoggedIn ? (
          <button
            onClick={handleLogout}
            className="w-full mb-4 py-2 bg-red-500 hover:bg-red-600 text-white rounded-lg font-semibold"
          >
            로그아웃
          </button>
        ) : (
          <>
            <Link to={"/login"}>
              <button className="w-full mb-4 py-2 bg-orange-500 hover:bg-orange-600 text-white rounded-lg font-semibold">
                로그인
              </button>
            </Link>

            <Link to={"/signup"}>
              <button className="w-full mb-4 py-2 bg-green-500 hover:bg-green-600 text-white rounded-lg font-semibold">
                회원가입
              </button>
            </Link>
          </>
        )}


        <div className="mb-4 text-sm font-medium text-gray-700 dark:text-gray-300">
          학과별 커뮤니티 둘러보기
        </div>
        <input
          type="text"
          placeholder="학과 검색"
          className="w-full p-2 mb-4 rounded-md border border-gray-300 dark:border-gray-600 bg-white dark:bg-[#2A2C36] text-sm"
        />
        <ul className="space-y-2 text-sm">
          <li className="flex justify-between">
            <Link to="/article/화학공학과" className="hover:text-orange-600">
              화학공학과 <span className="text-gray-400">77,506명</span>
            </Link>
          </li>
          <li className="flex justify-between">
            <Link to="/article/컴퓨터공학과" className="hover:text-orange-600">
              컴퓨터공학과 <span className="text-gray-400">70,457명</span>
            </Link>
          </li>
          <li className="flex justify-between">
            <Link to="/article/심리학과" className="hover:text-orange-600">
              심리학과 <span className="text-gray-400">68,798명</span>
            </Link>
          </li>
          {/* 나중에 학과 검색 인풋창이랑 과나열한 것들 수정해서 경로로 이동하는 식으로 해야함 */}
        </ul>
      </aside>
      <Outlet />
    </div>
  );
}
