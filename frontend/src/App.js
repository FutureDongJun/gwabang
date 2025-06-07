import "./App.css";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Sidebar from "./layout/Sidebar";
import PostDetail from "./pages/PostDetail";
import LoginPage from "./pages/LoginPage";
import SignupPage from "./pages/SignupPage";
import DepartmentBoard from "./pages/DepartmentBoard";
import MainPage from "./pages/MainPage";
import Profile from "./pages/Profile";
import WriteArticle from "./pages/WriteArticle";

function App() {
  return (
    <div className="dark:bg-gray-900 min-h-screen">
      <BrowserRouter>
        <Routes>
          <Route element={<Sidebar />}>
            <Route index element={<MainPage />} />
            <Route path="/article/:groupCode" element={<DepartmentBoard />} />
            <Route path="/article/:groupCode/:id" element={<PostDetail />} />
            <Route path="/user/me" element={<Profile />} />
            <Route
              path="/article/:groupCode/write"
              element={<WriteArticle />}
            />
          </Route>
          <Route path="/login" element={<LoginPage />}></Route>
          <Route path="/signup" element={<SignupPage />}></Route>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
