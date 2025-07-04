import axios from "axios";
import { Outlet } from "react-router";
import { useEffect, useState } from "react";

export default function Profile() {
  const [user, setUser] = useState(null);
  const API_URL = process.env.REACT_APP_API_URL || "http://localhost:8080";

  useEffect(() => {
    const token = localStorage.getItem("accessToken");
    console.log("accessToken:", token);
    axios
      .get(`${API_URL}/api/user/me`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((res) => {
        setUser(res.data);
      })
      .catch((err) => {
        console.error("내 정보 불러오기 실패:", err);
      });
  }, []);

  if (!user) return <div>로딩 중....</div>;

  return (
    <section>
      <div className="p-6">
        <h2 className="text-2xl font-bold mb-4">내 정보</h2>
        <p>
          <strong>이메일: </strong> {user.email}
        </p>
        <p>
          <strong>학과: </strong> {user.departmentName}
        </p>
        <p>
          <strong>닉네임: </strong> {user.nickname}
        </p>
      </div>
      <Outlet />
    </section>
  );
}
