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
    )
}
