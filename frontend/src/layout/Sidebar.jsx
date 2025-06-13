import axios from "axios";
import { useEffect, useState } from "react";
import { Link, Outlet, useNavigate } from "react-router-dom";
import DepartmentDropdown from "../layout/DepartmentDropdown";

export default function Sidebar() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [userDepartment, setUserDepartment] = useState("");
  const navigate = useNavigate();
  const API_URL = process.env.REACT_APP_API_URL;
  const [selectedDepartment, setSelectedDepartment] = useState("");
  const [popularDepartments, setPopularDepartments] = useState([]);
  //로그인 상태 유지 (localStorage)

  const handleMoveToDepartment = async () => {
    if (!selectedDepartment) {
      alert("학과를 선택해주세요!");
      return;
    }

    try {
      const token = localStorage.getItem("accessToken");
      const response = await axios.get(`${API_URL}/api/departments/name`, {
        params: { name: selectedDepartment },
        headers: {
          Authorization: `Bearer ${token}`, // 필요 없으면 생략 가능
        },
      });
      const groupCode = response.data;
      navigate(`/article/${groupCode}`);
    } catch (error) {
      console.error("학과 groupCode 불러오기 실패:", error);
      alert("학과 이동 중 오류가 발생했습니다.");
    }
  };

  useEffect(() => {
    const fetchPopularDepartments = async () => {
      try {
        console.log("에베베");
        const res = await axios.get(`${API_URL}/api/departments/popular`);
        setPopularDepartments(res.data);
        console.log(res);
      } catch (error) {
        console.error("인기 학과 불러오기 실패", error);
      }
    };

    fetchPopularDepartments();
  }, []);

  useEffect(() => {
    const token = localStorage.getItem("accessToken");
    setIsLoggedIn(!!token); // 토큰이 있다면 로그인 상태인것임
  }, []);
  useEffect(() => {
    const token = localStorage.getItem("accessToken");
    setIsLoggedIn(!!token);
    if (token) {
      axios
        .get(`${API_URL}/api/user/me`, {
          headers: { Authorization: `Bearer ${token}` },
        })
        .then((res) => {
          setUserDepartment(res.data.departmentName);
        })
        .catch(() => {
          setUserDepartment("");
        });
    }
  }, [API_URL]);

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
  };

  return (
    <div className="min-h-screen flex bg-gray-50 dark:bg-gray-900 text-gray-900 dark:text-white">
      {/* 좌측 사이드바 */}
      <aside className="w-64 p-6 bg-white dark:bg-[#1E2028] shadow-md">
        <div className="flex items-center space-x-2 mb-6">
          <img src="/images/Gwabang_logo.jpg" alt="로고" className="w-6 h-6" />
          <span>
            <Link to={"/"}>학과방</Link>
          </span>
        </div>
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
        {isLoggedIn && (
          <Link to={"/user/me"}>
            <button className="w-full mb-4 py-2 bg-green-500 hover:bg-green-600 text-white rounded-lg font-semibold">
              내 정보 보기
            </button>
          </Link>
        )}
        <div className="mb-4 text-sm font-medium text-gray-700 dark:text-gray-300">
          학과별 커뮤니티 둘러보기
        </div>
        <div className="mb-5">
          <DepartmentDropdown
            selected={selectedDepartment}
            setSelected={setSelectedDepartment}
          />
          <button
            onClick={handleMoveToDepartment}
            className="mt-2 w-full py-2 bg-blue-500 hover:bg-blue-600 text-white rounded-lg font-semibold"
          >
            선택한 학과 게시판으로 이동
          </button>
        </div>
        <ul className="space-y-2 text-sm">
          {Array.isArray(popularDepartments) ? (
            popularDepartments.map((dept) => (
              <li key={dept.departmentId} className="flex justify-between">
                <Link
                  to={`/article/${dept.gr}`}
                  className="hover:text-orange-600"
                >
                  {dept.departmentName}{" "}
                  <span className="text-gray-400">
                    {dept.memberCount.toLocaleString()}명
                  </span>
                </Link>
              </li>
            ))
          ) : (
            <li className="text-gray-500">부서 정보를 불러오는 중입니다...</li>
          )}
        </ul>{" "}
      </aside>
      <Outlet />
    </div>
  );
}
