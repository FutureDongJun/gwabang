import DepartmentDropdown from "../layout/DepartmentDropdown";
import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router";

export default function SignupPage() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [passwordConfirm, setPasswordConfirm] = useState("");
  const [selectedDepartment, setSelectedDepartment] = useState("");
  const API_URL = process.env.REACT_APP_API_URL;
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (password !== passwordConfirm) {
      alert("비밀번호가 일치하지 않음.");
      return;
    }

    try {
      const res = await axios.post(`${API_URL}/signup`, {
        email,
        password,
        department: selectedDepartment, //departmentdropdown에서 선택
      });

      alert("회원가입 완료");
      navigate("/login");
    } catch (err) {
      console.error(err);
      alert("회원가입 실패:" + err.response?.data?.message || "서버오류");
    }
  };

  return (
    <div className="flex min-h-full flex-col justify-center items-center px-6 py-12 lg:px-8">
      <div className="w-full items-center justify-center max-w-sm mt-10 pr-auto">
        <form
          onSubmit={handleSubmit}
          action="#"
          method="POST"
          className="space-y-6"
        >
          <div>
            <label
              htmlFor="email"
              className="block text-sm/6 font-medium text-gray-900"
            >
              이메일 주소
            </label>
            <div className="mt-2">
              <input
                id="email"
                name="email"
                type="email"
                value={email}
                required
                onChange={(e) => setEmail(e.target.value)}
                autoComplete="email"
                className="block w-full rounded-md bg-white px-3 py-1.5 text-base text-gray-900 outline-1 -outline-offset-1 outline-gray-300 placeholder:text-gray-400 focus:outline-2 focus:-outline-offset-2 focus:outline-green-600 sm:text-sm/6"
              />
            </div>
          </div>
          <div>
            <label className="block text-sm font-medium text-gray-900 mb-1">
              학과
            </label>
            <div className="mt-1">
              <DepartmentDropdown
                selected={selectedDepartment}
                setSelected={setSelectedDepartment}
              />
            </div>
          </div>

          <div>
            <div className="flex items-center justify-between">
              <label
                htmlFor="password"
                className="block text-sm/6 font-medium text-gray-900"
              >
                비밀번호
              </label>
            </div>
            <div className="mt-2">
              <input
                id="password"
                name="password"
                type="password"
                value={password}
                required
                autoComplete="current-password"
                onChange={(e) => setPassword(e.target.value)}
                className="block w-full rounded-md bg-white px-3 py-1.5 text-base text-gray-900 outline-1 -outline-offset-1 outline-gray-300 placeholder:text-gray-400 focus:outline-2 focus:-outline-offset-2 focus:outline-green-600 sm:text-sm/6"
              />
            </div>
          </div>
          <div>
            <div className="flex items-center justify-between">
              <label
                htmlFor="password"
                className="block text-sm/6 font-medium text-gray-900"
              >
                비밀번호 확인
              </label>
            </div>
            <div className="mt-2">
              <input
                id="password"
                name="password"
                type="password"
                value={passwordConfirm}
                onChange={(e) => setPasswordConfirm(e.target.value)}
                required
                autoComplete="passwordConfirm"
                className="block w-full rounded-md bg-white px-3 py-1.5 text-base text-gray-900 outline-1 -outline-offset-1 outline-gray-300 placeholder:text-gray-400 focus:outline-2 focus:-outline-offset-2 focus:outline-green-600 sm:text-sm/6"
              />
            </div>
          </div>
          <div>
            <button
              type="submit"
              className="w-full mb-4 py-2 bg-green-500 hover:bg-green-600 text-white rounded-lg font-semibold"
            >
              회원가입
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}
