import "./App.css";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Sidebar from "./layout/Sidebar";
import PostDetail from "./pages/PostDetail";
import LoginPage from "./pages/LoginPage";
import SignupPage from "./pages/SignupPage";
import DepartmentBoard from "./pages/DepartmentBoard";
import MainPage from "./pages/MainPage";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route element={<Sidebar />}>
          <Route index element={<MainPage />} />
          <Route path="/article/:groupCode" element={<DepartmentBoard />} />
          <Route path="/article/:groupCode/:id" element={<PostDetail />} />
        </Route>
        <Route path="/login" element={<LoginPage />}></Route>
        <Route path="/signup" element={<SignupPage />}></Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
