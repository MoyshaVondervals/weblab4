import React from "react";
import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import WelcomePage from "./components/WelcomePage";
import LoginPage from "./components/LoginPage";
import RegisterPage from "./components/RegisterPage";
import Dashboard from "./components/DashBoard";
import { DatePicker } from 'antd';
import PrivateRoute from "./PrivateRoute";

const App = () => {

    return (
        <Router>
            <Routes>
                <Route path="/" element={<WelcomePage />} />
                <Route path="/login" element={<LoginPage />} />
                <Route path="/register" element={<RegisterPage />} />
                {/*<Route path="/dashboard" element={<PrivateRoute component={Dashboard} />} />*/}
                <Route path="/dashboard" element={<Dashboard />} />
            </Routes>
        </Router>
    );
};


export default App;
