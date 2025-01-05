import React from "react";
import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import { useSelector } from "react-redux";
import WelcomePage from "./components/WelcomePage";
import LoginPage from "./components/LoginPage";
import RegisterPage from "./components/RegisterPage";
import Dashboard from "./components/DashBoard";
import { DatePicker } from 'antd';

const App = () => {
    const isAuthenticated = useSelector((state) => state.auth.isAuthenticated);

    return (
        <Router>
            <Routes>
                <Route path="/" element={<WelcomePage />} />
                <Route path="/loginUser" element={<LoginPage />} />
                <Route path="/register" element={<RegisterPage />} />
                <Route
                    path="/dashboard"
                    element={
                        isAuthenticated ? <Dashboard /> : <Navigate to="/loginUser" />
                    }
                />
            </Routes>
        </Router>
    );
};

export default App;
