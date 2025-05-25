import { Outlet } from "react-router";

export default function Sidebar() {
  return (
    <div className="min-h-screen flex bg-gray-50 dark:bg-gray-900 text-gray-900 dark:text-white">
      {/* 좌측 사이드바 */}
      <aside className="w-64 p-6 bg-white dark:bg-[#1E2028] shadow-md">
        <div className="text-xl font-bold mb-6">학과방</div>
        <button className="w-full mb-4 py-2 bg-orange-500 hover:bg-orange-600 text-white rounded-lg font-semibold">
          로그인
        </button>
        <button className="w-full mb-4 py-2 bg-green-500 hover:bg-green-600 text-white rounded-lg font-semibold">
          회원가입
        </button>
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
            화학공학과 <span className="text-gray-400">77,506명</span>
          </li>
          <li className="flex justify-between">
            컴퓨터공학과 <span className="text-gray-400">70,457명</span>
          </li>
          <li className="flex justify-between">
            심리학과 <span className="text-gray-400">68,798명</span>
          </li>
          {/* 나중에 학과 검색 인풋창이랑 과나열한 것들 수정해서 경로로 이동하는 식으로 해야함 */}
        </ul>
      </aside>

      {/* 본문 영역 */}
      <section className="flex-1 p-10">
        <h2 className="text-3xl font-bold mb-4">학과방이란?</h2>
        <p className="text-gray-600 dark:text-gray-300 mb-8">
          전공 학과별로 정보를 공유하고 소통할 수 있는 대학생 전용 커뮤니티
          플랫폼이에요!
        </p>

        <div className="grid grid-cols-2 gap-6">
          <div className="p-6 bg-white dark:bg-[#2A2C36] rounded-xl shadow">
            시험 정보 카드
          </div>
          <div className="p-6 bg-white dark:bg-[#2A2C36] rounded-xl shadow">
            강의실 대화 카드
          </div>
        </div>
        <Outlet />
      </section>
    </div>
  );
}
