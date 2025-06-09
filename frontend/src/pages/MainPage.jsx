import { Outlet } from "react-router"

export default function MainPage() {
    return (
        <div>
            {/* 본문 영역 */}
            <section className="flex-1 p-10">
                <h2 className="text-3xl font-bold mb-4">학과방이란?</h2>
                <p className="text-gray-600 dark:text-gray-300 mb-8">
                    전공 학과별로 정보를 공유하고 소통할 수 있는 대학생 전용 커뮤니티
                    플랫폼이에요!
                </p>
                  {/* 🔻 소개 배너 영역 추가 */}
  <div className="mb-8 flex flex-col md:flex-row items-center bg-white dark:bg-[#2A2C36] rounded-xl shadow p-6 gap-6">
    <img 
      src="/images/Gwabang_logo.jpg" // public/images/gwabang_intro.png 위치에 저장
      alt="학과방 소개"
      className="w-full md:w-1/3 rounded-lg"
    />
    <div className="flex-1">
      <h3 className="text-2xl font-bold mb-2 text-gray-800 dark:text-white">우리 학과 학생들의 소통 공간</h3>
      <p className="text-gray-600 dark:text-gray-300 leading-relaxed">
        같은 전공의 친구들과 강의 정보, 시험 후기, 대학 생활의 꿀팁을 나누세요! <br />
        누구보다 가까운 정보를, 누구보다 빠르게 만날 수 있어요.
      </p>
    </div>
  </div>
                <Outlet />
            </section>
        </div>
    )
}
