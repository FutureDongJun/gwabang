export default function LoginPage() {
  return (
    <div className="flex min-h-full flex-1 flex-col justify-center px-6 py-12 lg:px-8">
      <div className="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
        <form action="#" method="POST" className="space-y-6">
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
                required
                autoComplete="email"
                className="block w-full rounded-md bg-white px-3 py-1.5 text-base text-gray-900 outline-1 -outline-offset-1 outline-gray-300 placeholder:text-gray-400 focus:outline-2 focus:-outline-offset-2 focus:outline-orange-600 sm:text-sm/6"
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
              <div className="text-sm">
                <a
                  href="#"
                  className="font-semibold text-orange-600 hover:text-orange-500"
                >
                  아이디/비밀번호 찾기
                </a>
              </div>
            </div>
            <div className="mt-2">
              <input
                id="password"
                name="password"
                type="password"
                required
                autoComplete="current-password"
                className="block w-full rounded-md bg-white px-3 py-1.5 text-base text-gray-900 outline-1 -outline-offset-1 outline-gray-300 placeholder:text-gray-400 focus:outline-2 focus:-outline-offset-2 focus:outline-orange-600 sm:text-sm/6"
              />
            </div>
          </div>

          <div>
            <button
              type="submit"
              className="w-full mb-4 py-2 bg-orange-500 hover:bg-orange-600 text-white rounded-lg font-semibold"
            >
              로그인
            </button>
          </div>
        </form>
        
        <p className="mt-10 text-center text-sm/6 text-gray-500">
          <a
            href="#"
            className="font-semibold text-orange-600 hover:text-orange-500"
          >
            회원가입
          </a>
        </p>
      </div>
    </div>
  );
}
